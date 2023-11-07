package com.besp.likebesp1.post.service;

import com.besp.likebesp1.member.dto.MemberDto;
import com.besp.likebesp1.member.repository.MemberRepository;
import com.besp.likebesp1.post.entity.PostDto;
import com.besp.likebesp1.post.repository.PostRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("postService")
@Transactional
public class PostServiceImpl implements PostService {
    @Resource(name = "postRepository")
    PostRepository postRepository;

    @Resource(name="memberRepository")
    private MemberRepository memberRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public long insert(PostDto dto, long boardId) {
        dto.setBoardId(boardId);  // 게시판 아이디 설정
        return postRepository.insert(dto);
    }

    @Override
    public List<PostDto> getList(PostDto postDto) {
        List<PostDto> postList = postRepository.getList(postDto);
        for (PostDto post : postList) {  // 각 게시글마다 작성자의 정보 가져오기
            MemberDto member = memberRepository.findByMemberId(Long.parseLong(post.getMemberId()));  // 작성자의 정보 가져오기
            post.setUsername(member.getUsername());  // PostDto에 작성자의 이름 설정
        }
        return postList;
    }

    @Override
    public PostDto getPost(long postId, long boardId) {
        PostDto post = postRepository.getPost(postId, boardId);
        if (post != null) {
            MemberDto member = memberRepository.findByMemberId(Long.parseLong(post.getMemberId()));  // 작성자의 정보 가져오기
            post.setUsername(member.getUsername());
        }
        return post;
    }

    @Override
    public void updatePost(PostDto postDto) {
        postRepository.update(postDto);
    }

    @Override
    public void deletePost(long postId, long boardId) {
        postRepository.delete(postId, boardId);
    }

}
