package com.besp.likebesp1.cmnt.service;

import com.besp.likebesp1.cmnt.dto.CmntDto;
import com.besp.likebesp1.cmnt.dto.CmntInsertDto;
import org.apache.ibatis.javassist.tools.rmi.ObjectNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class CmntServiceImplTest {

    @Autowired
    private CmntService cmntService;

//    @Test
//    @DisplayName("InsertCmnt는 dto를 받아 댓글을 생성한다.")
//    public void cmnt1() throws Exception{
//
//        //given
//        CmntInsertDto dto = CmntInsertDto.builder()
//                .content("댓글입니다.")
//                .memberId("1")
//                .postId("123456")
//                .build();
//
//        //when
//        cmntService.insertCmnt(dto);
//
//        //then
//        List<CmntDto> list = cmntService.getCmntsByPostId(123456);
//        CmntDto dto123456 = list.get(list.size() - 1);
//        assertThat(dto123456.getContent())
//                .isEqualTo("댓글입니다.");
//    }

//    @Test
//    @DisplayName("UpdateCmnt는 content를 받아 댓글을 업데이트한다.")
//    public void cmnt2() throws Exception{
//        //given
//        CmntInsertDto dto = CmntInsertDto.builder()
//                .content("댓글입니다.")
//                .memberId("1")
//                .postId("123456")
//                .build();
//
//        cmntService.insertCmnt(dto);
//
//        List<CmntDto> list = cmntService.getCmntsByPostId(123456);
//        CmntDto dto123456 = list.get(list.size() - 1);
//
//        long cmntId = dto123456.getCmntId();
//        String content = "수정할 내용입니다.";
//
//        //when
//        cmntService.updateCmnt(content, cmntId);
//
//        //then
//        String newContent = cmntService.findById(cmntId).getContent();
//        assertThat(newContent).isEqualTo(content);
//    }

    @Test
    @DisplayName("getCmntsByPostId는 postId로 댓글들을 가져온다.")
    public void cmnt3() throws Exception{
        //given
        CmntInsertDto dto = CmntInsertDto.builder()
                .content("댓글입니다1")
                .memberId("1")
                .postId("123455")
                .build();
        cmntService.insertCmnt(dto);

        CmntInsertDto dto2 = CmntInsertDto.builder()
                .content("댓글입니다2")
                .memberId("1")
                .postId("123455")
                .build();
        cmntService.insertCmnt(dto2);

        CmntInsertDto dto3 = CmntInsertDto.builder()
                .content("댓글입니다3")
                .memberId("1")
                .postId("123455")
                .build();
        cmntService.insertCmnt(dto3);

        //when
        List<CmntDto> cmntsByPostId = cmntService.getCmntsByPostId(123455);

        //then
        int i = 1;
        for(CmntDto cmnt: cmntsByPostId){
            assertThat(cmnt.getContent()).isEqualTo("댓글입니다"+i);
            i++;
        }
    }

    @Test
    @DisplayName("findById는 id에 해당하는 댓글이 없으면 ObjectNotFoundException을 던진다.")
    public void cmnt4() throws Exception{

        //when
        //then
        assertThatThrownBy(()-> cmntService.findById(12345))
                .isInstanceOf(ObjectNotFoundException.class);

    }
}