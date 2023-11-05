package com.besp.likebesp1.imgBoard.repository;

import com.besp.likebesp1.imgBoard.entity.ImgBoardDto;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("imgBoardRepository")
public class ImgBoardRepositoryImpl implements ImgBoardRepository {
    @Autowired
    SqlSessionTemplate sm;

    @Override
    public List<ImgBoardDto> getList(ImgBoardDto dto) {
        return sm.selectList("ImgBoardGetList", dto);
    }

    @Override
    public ImgBoardDto getView(ImgBoardDto dto) {
        return sm.selectOne("ImgBoardGetView", dto);
    }
}
