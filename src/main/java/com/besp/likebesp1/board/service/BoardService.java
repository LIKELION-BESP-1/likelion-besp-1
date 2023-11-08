package com.besp.likebesp1.board.service;

import com.besp.likebesp1.board.entity.BoardDto;

import java.util.List;

public interface BoardService {
    List<BoardDto> getList(BoardDto dto);

    void insert(BoardDto dto);

    BoardDto getBoard(long boardId);

    int getTotalPosts(BoardDto dto);

    List<BoardDto> getList(BoardDto dto, int startIndex, int endIndex);

}
