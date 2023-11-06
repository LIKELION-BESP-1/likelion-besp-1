package com.besp.likebesp1.cmnt.service;

import com.besp.likebesp1.cmnt.dto.CmntDto;
import com.besp.likebesp1.cmnt.dto.CmntInsertDto;
import org.apache.ibatis.javassist.tools.rmi.ObjectNotFoundException;

import java.util.List;

public interface CmntService {
    List<CmntDto> getList(long postId);

    void insertCmnt(CmntInsertDto dto);

    CmntDto findById(long cmntId) throws ObjectNotFoundException;
}