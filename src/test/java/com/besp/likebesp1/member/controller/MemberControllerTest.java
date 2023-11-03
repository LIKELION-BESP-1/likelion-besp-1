package com.besp.likebesp1.member.controller;

import com.besp.likebesp1.member.dto.MemberDto;
import com.besp.likebesp1.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest
class MemberControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    MemberService memberService;


    @Test
    void loginGet() throws Exception {

        mvc.perform(get("/member/login"))
                .andExpect(handler().handlerType(MemberController.class))
                .andExpect(handler().methodName("getLogin"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("/member/login"));
    }


    @Test
    void successLogin() throws Exception {
        MemberDto memberDto = new MemberDto("user5", "1234", "hello", "email", "myphone");
        memberService.save(memberDto);

        mvc.perform(post("/member/login")
                        .param("userId", "user5")
                        .param("password", "1234"))
                .andExpect(handler().handlerType(MemberController.class))
                .andExpect(handler().methodName("postLogin"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void failLogin() throws Exception {

        mvc.perform(post("/member/login")
                        .param("userId", "noUser")
                        .param("password", "1234"))
                .andExpect(handler().handlerType(MemberController.class))
                .andExpect(handler().methodName("postLogin"))
                .andExpect(view().name("/member/login"));
    }


    @Test
    void registerGet() throws Exception {
        mvc.perform(get("/member/register"))
                .andExpect(handler().handlerType(MemberController.class))
                .andExpect(handler().methodName("getRegister"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("/member/register"));
    }


    @Test
    void registerPost() throws Exception {
        mvc.perform(post("/member/register")
                        .param("userId", "userId")
                        .param("username", "username")
                        .param("password", "1234")
                        .param("email", "naver@naver.com"))
                .andExpect(handler().handlerType(MemberController.class))
                .andExpect(handler().methodName("postRegister"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        assertThat(memberService.isMember("userId", "1234")).isTrue();
    }

    @DisplayName("로그인을 안하고 페이지에 접근 시 로그인 창으로")
    @Test
    void checkLogin() throws Exception {
        mvc.perform(get("/anywhere"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/member/login"));
    }
}
