package com.besp.likebesp1.imgBoard.repository;

import com.besp.likebesp1.imgBoard.entity.ImgBoardDto;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository("imgBoardRepository")
public class ImgBoardRepositoryImpl implements ImgBoardRepository {
    private SqlSessionTemplate sm;

    @Autowired
    public ImgBoardRepositoryImpl(SqlSessionTemplate sm) {
        this.sm = sm;
    }

    @Override
    public int countPage(ImgBoardDto dto) {
        return sm.selectOne("ImgBoardPage", dto);
    }

    @Override
    public List<ImgBoardDto> getList(ImgBoardDto dto) {
        return sm.selectList("ImgBoardGetList", dto);
    }

    @Override
    public ImgBoardDto getView(ImgBoardDto dto) {
        return sm.selectOne("ImgBoardGetView", dto);
    }

    @Override
    public void upload(ImgBoardDto dto) {
        sm.insert("ImgBoardUpload", dto);
    }

    @Override
    public void update(ImgBoardDto dto) {
        sm.update("ImgBoardUpdate", dto);
    }

    @Override
    public void delete(long id) {
        sm.delete("ImgBoardDelete", id);
    }
}
