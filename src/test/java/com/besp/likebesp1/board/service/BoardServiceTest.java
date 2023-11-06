package com.besp.likebesp1.board.service;

import static org.junit.jupiter.api.Assertions.assertFalse;

import com.besp.likebesp1.board.entity.BoardDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootTest
public class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Test
    public void testInsertAndList() {
        BoardDto dto = new BoardDto();
        dto.setBoardName("Test Board");

        // 게시판 추가 테스트
        boardService.insert(dto);

        // 게시판 리스트 가져오기 테스트
        List<BoardDto> list = boardService.getList(dto);
        assertFalse(list.isEmpty()); // 리스트가 비어있지 않아야 함
    }
}
