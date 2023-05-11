package com.tweetclone.controller;

import com.tweetclone.entity.Role;
import com.tweetclone.entity.User;
import com.tweetclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Ivan Gordeev 09.05.2023
 */

@Controller
@RequestMapping("/user")

public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public String userList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "userList";
    }

    @GetMapping("/{user}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public String userSave(@RequestParam("userId") User user, @RequestParam Map<String, String> form,
                           @RequestParam String username) {
        userService.saveUser(user, username, form);
        return "redirect:/user";
    }

    @GetMapping("/profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@AuthenticationPrincipal User user, @RequestParam String password,
                                @RequestParam String email) {
        userService.updateProfile(user, password, email);
        return "redirect:/user/profile";
    }


    @GetMapping("/subscribe/{user}")
    public String subscribe(@PathVariable User user, @AuthenticationPrincipal User currentUser) {
        userService.subscribe(currentUser, user);
        return "redirect:/user-messages/" + user.getId();
    }

    @GetMapping("/unsubscribe/{user}")
    public String unsubscribe(@PathVariable User user, @AuthenticationPrincipal User currentUser) {
        userService.unsubscribe(currentUser, user);
        return "redirect:/user-messages/" + user.getId();
    }

    @GetMapping("/{type}/{user}/list")
    public String userList(Model model, @PathVariable User user, @PathVariable String type) {
        model.addAttribute("userChannel", user);
        model.addAttribute("type", type);
        if (type.equals("subscriptions")) {
            model.addAttribute("users", user.getSubscriptions());
        } else {
            model.addAttribute("users", user.getSubscribers());
        }
        return "subscriptions";
    }
}
