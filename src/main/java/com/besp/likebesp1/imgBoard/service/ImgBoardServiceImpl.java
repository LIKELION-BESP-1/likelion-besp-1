package com.besp.likebesp1.imgBoard.service;

import com.besp.likebesp1.imgBoard.entity.ImgBoardDto;
import com.besp.likebesp1.imgBoard.repository.ImgBoardRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("imgBoardService")
@Transactional
public class ImgBoardServiceImpl implements ImgBoardService {
    @Resource(name = "imgBoardRepository")
    ImgBoardRepository imgBoardRepository;

    public ImgBoardServiceImpl(ImgBoardRepository imgBoardRepository) {
        this.imgBoardRepository = imgBoardRepository;
    }

    @Override
    public List<ImgBoardDto> getList(ImgBoardDto dto) {
        return imgBoardRepository.getList(dto);
    }

    @Override
    public ImgBoardDto getView(ImgBoardDto dto) {
        return imgBoardRepository.getView(dto);
    }

    @Override
    public void upload(ImgBoardDto dto) {
        imgBoardRepository.upload(dto);
    }

    @Override
    public void update(ImgBoardDto dto) {
        imgBoardRepository.update(dto);
    }

    @Override
    public void delete(long id) {
        imgBoardRepository.delete(id);
    }
}
