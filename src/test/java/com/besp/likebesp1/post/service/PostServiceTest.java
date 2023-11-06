package com.besp.likebesp1.post.service;

import com.besp.likebesp1.board.entity.BoardDto;
import com.besp.likebesp1.board.service.BoardService;
import com.besp.likebesp1.post.entity.PostDto;
import com.besp.likebesp1.post.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
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
    void getList() {
    }

    @Test
    void getView() {
    }
}