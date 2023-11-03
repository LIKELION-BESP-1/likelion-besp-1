package com.besp.likebesp1.member.service;

import com.besp.likebesp1.member.dto.MemberDto;
import com.besp.likebesp1.member.repository.MemberRepository;
import com.besp.likebesp1.util.EncrpytUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @DisplayName("MemberService save and encrypt password")
    @Test
    void save() {
        //given
        MemberDto memberDto = new MemberDto("user1", "1234", "hello", "email", "myphone");
        memberService.save(memberDto);

        //when
        MemberDto findUser = memberRepository.findByUserId("user1");

        //then
        assertThat(findUser.getMemberId()).isNotNull();
        assertThat(findUser.getPassword()).isEqualTo(EncrpytUtil.hashPassword("1234"));

    }
}