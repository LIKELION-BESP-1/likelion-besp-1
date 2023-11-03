package com.besp.likebesp1.member.repository;

import com.besp.likebesp1.entity.MemberDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository repository;

    @Test
    void findOne() {
        MemberDto member = repository.findMember(1);
        System.out.println(member);
    }
}