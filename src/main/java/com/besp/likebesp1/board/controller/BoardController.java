package com.besp.likebesp1.board.controller;

import com.besp.likebesp1.board.entity.BoardDto;
import com.besp.likebesp1.board.repository.BoardRepository;
import com.besp.likebesp1.board.service.BoardService;
import com.besp.likebesp1.common.Pager;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BoardController {

    @Resource(name = "boardService")
    BoardService boardService;

    @GetMapping("/board/list")
    public String board_list(Model model, BoardDto dto,
                             @RequestParam(value = "page", defaultValue = "0") int page,
                             @RequestParam(value = "size", defaultValue = "10") int size) {
        int totalPosts = boardService.getTotalPosts(dto);

        // 페이징 처리를 위한 Pager 클래스의 makePage 메서드를 호출하여 HTML 태그 생성
        String pagingTags = Pager.makePage(size, totalPosts, page);

        // 페이징에 필요한 시작 인덱스와 가져올 게시물 수 계산
        int startIndex = page * size;
        int endIndex = Math.min((page + 1) * size, totalPosts);

        // 게시물 리스트 가져오기
        List<BoardDto> boardList = boardService.getList(dto, startIndex, endIndex);

        // 모델에 데이터 추가
        model.addAttribute("boardList", boardList);
        model.addAttribute("pagingTags", pagingTags);

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
