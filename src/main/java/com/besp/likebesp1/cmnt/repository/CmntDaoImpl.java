package com.besp.likebesp1.cmnt.repository;

import com.besp.likebesp1.cmnt.dto.CmntDto;
import com.besp.likebesp1.cmnt.dto.CmntInsertDto;
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
    public List<CmntDto> getCmntsByPostId(long postId) {
        List<CmntDto> cmntGetList = sm.selectList("Cmnt_getCmntsByPostId", postId);
        return cmntGetList;
    }

    @Override
    public void insertCmnt(CmntInsertDto dto) {
        sm.insert("Cmnt_insert", dto);
    }

    @Override
    public Optional<CmntDto> findById(long cmntId){
        CmntDto dto = sm.selectOne("Cmnt_findByCmntId", cmntId);
        return Optional.of(dto);
    }
}
