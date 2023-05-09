package com.tweetclone.controller;


import com.tweetclone.entity.User;
import com.tweetclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Ivan Gordeev 07.05.2023
 */
@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String doRegister(User user, Model model) {
        if (!userService.addUser(user)) {
            model.addAttribute("message", "User is already exists!");
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    private String activate(Model model, @PathVariable String code) {
        boolean isActive = userService.activeUser(code);
        if (isActive) {
            model.addAttribute("message", "User successfully activated");
        } else {
            model.addAttribute("message", "Activation code not found");
        }
        return "login";
    }
}
