package com.besp.likebesp1.board.controller;

import com.besp.likebesp1.board.entity.BoardDto;
import com.besp.likebesp1.board.repository.BoardDao;
import com.besp.likebesp1.common.Pager;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class BoardController {

    @Resource(name="boardDao")
    BoardDao dao;

    @GetMapping("/board/list/{pg}")
    public String board_list(Model model, BoardDto dto, @PathVariable("pg")int pg)
    {
        String page = Pager.makePage(10, 100, pg);//한페이지당표출될데이터개수, 전체개수, 현재페이지
        dto.setPg(pg);

        List<BoardDto> list = dao.getList(dto);

        model.addAttribute("boardList", list);
        model.addAttribute("page", page);////////// html로 정보를 보내자
        return "/board/board_list";
    }

}
