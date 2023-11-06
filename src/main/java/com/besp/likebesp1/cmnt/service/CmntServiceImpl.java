package com.besp.likebesp1.cmnt.service;

import com.besp.likebesp1.cmnt.dto.CmntDto;
import com.besp.likebesp1.cmnt.repository.CmntDao;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cmntService")
public class CmntServiceImpl implements CmntService{

    @Resource(name="cmntDao")
    CmntDao dao;

    //TODO: make Test

    @Override
    public List<CmntDto> getList(long postId) {
        List<CmntDto> li = dao.getList(postId);
        return li;
    }

    @Override
    public void insertCmnt(CmntDto dto) {
        dao.insertCmnt(dto);
    }

    @Override
    public CmntDto findById(long cmntId){
        CmntDto dto = dao.findById(cmntId);

        //TODO: null 처리?
        return dto;
    }
}
