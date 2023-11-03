package com.besp.likebesp1.board.repository;

import com.besp.likebesp1.board.entity.BoardDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("boardDao")
public class BoardDaoImpl implements BoardDao{

    @Override
    public List<BoardDto> getList(BoardDto dto) {
        return null;
    }

    @Override
    public BoardDto getView(BoardDto dto) {
        return null;
    }

    @Override
    public void insert(BoardDto dto) {

    }

    @Override
    public void update(BoardDto dto) {

    }

    @Override
    public void delete(String id) {

    }
}
