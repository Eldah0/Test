package com.codingdojo.testjava.services;

import com.codingdojo.testjava.models.User;
import com.codingdojo.testjava.repositories.CourseRepository;
import com.codingdojo.testjava.models.Course;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public List<Course> allcourse(){
        return courseRepository.findAll();
    }

    public Course updateCourse(Course course) {
        return courseRepository.save(course);
    }


    public List<Course> getAssignedCourse(User user){
        return (List<Course>) courseRepository.findByIdIs(user.getId());
    }

    public List<Course> getUnassignedCourse(User user){
        return (List<Course>) courseRepository.findByIdIs(user.getId());
    }

    public Course create(Course courses) {
        return courseRepository.save(courses);
    }

    public void delete(Course course) {
        courseRepository.delete(course);
    }
    public Course addCourses(Course course) {
        return courseRepository.save(course);
    }
    public Course getCourses(Course course) {
        return courseRepository.save(course);
    }

    public Course findById(Long id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if(optionalCourse.isPresent()) {
            return optionalCourse.get();
        }else {
            return null;
        }
    }

    public List<Course> allCourses(){
        return courseRepository.findAll();
    }



    public List<Course> getAssignedCourses(User user){
        return courseRepository.findAllByUser(user);
    }

    public List<Course> getUnassignedCourses(User user){
        return courseRepository.findByUserNotContains(user);
    }

    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    public void deleteCourse(Course course) {
        courseRepository.delete(course);
    }



}
