package com.besp.likebesp1.board.service;

import com.besp.likebesp1.board.entity.BoardDto;

import java.util.List;

public interface BoardService {
    List<BoardDto> getList();
    BoardDto getView(long boardId);
    void insert(BoardDto dto);
    void update(BoardDto dto);
    void delete(long boardId);
}
