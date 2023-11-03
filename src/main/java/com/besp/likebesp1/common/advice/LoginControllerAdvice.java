package com.besp.likebesp1.common.advice;

import com.besp.likebesp1.common.LoginUser;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class LoginControllerAdvice {
    @ModelAttribute("loginUser")
    public String loginUser(HttpSession session) {
        return (String) session.getAttribute(LoginUser.LOGIN_USER.name());
    }
}
