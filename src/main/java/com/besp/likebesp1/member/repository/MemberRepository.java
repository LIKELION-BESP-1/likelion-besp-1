package com.besp.likebesp1.member.repository;

import com.besp.likebesp1.entity.MemberDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@RequiredArgsConstructor
@Repository
public class MemberRepository {

    private final SqlSessionTemplate sm;

    public MemberDto findMember(@Param("memberId") int memberId) {
        return sm.selectOne("findMember", Map.of("memberId", memberId));
    }

}
