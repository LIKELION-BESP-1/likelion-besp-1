package com.besp.likebesp1.cmnt.repository;

import com.besp.likebesp1.cmnt.dto.CmntDto;
import com.besp.likebesp1.cmnt.dto.CmntInsertDto;

import java.util.List;
import java.util.Optional;

public interface CmntDao {
    List<CmntDto> getList(long postId);

    void insertCmnt(CmntInsertDto dto);

    Optional<CmntDto> findById(long cmntId);
}
