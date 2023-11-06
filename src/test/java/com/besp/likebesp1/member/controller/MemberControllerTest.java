package com.besp.likebesp1.member.controller;

import com.besp.likebesp1.common.LoginUser;
import com.besp.likebesp1.member.dto.MemberDto;
import com.besp.likebesp1.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
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

    @DisplayName("로그인")
    @Nested
    class Login {
        @DisplayName("로그인 get 요청")
        @Test
        void loginGet() throws Exception {

            mvc.perform(get("/member/login"))
                    .andExpect(handler().handlerType(MemberController.class))
                    .andExpect(handler().methodName("getLogin"))
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(view().name("/member/login"));
        }

        @DisplayName("로그인 성공")
        @Test
        void successLogin() throws Exception {
            MemberDto memberDto = new MemberDto("user5", "1234", "hello", "email", "myphone");
            memberService.save(memberDto);

            mvc.perform(post("/member/login")
                            .param("userId", "user5")
                            .param("password", "1234"))
                    .andExpect(handler().handlerType(MemberController.class))
                    .andExpect(handler().methodName("postLogin"))
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(content().string("/"));

        }

        @DisplayName("로그인 실패")
        @Test
        void failLogin() throws Exception {

            mvc.perform(post("/member/login")
                            .param("userId", "noUser")
                            .param("password", "1234"))
                    .andExpect(handler().handlerType(MemberController.class))
                    .andExpect(handler().methodName("postLogin"))
                    .andExpect(status().is4xxClientError());


        }
    }

    @DisplayName("등록")
    @Nested
    class register {
        @DisplayName("등록 페이지 요청")
        @Test
        void registerGet() throws Exception {
            mvc.perform(get("/member/register"))
                    .andExpect(handler().handlerType(MemberController.class))
                    .andExpect(handler().methodName("getRegister"))
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(view().name("/member/register"));
        }

        @DisplayName("등록 성공")
        @Test
        void registerPost() throws Exception {
            mvc.perform(post("/member/register")
                            .param("userId", "userId")
                            .param("username", "username")
                            .param("password", "1234")
                            .param("email", "naver@naver.com"))
                    .andExpect(handler().handlerType(MemberController.class))
                    .andExpect(handler().methodName("postRegister"))
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(content().string("/"));

            assertThat(memberService.isMember("userId", "1234")).isNotNull();
        }
    }

    @DisplayName("접근 권한")
    @Nested
    class AccessTest {
        @DisplayName("로그인을 안하고 페이지에 접근 시 로그인 창으로")
        @Test
        void checkLogin() throws Exception {
            mvc.perform(get("/anywhere"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl("/member/login"));
        }

        @DisplayName("로그아웃시 세션 없음")
        @Test
        void emptySession() throws Exception {
            HttpSession session = mvc.perform(get("/anywhere"))
                    .andReturn().getRequest().getSession();
            assertThat(session.getAttribute(LoginUser.LOGIN_USER.name())).isNull();
        }

        @DisplayName("로그인시 세션 있음")
        @Test
        void notEmptySession() throws Exception {
            MemberDto memberDto = new MemberDto("user5", "1234", "hello", "email", "myphone");
            memberService.save(memberDto);

            MvcResult mvcResult = mvc.perform(post("/member/login")
                            .param("userId", "user5")
                            .param("password", "1234"))
                    .andReturn();


            HttpSession session = mvcResult.getRequest().getSession();
            assertThat(session.getAttribute(LoginUser.LOGIN_USER.name())).isNotNull();
        }

        @DisplayName("로그인 후 로그인 페이지 접근 불가")
        @Test
        void cannotAccessToLogin() throws Exception {


            MockHttpSession mockSession = new MockHttpSession();
            mockSession.setAttribute(LoginUser.LOGIN_USER.name(), "login");

            mvc.perform(get("/member/register").session(mockSession))
                    .andExpect(redirectedUrl("/"));
        }
    }


}
