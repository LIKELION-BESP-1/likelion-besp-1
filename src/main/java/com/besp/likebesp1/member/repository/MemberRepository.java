package com.besp.likebesp1.member.repository;

import com.besp.likebesp1.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@RequiredArgsConstructor
@Repository
public class MemberRepository {

    private final SqlSessionTemplate sm;

    public MemberDto findByUserId(@Param("userId") String userId) {
        return sm.selectOne("findByUserId", Map.of("userId", userId));
    }

    public MemberDto findByMemberId(@Param("memberId") Long memberId) {
        return sm.selectOne("findByMemberId", Map.of("memberId", memberId));
    }

    public void save(MemberDto dto) {
        sm.insert("insertMember", dto);
    }

}
