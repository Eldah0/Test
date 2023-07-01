package com.codingdojo.testjava.controller;

import com.codingdojo.testjava.models.Course;
import com.codingdojo.testjava.models.LoginUser;
import com.codingdojo.testjava.models.User;
import com.codingdojo.testjava.services.CourseService;
import com.codingdojo.testjava.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;


    @GetMapping("/")
    public String index(Model model, @ModelAttribute("newUser") User newUser, @ModelAttribute("newLogin") User newLogin, HttpSession session) {
        Long loggedInUserID = (Long) session.getAttribute("loggedInUserID");
        if (loggedInUserID != null) {
            return "redirect:/dashboard";
        }
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model, HttpSession session) {
        User user = userService.register(newUser, result);
        if (result.hasErrors()) {
            model.addAttribute("newLogin", new LoginUser());
            return "index";
        }
        session.setAttribute("loggedInUserID", newUser.getId());
        return "redirect:/dashboard";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model, HttpSession session) {
        User user = userService.login(newLogin, result);
        if (result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index";
        }
        session.setAttribute("loggedInUserID", user.getId());
        return "redirect:/dashboard";
    }

    @RequestMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        if (session.getAttribute("loggedInUserID") == null) {
            return "redirect:/";
        }
        Long userId = (Long) session.getAttribute("loggedInUserID");
        User user = userService.findById(userId);
        model.addAttribute("user", user);
        model.addAttribute("allClasses", courseService.allCourses());
        return "dashboard";
    }



    @GetMapping("/courses/new")
    public String newC(@ModelAttribute("course") Course course, HttpSession session, Model model){
        if(session.getAttribute("loggedInUserID") == null) {
            return "redirect:/logout";
        }
        Long userId = (Long) session.getAttribute("loggedInUserID");
        User user = userService.findById(userId);
        model.addAttribute("user",user);
        return "new";
    }


    @PostMapping("/courses")
    public String createC(@Valid @ModelAttribute("course")Course course,BindingResult result, HttpSession session){
        if(session.getAttribute("loggedInUserID") == null) {
            return "redirect:/logout";
        }
        if (result.hasErrors()){
            return "new";
        }else {
            courseService.addCourse(course);
            Long userId = (Long) session.getAttribute("loggedInUserID");
            User user = userService.findById(userId);
            user.getInstructorCourses().add(course);

            course.setUser(user);
            userService.updateUser(user);
            return "redirect:/dashboard";
        }
    }

    @GetMapping("/courses/{id}")
    public String courseDetail(Model model, @PathVariable("id")Long id, HttpSession session){

        if (session.getAttribute("loggedInUserID")==null){
            return "redirect:/home";
        }
        Course course = courseService.findById(id);
        model.addAttribute("course",course);
        model.addAttribute("user",userService.findById((Long) session.getAttribute("loggedInUserId")));
        return "classes";
    }

    @GetMapping("/courses/{id}/edit")
    public String editCourse(Model model, @PathVariable("id")Long id,HttpSession session) {

        if (session.getAttribute("loggedInUserID") == null) {
            return "redirect:/dashboard";

        }
        Course course = courseService.findById(id);
        model.addAttribute("course",course);
        return "edit";
    }

    @PostMapping("/courses/{id}/update")
    public String update(@Valid @ModelAttribute("course")Course course ,BindingResult result,Model model, @PathVariable("id")Long id,HttpSession session) {
        Long userId = (Long) session.getAttribute("loggedInUserID");
        if (result.hasErrors()) {
            return "edit";
        }else {
            courseService.addCourse(course);
            User user = userService.findById(userId);
            user.getInstructorCourses().add(course);
            course.setUser(user);
            userService.updateUser(user);
            return "redirect:/dashboard";
        }
    }

    @RequestMapping("/courses/{id}/delete")
    public String delete(@PathVariable("id")Long id,HttpSession session){
        Course course = courseService.findById(id);
        courseService.deleteCourse(course);
        return "redirect:/dashboard";
    }

    @RequestMapping("/logout")
    public String logout (HttpServletRequest request, HttpSession session ){
        session.invalidate();
        return "redirect:/";
    }

}