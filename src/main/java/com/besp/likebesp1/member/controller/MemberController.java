package com.besp.likebesp1.member.controller;

import com.besp.likebesp1.member.dto.MemberDto;
import com.besp.likebesp1.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static com.besp.likebesp1.common.LoginUser.LOGIN_USER;

@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;


    @GetMapping("/member/login")
    public String getLogin() {
        return "/member/login";
    }


    @PostMapping("/member/login")
    public ResponseEntity<String> postLogin(String userId, String password, HttpServletRequest request) {

        Long memberId = memberService.isMember(userId, password);

        if (memberId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        HttpSession session = request.getSession();
        session.setAttribute(LOGIN_USER.name(), memberId);
        return new ResponseEntity<>("/", HttpStatus.OK);
    }

    @GetMapping("/member/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(LOGIN_USER.name());
        return "redirect:/";
    }

    @GetMapping("/member/register")
    public String getRegister() {
        return "/member/register";
    }


    @PostMapping("/member/register")
    public ResponseEntity<String> postRegister(MemberDto dto) {
        if (!memberService.canMemberBeRegistered(dto)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        memberService.save(dto);
        return new ResponseEntity<>("/", HttpStatus.OK);
    }

}
