package com.besp.likebesp1.common.advice;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import static com.besp.likebesp1.common.LoginUser.LOGIN_USER;

@ControllerAdvice
public class LoginControllerAdvice {
    @ModelAttribute("loginUser")
    public Long loginUser(HttpSession session) {
        return (Long) session.getAttribute(LOGIN_USER.name());
    }
}
