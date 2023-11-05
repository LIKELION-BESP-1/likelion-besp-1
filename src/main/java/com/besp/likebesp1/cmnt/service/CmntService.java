package com.besp.likebesp1.cmnt.service;

import com.besp.likebesp1.cmnt.dto.CmntDto;

import java.util.List;

public interface CmntService {
    List<CmntDto> getList(long postId);

    void insertCmnt(CmntDto dto);
}
