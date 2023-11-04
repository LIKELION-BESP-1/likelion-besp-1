package com.besp.likebesp1.board.repository;

import com.besp.likebesp1.board.entity.BoardDto;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("boardRepository")
public class BoardRepositoryImpl implements BoardRepository {

    @Autowired
    SqlSessionTemplate sm; //DatabaseConfig 파일에서 만든 객체를 전달한다.

    @Override
    public List<BoardDto> getList() {
        return sm.selectList("getList");
    }

    @Override
    public BoardDto getView(long boardId) {
        return sm.selectOne("getView", boardId);
    }

    @Override
    public void insert(BoardDto dto) {
        sm.insert("insert", dto);
    }


}
