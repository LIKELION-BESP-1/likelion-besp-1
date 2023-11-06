package com.besp.likebesp1.imgBoard.controller;

import com.besp.likebesp1.common.Pager;
import com.besp.likebesp1.imgBoard.entity.ImgBoardDto;
import com.besp.likebesp1.imgBoard.repository.ImgBoardRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
}
