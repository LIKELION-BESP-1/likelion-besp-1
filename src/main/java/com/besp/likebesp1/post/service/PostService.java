package com.besp.likebesp1.post.service;

import com.besp.likebesp1.post.entity.PostDto;

import java.util.List;

public interface PostService {
    long insert(PostDto dto, long boardId);

    List<PostDto> getList(PostDto dto);

    PostDto getPost(long postId, long boardId);

    void updatePost(PostDto postDto);

    void deletePost(long postId, long boardId);

    boolean checkPostOwner(String memberId, long boardId, long postId);

}
