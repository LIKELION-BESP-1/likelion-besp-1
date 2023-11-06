package com.besp.likebesp1.cmnt.controller;

import com.besp.likebesp1.cmnt.dto.CmntDto;
import com.besp.likebesp1.cmnt.dto.CmntInsertDto;
import com.besp.likebesp1.cmnt.service.CmntService;
import com.besp.likebesp1.common.RsData;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.besp.likebesp1.common.LoginUser.LOGIN_USER;

@Controller
public class CmntController {

    @Resource(name="cmntService")
    CmntService service;

    @GetMapping("/cmnt/{postId}")
    public String getCmnt(Model model, @PathVariable("postId")long postId){
        List<CmntDto> list = service.getCmntsByPostId(postId);

        model.addAttribute("cmntList", list);
        return "cmntTest";
    }

    @PostMapping("/cmnt/{postId}")
    @ResponseBody
    public RsData<String> insertCmnt(CmntInsertDto dto, HttpServletRequest request){

        HttpSession session = request.getSession(false);
        if (session == null) {
            return RsData.of("F-1", "세션이 만료되었습니다.");
        }

        // 세션에서 로그인한 사용자의 정보(memberId)를 가져옵니다.
        long memberId = (long) session.getAttribute(LOGIN_USER.name());

        dto.setMemberId(String.valueOf(memberId));
        service.insertCmnt(dto);

        return RsData.of("S-1", "댓글이 성공적으로 작성되었습니다.", dto.getContent());
    }

    @PatchMapping("/cmnt/{postId}/{cmntId}")
    @ResponseBody
    public RsData<String> updateCmnt(String content, @PathVariable long postId, @PathVariable long cmntId, HttpServletRequest request) throws ObjectNotFoundException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return RsData.of("F-1", "세션이 만료되었습니다.");
        }

        long memberId = (long) session.getAttribute(LOGIN_USER.name());

        if(!canUpdate(cmntId, memberId)){
            return RsData.of("F-2", "잘못된 로그인 정보입니다.");
        }
        service.updateCmnt(content, memberId, cmntId);

        return RsData.of("S-1", "댓글이 성공적으로 수정되었습니다.");
    }

    private boolean canUpdate(long cmntId, long memberId) throws ObjectNotFoundException {
        CmntDto findById = service.findById(cmntId);
        return findById.getMemberId().equals(String.valueOf(memberId));
    }
}
