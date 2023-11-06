package com.besp.likebesp1.cmnt.service;

import com.besp.likebesp1.cmnt.dto.CmntDto;
import com.besp.likebesp1.cmnt.dto.CmntInsertDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CmntServiceImplTest {

    @Autowired
    private CmntService cmntService;

    @Test
    @DisplayName("InsertCmnt는 dto를 받아 댓글을 생성한다.")
    public void cmnt1() throws Exception{

        //given
        CmntInsertDto dto = CmntInsertDto.builder()
                .content("댓글입니다.")
                .memberId("1")
                .postId("123456")
                .build();

        //when
        cmntService.insertCmnt(dto);

        //then
        List<CmntDto> list = cmntService.getList(123456);
        CmntDto dto123456 = list.get(list.size() - 1);
        assertThat(dto123456.getContent())
                .isEqualTo("댓글입니다.");
    }
}