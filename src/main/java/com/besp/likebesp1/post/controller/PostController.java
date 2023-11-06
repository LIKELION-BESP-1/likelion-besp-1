package com.besp.likebesp1.post.controller;

import com.besp.likebesp1.post.entity.PostDto;
import com.besp.likebesp1.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/board/{boardId}")
    public void insert(@RequestBody PostDto postDto, @PathVariable long boardId) {
        postService.insert(postDto, boardId);
    }
}
