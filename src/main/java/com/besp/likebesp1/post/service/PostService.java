package com.besp.likebesp1.post.service;

import com.besp.likebesp1.post.entity.PostDto;

import java.util.List;

public interface PostService {
    void insert(PostDto dto, long boardId);

    List<PostDto> getList(PostDto dto);

    PostDto getPost(long postId, long boardId);
}
