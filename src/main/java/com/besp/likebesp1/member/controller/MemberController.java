package com.besp.likebesp1.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {


    @GetMapping("/member/login")
    public String getLogin() {
        return "/member/login";
    }

    @PostMapping("/member/login")
    public String postLogin(String username, String password) {
        return "/";
    }
}
