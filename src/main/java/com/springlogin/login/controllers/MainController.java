package com.springlogin.login.controllers;

import com.springlogin.login.models.User;
import com.springlogin.login.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @GetMapping("/home")
    public String homePage(Model model){
        return "home";
    }
    @GetMapping("/")
    public String main(Model model){
        return "unauthorized";
    }

    @GetMapping("/error-page")
    public String handleException(Model model){
        return "error-page";
    }

    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "register-page";
    }

    @PostMapping("/register")
    public String createAnAccount(User user){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRep.save(user);
        return "redirect:/login";
    }

}
