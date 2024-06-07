package org.zerock.b01.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute
    public void addAttributes(HttpSession session, Model model) {
        String loginId = (String) session.getAttribute("loginSession");
        model.addAttribute("isLoggedIn", loginId != null);
    }
}