package com.besp.likebesp1.board.service;

import com.besp.likebesp1.board.entity.BoardDto;
import com.besp.likebesp1.board.repository.BoardDao;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("boardService")
public class BoardServiceImpl implements BoardService {

    @Resource(name = "boardDao")
    BoardDao boardDao;

    public BoardServiceImpl(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    @Override
    public List<BoardDto> getList() {
        return boardDao.getList();
    }

    @Override
    public BoardDto getView(long boardId) {
        return boardDao.getView(boardId);
    }

    @Override
    public void insert(BoardDto dto) {
        boardDao.insert(dto);
    }

    @Override
    public void update(BoardDto dto) {
        boardDao.update(dto);
    }

    @Override
    public void delete(long boardId) {
        boardDao.delete(boardId);
    }
}