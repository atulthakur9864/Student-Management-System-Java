package com.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.sms.entity.User;
import com.sms.service.UserService;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login"; // login.html banayenge
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "signup"; // signup.html banayenge
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        try {
            userService.saveUser(user);
            return "redirect:/login?success";
        } catch (Exception e) {
            return "redirect:/signup?error";
        }
    }
}