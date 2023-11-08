package com.besp.likebesp1.imgBoard.service;

import com.besp.likebesp1.imgBoard.entity.ImgBoardDto;

import java.util.List;

public interface ImgBoardService {
    List<ImgBoardDto> getList(ImgBoardDto dto);

    ImgBoardDto getView(ImgBoardDto dto);

    void upload(ImgBoardDto dto);

    void update(ImgBoardDto dto);

    void delete(long id);
}
