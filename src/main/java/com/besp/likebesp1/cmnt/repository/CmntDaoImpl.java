package com.besp.likebesp1.cmnt.repository;

import com.besp.likebesp1.cmnt.dto.CmntDto;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("cmntDao")
public class CmntDaoImpl implements CmntDao{

    @Autowired
    SqlSessionTemplate sm;


    @Override
    public List<CmntDto> getList(long postId) {
        List<CmntDto> cmntGetList = sm.selectList("Cmnt_getList", postId);
        return cmntGetList;
    }

    @Override
    public void insertCmnt(CmntDto dto) {
        sm.insert("Cmnt_insert", dto);
    }

    @Override
    public Optional<CmntDto> findById(long cmntId){
        CmntDto dto = sm.selectOne("Cmnt_findByCmntId", cmntId);
        return Optional.of(dto);
    }
}
