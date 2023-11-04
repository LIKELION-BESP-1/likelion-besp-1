package com.besp.likebesp1.board.controller;

import com.besp.likebesp1.board.entity.BoardDto;
import com.besp.likebesp1.board.repository.BoardRepository;
import com.besp.likebesp1.board.service.BoardService;
import com.besp.likebesp1.common.Pager;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BoardController {

    @Resource(name="boardRepository")
    BoardRepository repository;

    @Resource(name="boardService")
    BoardService boardService;

    @GetMapping("/board/list")
    public String board_list(Model model) {
        List<BoardDto> boardList = boardService.getList();
        model.addAttribute("boardList", boardList);
        return "/board/board_list";
    }

    @GetMapping("/board/list/{pg}")
    public String board_list(Model model, BoardDto dto, @PathVariable("pg")int pg)
    {
        String page = Pager.makePage(10, 100, pg);//한페이지당표출될데이터개수, 전체개수, 현재페이지
        dto.setPg(pg);

        List<BoardDto> list = repository.getList();

        model.addAttribute("boardList", list);
        model.addAttribute("page", page);////////// html로 정보를 보내자
        return "/board/board_list";
    }

    @GetMapping("/board/add")
    public String board_add() {
        return "/board/board_add";
    }

    @PostMapping("/board/add")
    @ResponseBody
    public BoardDto board_add_post(BoardDto dto) {
        boardService.insert(dto);
        return dto;
    }
}
