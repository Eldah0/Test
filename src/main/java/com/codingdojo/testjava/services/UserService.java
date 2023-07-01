package com.codingdojo.testjava.services;


import com.codingdojo.testjava.models.User;
import com.codingdojo.testjava.repositories.UserRepository;
import com.codingdojo.testjava.models.LoginUser;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;



@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User register(User newUser, BindingResult result) {

        Optional<User> potentialUser = this.userRepository.findByEmail(newUser.getEmail());
        if(potentialUser.isPresent()) {
            result.rejectValue("email", "EmailTaken", "An account with that email already exists!");
        }

        if(!newUser.getPassword().equals(newUser.getConfirm())) {
            result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
        }

        if(result.hasErrors()) {
            return null;
        }
        else {
            // Hash and set password, save user to database
            String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
            newUser.setPassword(hashed);
            return userRepository.save(newUser);
        }

    }

    public User login(LoginUser newLoginObject, BindingResult result) {

        Optional<User> potentialUser = this.userRepository.findByEmail(newLoginObject.getEmail());

        if(!potentialUser.isPresent()) {
            result.rejectValue("email", "EmailNotFound", "User not found!");
        }else{
            if(!BCrypt.checkpw(newLoginObject.getPassword(), potentialUser.get().getPassword())) {
                result.rejectValue("password", "Matches", "Invalid Password!");
            }
        }

        if(result.hasErrors()) {
            return null;
        }

        return potentialUser.get();
    }
    public User updateUser(User user) {
        return userRepository.save(user);
    }
    public User add(User user) {
        return userRepository.save(user);
    }
    public User findById(Long id){return this.userRepository.findById(id).orElse(null);}


}


