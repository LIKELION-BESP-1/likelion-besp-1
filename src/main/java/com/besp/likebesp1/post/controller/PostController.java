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

    private boolean canUpdate(long postId, long boardId, String memberId) {
        PostDto post = postService.getPost(postId, boardId);
        return post.getMemberId().equals(memberId);
    }

    private PostDto getVerifiedPost(long boardId, long postId, HttpServletRequest request) {
        RsData<String> sessionCheckResult = checkSession(request);
        if (!sessionCheckResult.isSuccess()) {
            return null;
        }

        String memberId = sessionCheckResult.getData();

        if (!canUpdate(postId, boardId, memberId)) {
            return null;
        }

        return postService.getPost(postId, boardId);
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
    public String getPostDetail(@PathVariable long boardId, @PathVariable long postId, Model model, HttpServletRequest request) {
        PostDto post = postService.getPost(postId, boardId);
        List<CmntDto> cmntList = cmntService.getCmntsByPostId(postId);  // 댓글 목록 가져오기

        // 세션에서 현재 사용자 아이디 가져오기
        RsData<String> sessionCheckResult = checkSession(request);
        String currentUserId = sessionCheckResult.getData();

        // 현재 사용자가 게시글 작성자인지 확인
        boolean isAuthor = post.getMemberId().equals(currentUserId);
        model.addAttribute("isAuthor", isAuthor);

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
    @PatchMapping("/{boardId}/posts/{postId}")
    @ResponseBody
    public RsData<String> updatePost(String postTitle, String content, @PathVariable long boardId, @PathVariable long postId, HttpServletRequest request) {
        PostDto postDto = getVerifiedPost(boardId, postId, request);

        if (postDto == null) {
            return RsData.of("F-2", "잘못된 로그인 정보입니다.");
        }

        postDto.setPostTitle(postTitle);
        postDto.setContent(content);
        postService.updatePost(postDto);

        return RsData.of("S-1", "게시글이 성공적으로 수정되었습니다.");
    }

    // 게시글 삭제
    @DeleteMapping("/{boardId}/posts/{postId}")
    @ResponseBody
    public RsData<String> deletePost(@PathVariable long boardId, @PathVariable long postId, HttpServletRequest request) {
        PostDto postDto = getVerifiedPost(boardId, postId, request);

        if (postDto == null) {
            return RsData.of("F-2", "잘못된 로그인 정보입니다.");
        }

        postService.deletePost(postId, boardId);

        return RsData.of("S-1", "게시글이 성공적으로 삭제되었습니다.");
    }
}
