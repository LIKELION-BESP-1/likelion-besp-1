package com.besp.likebesp1.board.service;

import com.besp.likebesp1.board.entity.BoardDto;
import com.besp.likebesp1.board.repository.BoardRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("boardService")
@Transactional
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public List<BoardDto> getList(BoardDto dto) {
        return boardRepository.getList(dto);
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
    public BoardDto getBoard(long boardId) {
        return boardRepository.getBoard(boardId);
    }
}