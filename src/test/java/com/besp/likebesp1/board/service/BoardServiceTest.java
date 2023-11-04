package com.besp.likebesp1.board.service;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.besp.likebesp1.board.entity.BoardDto;
import com.besp.likebesp1.board.repository.BoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BoardServiceTest {

    private BoardServiceImpl boardService;
    private BoardRepository boardRepository;

    @BeforeEach
    public void setup() {
        boardRepository = mock(BoardRepository.class);
        boardService = new BoardServiceImpl(boardRepository);
    }

    @Test
    @Order(1)
    @DisplayName("게시판 추가")
    public void testInsert() {
        // given
        BoardDto boardDto = new BoardDto();
        boardDto.setBoardName("자유게시판");

        // when
        boardService.insert(boardDto);

        // then
        System.out.println("게시판 추가 성공");
        verify(boardRepository, times(1)).insert(boardDto);
    }

    @Test
    @Order(2)
    @DisplayName("게시판 조회")
    public void testGetList() {
        // given
        List<BoardDto> expectedList = new ArrayList<>();
        when(boardRepository.getList()).thenReturn(expectedList);

        // when
        List<BoardDto> actualList = boardService.getList();

        // then
        System.out.println("기대한 목록 크기: " + expectedList.size());
        System.out.println("실제 목록 크기: " + actualList.size());

        assertThat(actualList).isNotNull();
        assertThat(actualList).isEqualTo(expectedList);
    }
}