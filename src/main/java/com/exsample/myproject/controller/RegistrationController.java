package com.exsample.myproject.controller;

import com.exsample.myproject.domain.Role;
import com.exsample.myproject.domain.User;
import com.exsample.myproject.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration( Model model) {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser( User user, Map<String, Object> model) {
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (userFromDb != null) {
            model.put("message", "User exists");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login( Map<String, Object> model) {
        return "login";
    }
}
