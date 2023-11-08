package com.besp.likebesp1.imgBoard.controller;

import com.besp.likebesp1.common.Pager;
import com.besp.likebesp1.imgBoard.entity.ImgBoardDto;
import com.besp.likebesp1.imgBoard.repository.ImgBoardRepository;
import com.besp.likebesp1.imgBoard.service.ImgBoardService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
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
    ImgBoardService imgBoardService;

    @Autowired
    public ImgBoardController(ImgBoardService imgBoardService) {
        this.imgBoardService = imgBoardService;
    }

    @GetMapping("/imgBoard/list/{pg}")
    public String imgBoardList(Model model, ImgBoardDto dto, @PathVariable("pg") int pg) {
        String page = Pager.makePage(10, 100, pg);
        dto.setPg(pg);
        List<ImgBoardDto> list = imgBoardService.getList(dto);

        model.addAttribute("imgBoardList", list);
        model.addAttribute("page", page);
        return "/imgBoard/imgBoardList";
    }

    @GetMapping("/imgBoard/view/{id}")
    public String imgBoardView(Model model, @PathVariable("id") int id) {
        ImgBoardDto dto = new ImgBoardDto();
        dto.setImgBoardId(id);
        ImgBoardDto resultDto = imgBoardService.getView(dto);

        model.addAttribute("imgBoardView", resultDto);
        return "/imgBoard/imgBoardView";
    }

    @GetMapping("/imgBoard/upload")
    public String imgBoardUpload() {
        return "/imgBoard/imgBoardUpload";
    }

    @PostMapping("/upload")
    @ResponseBody
    public HashMap<String, Object> upload(MultipartFile file, ImgBoardDto dto)
    {
        HashMap<String, Object> resultMap = new HashMap<String, Object>();

        imgBoardService.uploadFile(file, dto);
        imgBoardService.upload(dto);

        resultMap.put("filename", dto.getFilename());
        resultMap.put("filepath", dto.getFilepath());

        return resultMap;
    }

    @GetMapping("/imgBoard/update/{id}")
    public String imgBoardUpdate(Model model, @PathVariable("id") int id) {
        ImgBoardDto dto = new ImgBoardDto();
        dto.setImgBoardId(id);
        ImgBoardDto resultDto = imgBoardService.getView(dto);

        model.addAttribute("imgBoardView", resultDto);
        return "/imgBoard/imgBoardUpdate";
    }

    @PostMapping("/update/{id}")
    @ResponseBody
    public HashMap<String, Object> update(MultipartFile file, ImgBoardDto dto, @PathVariable("id") int id)
    {
        HashMap<String, Object> resultMap = new HashMap<String, Object>();

        imgBoardService.updateFile(file, dto, id);
        imgBoardService.update(dto);

        resultMap.put("filename", dto.getFilename());
        resultMap.put("filepath", dto.getFilepath());

        return resultMap;
    }

    @GetMapping("/imgBoard/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        imgBoardService.delete(id);

        return "redirect:/imgBoard/list/0";
    }
}
