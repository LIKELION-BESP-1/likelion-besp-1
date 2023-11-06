package com.besp.likebesp1.common.interceptor;

import com.besp.likebesp1.common.LoginUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthenticatedUserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(LoginUser.LOGIN_USER.name()) == null) {
            response.sendRedirect("/member/login");
            return false;
        }

        return true;
    }
}
