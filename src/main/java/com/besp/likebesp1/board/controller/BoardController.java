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

    @Resource(name="boardService")
    BoardService boardService;

    @GetMapping("/board/list")
    public String board_list(Model model, BoardDto dto) {
        List<BoardDto> boardList = boardService.getList(dto);
        model.addAttribute("boardList", boardList);
        return "/html/boardList";
    }

    @GetMapping("/board/add")
    public String board_add() {
        return "/html/boardAddForm";
    }

    @PostMapping("/board/add")
    public String board_add_post(BoardDto dto) {
        boardService.insert(dto);
        System.out.println(dto);
        return "redirect:/board/list";
    }
}
