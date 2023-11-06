package com.besp.likebesp1.board.repository;

import com.besp.likebesp1.board.entity.BoardDto;

import java.util.List;

public interface BoardRepository {

    List<BoardDto> getList(BoardDto dto);

    BoardDto getView(long boardId);

    void insert(BoardDto dto); // 새로운 게시판 추가
    //void update(BoardDto dto); // 게시판 정보 업데이트
    //void delete(long boardId); // 특정 게시판 삭제


}