package com.besp.likebesp1.post.controller;

import com.besp.likebesp1.board.entity.BoardDto;
import com.besp.likebesp1.board.service.BoardService;
import com.besp.likebesp1.post.entity.PostDto;
import com.besp.likebesp1.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/boards")
public class PostController {

    private final PostService postService;
    private final BoardService boardService;
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    public PostController(PostService postService, BoardService boardService) {
        this.postService = postService;
        this.boardService = boardService;
    }

    // 게시판에 연결된 게시글 리스트
    @GetMapping("/{boardId}/posts")
    public String getPostsByBoard(@PathVariable long boardId, Model model) {
        PostDto postDto = new PostDto();
        postDto.setBoardId(boardId);
        List<PostDto> postList = postService.getList(postDto);

        // 게시판 정보를 가져와 모델에 추가
        BoardDto board = boardService.getBoard(boardId);
        model.addAttribute("board", board);

        model.addAttribute("postList", postList);
        return "/post/postList";
    }

    // 게시글 상세 페이지
    @GetMapping("/{boardId}/posts/{postId}")
    public String getPostDetail(@PathVariable long boardId, @PathVariable long postId, Model model) {
        PostDto post = postService.getPost(postId, boardId);
        model.addAttribute("post", post);
        return "/post/postDetail";
    }

    // 게시글 작성 페이지
    @GetMapping("/{boardId}/posts/new")
    public String getWritePostPage(@PathVariable long boardId, Model model) {
        model.addAttribute("boardId", boardId);
        model.addAttribute("post", new PostDto());
        return "/post/postAddForm";
    }

    @PostMapping("/{boardId}/posts/new")
    public String writePost(@PathVariable long boardId, @ModelAttribute PostDto postDto) {
        long postId = postService.insert(postDto, boardId);  // 삽입된 게시글의 id를 반환받음
        return "redirect:/boards/" + boardId + "/posts/" + postId;  // 게시글 작성 후 해당 게시글의 상세 페이지로 리다이렉트
    }

    // 게시글 수정
    @GetMapping("/{boardId}/posts/{postId}/edit")
    public String editPostForm(@PathVariable long boardId, @PathVariable long postId, Model model) {
        PostDto postDto = postService.getPost(boardId, postId);
        if (postDto == null) {
            logger.error("No post found with boardId: " + boardId + ", postId: " + postId);
        } else {
            model.addAttribute("post", postDto);
            // 모델에 post 객체가 추가되었음을 확인하는 로그
            logger.info("Post added to model: " + postDto);
        }
        return "/post/postEdit";
    }


    @PostMapping("/{boardId}/posts/{postId}/edit")
    public String editPost(@PathVariable long boardId, @PathVariable long postId, @ModelAttribute PostDto postDto) {
        postDto.setBoardId(boardId);
        postDto.setPostId(postId);
        postService.updatePost(postDto);
        return "redirect:/boards/{boardId}/posts/{postId}";
    }

    // 게시글 삭제
    @DeleteMapping("/{boardId}/posts/{postId}")
    public String deletePost(@PathVariable long boardId, @PathVariable long postId) {
        postService.deletePost(postId, boardId);
        return "redirect:/boards/" + boardId + "/posts";
    }


}
