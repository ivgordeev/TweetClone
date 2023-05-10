package com.tweetclone.controller;


import com.tweetclone.entity.User;
import com.tweetclone.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

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
    public String doRegister(@RequestParam("password2") String passwordConfirm, @Valid User user,
                             BindingResult bindingResult, Model model) {
        boolean isConfirmEmpty = !StringUtils.hasLength(passwordConfirm);
        if (isConfirmEmpty) {
            model.addAttribute("password2Error", "Password confirmation cannot be empty");
        }

        if (user.getPassword() != null && !user.getPassword().equals(passwordConfirm)) {
            model.addAttribute("passwordError", "Passwords are different");
        }

        if (isConfirmEmpty || bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtil.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "/registration";
        }

        if (!userService.addUser(user)) {
            model.addAttribute("usernameError", "User is already exists!");
            return "registration";
        }

        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    private String activate(Model model, @PathVariable String code) {
        boolean isActive = userService.activeUser(code);
        if (isActive) {
            model.addAttribute("message", "User successfully activated");
            model.addAttribute("type", "success");
        } else {
            model.addAttribute("message", "Activation code not found");
            model.addAttribute("type", "danger");
        }
        return "login";
    }
}
