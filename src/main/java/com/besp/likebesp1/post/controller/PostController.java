package com.besp.likebesp1.post.controller;

import com.besp.likebesp1.board.entity.BoardDto;
import com.besp.likebesp1.board.service.BoardService;
import com.besp.likebesp1.cmnt.dto.CmntDto;
import com.besp.likebesp1.cmnt.service.CmntService;
import com.besp.likebesp1.common.RsData;
import com.besp.likebesp1.post.entity.PostDto;
import com.besp.likebesp1.post.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.besp.likebesp1.common.LoginUser.LOGIN_USER;

@Controller
@RequestMapping("/boards")
@Slf4j
public class PostController {

    private final PostService postService;
    private final BoardService boardService;
    private final CmntService cmntService;
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    @Autowired
    public PostController(PostService postService, BoardService boardService, CmntService cmntService) {
        this.postService = postService;
        this.boardService = boardService;
        this.cmntService = cmntService;
    }

    private RsData<String> checkSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return RsData.of("F-1", "세션이 만료되었습니다.");
        }

        long memberId = (long) session.getAttribute(LOGIN_USER.name());
        return RsData.successOf(String.valueOf(memberId));
    }

    // 게시판에 연결된 게시글 리스트
    @GetMapping("/{boardId}/posts")
    public String getPostsByBoard(@PathVariable long boardId, Model model) {
        PostDto postDto = new PostDto();
        postDto.setBoardId(boardId);
        List<PostDto> postList = postService.getList(postDto);

        // 게시판 정보를 가져와 모델에 추가
        BoardDto board = boardService.getBoard(boardId);
        model.addAttribute("board", board);

        model.addAttribute("postList", postList);
        return "/post/postList";
    }

    // 게시글 상세 페이지
    @GetMapping("/{boardId}/posts/{postId}")
    public String getPostDetail(@PathVariable long boardId, @PathVariable long postId, Model model) {
        PostDto post = postService.getPost(postId, boardId);
        List<CmntDto> cmntList = cmntService.getCmntsByPostId(postId);  // 댓글 목록 가져오기
        model.addAttribute("post", post);
        model.addAttribute("cmntList", cmntList);  // 댓글 목록을 Model에 추가
        return "/post/postDetail";
    }

    // 게시글 작성 페이지
    @GetMapping("/{boardId}/posts/new")
    public String getWritePostPage(@PathVariable long boardId, Model model) {
        model.addAttribute("boardId", boardId);
        model.addAttribute("post", new PostDto());
        return "/post/postAddForm";
    }

    @PostMapping("/{boardId}/posts/new")
    public String writePost(@PathVariable long boardId, @ModelAttribute PostDto postDto, HttpServletRequest request) {
        RsData<String> sessionCheckResult = checkSession(request);
        if (!sessionCheckResult.isSuccess()) {
            return "redirect:/login";  // 로그인 세션이 없는 경우, 로그인 페이지로 리다이렉트
        }

        String memberId = sessionCheckResult.getData();
        postDto.setMemberId(memberId);  // 세션에서 받아온 로그인 아이디를 PostDto에 설정
        long postId = postService.insert(postDto, boardId);  // 삽입된 게시글의 id를 반환받음
        return "redirect:/boards/" + boardId + "/posts/" + postId;  // 게시글 작성 후 해당 게시글의 상세 페이지로 리다이렉트
    }

    // 게시글 수정
    @GetMapping("/{boardId}/posts/{postId}/edit")
    public String editPostForm(@PathVariable long boardId, @PathVariable long postId, Model model) {
        PostDto postDto = postService.getPost(boardId, postId);
        if (postDto == null) {
            logger.error("No post found with boardId: " + boardId + ", postId: " + postId);
        } else {
            model.addAttribute("post", postDto);
            // 모델에 post 객체가 추가되었음을 확인하는 로그
            logger.info("Post added to model: " + postDto);
        }
        return "/post/postEdit";
    }


    @PostMapping("/{boardId}/posts/{postId}/edit")
    public String editPost(@PathVariable long boardId, @PathVariable long postId, @ModelAttribute PostDto postDto, HttpServletRequest request) {
        RsData<String> sessionCheckResult = checkSession(request);
        if (!sessionCheckResult.isSuccess()) {
            return "redirect:/login";  // 로그인 세션이 없는 경우, 로그인 페이지로 리다이렉트
        }

        String memberId = sessionCheckResult.getData();
        PostDto originalPost = postService.getPost(boardId, postId);
        if (!postService.checkPostOwner(memberId, boardId, postId)) {
            return "redirect:/boards/" + boardId + "/posts/" + postId;  // 게시글 작성자와 로그인 사용자가 다른 경우, 게시글 상세 페이지로 리다이렉트
        }

        postDto.setBoardId(boardId);
        postDto.setPostId(postId);
        postService.updatePost(postDto);
        return "redirect:/boards/{boardId}/posts/{postId}";
    }

    // 게시글 삭제
    @DeleteMapping("/{boardId}/posts/{postId}")
    public String deletePost(@PathVariable long boardId, @PathVariable long postId, HttpServletRequest request) {
        RsData<String> sessionCheckResult = checkSession(request);
        if (!sessionCheckResult.isSuccess()) {
            return "redirect:/login";  // 로그인 세션이 없는 경우, 로그인 페이지로 리다이렉트
        }

        String memberId = sessionCheckResult.getData();
        PostDto originalPost = postService.getPost(boardId, postId);
        if (!originalPost.getMemberId().equals(memberId)) {
            return "redirect:/boards/" + boardId + "/posts/" + postId;  // 게시글 작성자와 로그인 사용자가 다른 경우, 게시글 상세 페이지로 리다이렉트
        }

        postService.deletePost(postId, boardId);
        return "redirect:/boards/" + boardId + "/posts";
    }


}
