package com.besp.likebesp1.imgBoard.service;

import com.besp.likebesp1.imgBoard.entity.ImgBoardDto;
import com.besp.likebesp1.imgBoard.repository.ImgBoardRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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

    //application.yml 파일에서 fileUploadPath와 domain 속성값 주입
    @Value("${fileUploadPath}")
    String  fileUploadPath;
    @Value("${domain}")
    String  domain;

    @Override
    public void saveFile(MultipartFile file, ImgBoardDto dto) {
        String filename = file.getOriginalFilename(); //업로드된 파일의 파일명 원본

        try
        {
            Path uploadPath = Paths.get(fileUploadPath);
            Path filePath = uploadPath.resolve(filename); //업로드된 파일의 저장 경로 생성
            InputStream inputStream = file.getInputStream();
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING); //파일 저장
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        dto.setFilename(filename);
        dto.setFilepath(domain + "/" + fileUploadPath + "/" + filename);
    }

    @Override
    public void uploadFile(MultipartFile file, ImgBoardDto dto) {
        saveFile(file, dto);
    }

    @Override
    public void upload(ImgBoardDto dto) {
        imgBoardRepository.upload(dto);
    }

    @Override
    public void updateFile(MultipartFile file, ImgBoardDto dto, int id) {
        saveFile(file, dto);
        dto.setImgBoardId(id);
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
