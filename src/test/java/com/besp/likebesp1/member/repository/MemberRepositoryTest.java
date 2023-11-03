package com.besp.likebesp1.member.repository;

import com.besp.likebesp1.member.dto.MemberDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository repository;

    @DisplayName("member 저장 및 조회")
    @Test
    void insertMember() {
        //given
        MemberDto memberDto = new MemberDto("user1", "1234", "hello", "email", "myphone");
        repository.save(memberDto);

        //when
        MemberDto member = repository.findMember("user1");

        //then
        assertThat(member.getUserId()).isEqualTo("user1");
        assertThat(member.getPassword()).isEqualTo("1234");
        assertThat(member.getCreatedDate()).isBeforeOrEqualTo(LocalDateTime.now());

    }
}