package com.besp.likebesp1.post.service;

import com.besp.likebesp1.post.entity.PostDto;
import com.besp.likebesp1.post.repository.PostRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("postService")
public class PostServiceImpl implements PostService {
    @Resource(name = "postRepository")
    PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void insert(PostDto dto, long boardId) {
        dto.setBoardId(boardId);  // 게시판 아이디 설정
        postRepository.insert(dto);
    }

    @Override
    public List<PostDto> getList(PostDto dto) {
        return postRepository.getList(dto);
    }

    @Override
    public PostDto getPost(long postId, long boardId) {
        return postRepository.getPost(postId, boardId);
    }
}
