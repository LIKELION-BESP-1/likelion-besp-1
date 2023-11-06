package com.besp.likebesp1.common.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

import static com.besp.likebesp1.common.LoginUser.LOGIN_USER;

public class GuestUserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute(LOGIN_USER.name()) != null) {
            response.sendRedirect("/");
            return false;
        }

        return true;
    }
}
