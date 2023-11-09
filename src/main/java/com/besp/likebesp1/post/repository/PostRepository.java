package com.besp.likebesp1.post.repository;

import com.besp.likebesp1.post.entity.PostDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PostRepository {
    long insert(PostDto postDto);

    List<PostDto> getList(PostDto dto, int page, int pageSize);

    PostDto getPost(long postId, long boardId);

    void update(PostDto dto);

    void delete(long postId, long boardId);

    int getTotalPosts(long boardId);
}
