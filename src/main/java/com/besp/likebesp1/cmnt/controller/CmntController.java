package com.besp.likebesp1.cmnt.controller;

import com.besp.likebesp1.cmnt.dto.CmntDto;
import com.besp.likebesp1.cmnt.dto.CmntInsertDto;
import com.besp.likebesp1.cmnt.service.CmntService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CmntController {

    @Resource(name="cmntService")
    CmntService service;

    @GetMapping("/cmnt/{postId}")
    public String getCmnt(Model model, @PathVariable("postId")long postId){
        List<CmntDto> list = service.getCmntsByPostId(postId);

        model.addAttribute("cmntList", list);
        return "cmntTest";
    }

    @PostMapping("/cmnt/{postId}")
    public void insertCmnt(CmntInsertDto dto){
        service.insertCmnt(dto);
    }
}
