package com.besp.likebesp1.imgBoard.controller;

import com.besp.likebesp1.common.Pager;
import com.besp.likebesp1.imgBoard.entity.ImgBoardDto;
import com.besp.likebesp1.imgBoard.repository.ImgBoardRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;

@Controller
public class ImgBoardController {
    @Resource(name = "imgBoardRepository")
    ImgBoardRepository repository;

    @GetMapping("/imgBoard/list/{pg}")
    public String imgBoardList(Model model, ImgBoardDto dto, @PathVariable("pg") int pg) {
        String page = Pager.makePage(10, 100, pg);
        dto.setPg(pg);
        List<ImgBoardDto> list = repository.getList(dto);

        model.addAttribute("imgBoardList", list);
        model.addAttribute("page", page);
        return "/imgBoard/imgBoardList";
    }

    @GetMapping("/imgBoard/view/{id}")
    public String imgBoardView(Model model, @PathVariable("id") int id) {
        ImgBoardDto dto = new ImgBoardDto();
        dto.setImgBoardId(id);
        ImgBoardDto resultDto = repository.getView(dto);

        model.addAttribute("imgBoardView", resultDto);
        return "/imgBoard/imgBoardView";
    }

    @GetMapping("/imgBoard/delete/{id}")
    public String imgBoardDelete(Model model, @PathVariable("id") int id) {
        repository.delete(id);

        return "redirect:/imgBoard/list/0";
    }

    @GetMapping("/imgBoard/upload")
    public String imgBoardUpload() {
        return "/imgBoard/imgBoardUpload";
    }

    //application.yml 파일에서 fileUploadPath와 domain 속성값 주입
    @Value("${fileUploadPath}")
    String  fileUploadPath;
    @Value("${domain}")
    String  domain;

    @PostMapping("/upload")
    @ResponseBody
    public HashMap<String, Object> upload(MultipartFile file, ImgBoardDto dto)
    {
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
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
        repository.upload(dto);

        resultMap.put("filename", filename);
        resultMap.put("filepath", domain + "/" + fileUploadPath + "/" + filename);

        return resultMap;
    }
}
