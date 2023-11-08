package com.besp.likebesp1.imgBoard.service;

import com.besp.likebesp1.imgBoard.entity.ImgBoardDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImgBoardService {
    List<ImgBoardDto> getList(ImgBoardDto dto);

    ImgBoardDto getView(ImgBoardDto dto);

    void uploadFile(MultipartFile file, ImgBoardDto dto);

    void upload(ImgBoardDto dto);

    void updateFile(MultipartFile file, ImgBoardDto dto, int id);

    void update(ImgBoardDto dto);

    void delete(long id);
}
