package com.besp.likebesp1.cmnt.repository;

import com.besp.likebesp1.cmnt.dto.CmntDto;

import java.util.List;

public interface CmntDao {
    List<CmntDto> getList(long postId);

    void insertCmnt(CmntDto dto);

    CmntDto findById(long cmntId);
}
