package com.besp.likebesp1.board.service;

import com.besp.likebesp1.board.entity.BoardDto;
import com.besp.likebesp1.board.repository.BoardRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("boardService")
public class BoardServiceImpl implements BoardService {

    @Resource(name = "boardDao")
    BoardRepository boardRepository;

    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public List<BoardDto> getList() {
        return boardRepository.getList();
    }

    @Override
    public BoardDto getView(long boardId) {
        return boardRepository.getView(boardId);
    }

    @Override
    public void insert(BoardDto dto) {
        boardRepository.insert(dto);
    }

    @Override
    public void update(BoardDto dto) {
        boardRepository.update(dto);
    }

    @Override
    public void delete(long boardId) {
        boardRepository.delete(boardId);
    }
}