package com.springlogin.login.controllers;

import com.springlogin.login.models.User;
import com.springlogin.login.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRep;

    @GetMapping("/")
    public String main(Model model){
        return "home";
    }

    @ExceptionHandler(ResponseStatusException.class)
    public String handleException(Model model){
        return "error-page";
    }

    @GetMapping("/login")
    public String login(Model model){
        return "signIn-page";
    }

    @GetMapping("/register")
    public String register(Model model){
        return "register-page";
    }

    @PostMapping("/register")
    public String createAnAccount(@RequestParam String first_name, @RequestParam String last_name, @RequestParam String email, @RequestParam String password, Model model){
        Optional<User> oUser = userRep.findById(email);
        if(oUser.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists!");
        }
        User user = new User(first_name,last_name,email,password);
        userRep.save(user);
        return "redirect:/login";
    }

}
