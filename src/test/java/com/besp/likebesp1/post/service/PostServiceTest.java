package com.besp.likebesp1.post.service;

import com.besp.likebesp1.board.entity.BoardDto;
import com.besp.likebesp1.board.service.BoardService;
import com.besp.likebesp1.post.entity.PostDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class PostServiceTest {

    @Autowired
    private BoardService boardService;

    @Autowired
    private PostService postService;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("게시판 안에 게시글 작성")
    void insert() {
        // 게시판 생성
        BoardDto boardDto = new BoardDto();
        boardDto.setBoardName("자유게시판");
        boardService.insert(boardDto);

        // 게시판 리스트 가져오기
        List<BoardDto> list = boardService.getList(boardDto);

        // 게시판 ID 가져오기
        long boardId = list.get(0).getBoardId();

        // 게시판에 게시글 작성
        PostDto postDto = new PostDto();
        postDto.setPostId(1L);
        postDto.setPostTitle("내가 첫번째넹");
        postDto.setContent("안녕하세요!! 자유게시판 첫번째 게시글입니다!");
        //postDto.setCreatedDate("2023-11-06");

        postService.insert(postDto, boardId);  // 게시판 ID를 파라미터로 전달

        // 작성한 게시글 가져오기
        PostDto savedPostDto = postService.getPost(postDto.getPostId(), boardId);

        // 게시글이 잘 저장되었는지 확인
        assertEquals(postDto.getPostTitle(), savedPostDto.getPostTitle());
        assertEquals(postDto.getContent(), savedPostDto.getContent());
        //assertEquals(postDto.getCreatedDate(), savedPostDto.getCreatedDate());
    }

    @Test
    @DisplayName("게시글 수정")
    public void updatePostTest() {
        // 게시글 조회
        long boardId = 1L; // 수정하려는 게시글이 속한 게시판의 ID
        long postId = 6L; // 수정하려는 게시글의 ID
        PostDto originalPost = postService.getPost(postId, boardId);

        // 게시글 수정
        PostDto updatedPost = new PostDto();
        updatedPost.setPostId(originalPost.getPostId());
        updatedPost.setBoardId(originalPost.getBoardId());
        updatedPost.setPostTitle("수정한 제목"); // 수정할 제목
        updatedPost.setContent("수정한 내용"); // 수정할 내용
        postService.updatePost(updatedPost);

        // 수정된 게시글 조회
        PostDto postAfterUpdate = postService.getPost(postId, boardId);

        // 수정된 게시글의 제목과 내용이 정상적으로 업데이트되었는지 확인
        assertThat(postAfterUpdate.getPostTitle()).isEqualTo("수정한 제목");
        assertThat(postAfterUpdate.getContent()).isEqualTo("수정한 내용");
    }

    @Test
    @DisplayName("게시글 삭제")
    public void deletePostTest() {
        // 게시글 조회
        long boardId = 1L; // 삭제하려는 게시글이 속한 게시판의 ID
        long postId = 6L; // 삭제하려는 게시글의 ID
        PostDto originalPost = postService.getPost(postId, boardId);
        assertThat(originalPost).isNotNull();

        // 게시글 삭제
        postService.deletePost(postId, boardId);

        // 삭제된 게시글 조회
        PostDto postAfterDelete = postService.getPost(postId, boardId);

        // 삭제된 게시글이 null인지 확인
        assertThat(postAfterDelete).isNull();
    }
}