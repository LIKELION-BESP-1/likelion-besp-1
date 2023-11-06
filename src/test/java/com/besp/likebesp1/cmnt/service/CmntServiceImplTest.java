package com.besp.likebesp1.cmnt.service;

import com.besp.likebesp1.cmnt.dto.CmntDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CmntServiceImplTest {

    @Autowired
    private CmntService cmntService;

    @Test
    @DisplayName("InsertCmnt는 dto를 받아 댓글을 생성한다.")
    public void cmnt1() throws Exception{

        //given
        CmntDto dto = CmntDto.builder()
                .cmntId(12345)
                .content("댓글입니다.")
                .memberId("1")
                .postId("1")
                .build();

        //when
        cmntService.insertCmnt(dto);

        //then
        CmntDto findById = cmntService.findById(dto.getCmntId());
        assertThat(findById.getContent())
                .isEqualTo("댓글입니다.");
    }
}