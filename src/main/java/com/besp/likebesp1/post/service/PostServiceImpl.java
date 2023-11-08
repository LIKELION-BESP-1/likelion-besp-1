package com.besp.likebesp1.post.service;

import com.besp.likebesp1.member.dto.MemberDto;
import com.besp.likebesp1.member.repository.MemberRepository;
import com.besp.likebesp1.post.entity.PostDto;
import com.besp.likebesp1.post.repository.PostRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("postService")
@Transactional
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, MemberRepository memberRepository) {
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public long insert(PostDto dto, long boardId) {
        dto.setBoardId(boardId);  // 게시판 아이디 설정
        return postRepository.insert(dto);
    }

    @Override
    public List<PostDto> getList(PostDto postDto) {
        List<PostDto> postList = postRepository.getList(postDto);
        for (PostDto post : postList) {
            attachAuthorInfo(post);
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

    // 게시글 소유자 확인 메소드
    public boolean checkPostOwner(String memberId, long boardId, long postId) {
        PostDto originalPost = getPost(boardId, postId);
        return originalPost.getMemberId().equals(memberId);
    }

    // 작성자 정보 설정 메소드
    private void attachAuthorInfo(PostDto post) {
        MemberDto member = memberRepository.findByMemberId(Long.parseLong(post.getMemberId()));  // 작성자의 정보 가져오기
        post.setUsername(member.getUsername());
    }

}
