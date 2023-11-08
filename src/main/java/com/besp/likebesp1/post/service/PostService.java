package com.besp.likebesp1.post.service;

import com.besp.likebesp1.post.entity.PostDto;

import java.util.List;

public interface PostService {
    long insert(PostDto dto, long boardId);

    PostDto getPost(long postId, long boardId);

    void updatePost(PostDto postDto);

    void deletePost(long postId, long boardId);

    boolean checkPostOwner(String memberId, long boardId, long postId);

    List<PostDto> getList(PostDto postDto, int startIndex, int endIndex);

    int getTotalPosts(long boardId);
}
