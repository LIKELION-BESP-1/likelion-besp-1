package com.besp.likebesp1.post.repository;

import com.besp.likebesp1.board.entity.BoardDto;
import com.besp.likebesp1.post.entity.PostDto;

import java.util.List;

public interface PostRepository {
    void insert(PostDto postDto);

    List<PostDto> getList(PostDto dto);

    PostDto getPost(long postId, long boardId);
}
