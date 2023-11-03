package com.besp.likebesp1.member.controller;

import com.besp.likebesp1.member.dto.MemberDto;
import com.besp.likebesp1.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/login")
    public String getLogin() {
        return "/member/login";
    }

    @PostMapping("/member/login")
    public String postLogin(Model model, String userId, String password) {
        if (memberService.isMember(userId, password)) {
            return "redirect:/";
        }
        model.addAttribute("message", "잘못된 id 또는 password 입니다");
        return "/member/login";
    }

    @GetMapping("/member/register")
    public String getRegister() {
        return "/member/register";
    }

    @PostMapping("/member/register")
    public String postRegister(MemberDto dto) {
        memberService.save(dto);
        return "redirect:/";
    }

}
