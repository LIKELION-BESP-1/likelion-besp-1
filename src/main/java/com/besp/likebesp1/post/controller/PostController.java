package com.besp.likebesp1.post.controller;

import com.besp.likebesp1.post.entity.PostDto;
import com.besp.likebesp1.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/boards")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 게시판에 연결된 게시글 리스트
    @GetMapping("/{boardId}/posts")
    public String getPostsByBoard(@PathVariable long boardId, Model model) {
        PostDto postDto = new PostDto();
        postDto.setBoardId(boardId);
        List<PostDto> postList = postService.getList(postDto);
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

}
