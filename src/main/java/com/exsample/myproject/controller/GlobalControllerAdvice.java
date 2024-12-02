package com.exsample.myproject.controller;

import org.springframework.security.web.csrf.CsrfToken;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute
    public void addCsrfTokenToModel(Model model, HttpServletRequest request) {
        CsrfToken csrfToken = (CsrfToken) request.getAttribute("_csrf");
        if (csrfToken != null) {
            model.addAttribute("_csrf", csrfToken);
            System.out.println("CSRF Token added to model: " + csrfToken.getToken());
        } else {
            System.out.println("CSRF Token is null");
        }
    }
}

