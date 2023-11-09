package com.besp.likebesp1.ChatRoom.controller;

import com.besp.likebesp1.Chat.dto.ChatMessageDto;
import com.besp.likebesp1.ChatRoom.dto.ChatRoomDto;
import com.besp.likebesp1.Chat.service.ChatMessageService;
import com.besp.likebesp1.ChatRoom.service.ChatRoomService;
import com.besp.likebesp1.common.RsData;
import com.besp.likebesp1.member.dto.MemberDto;
import com.besp.likebesp1.member.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.besp.likebesp1.common.LoginUser.LOGIN_USER;

@Controller
@RequestMapping("/room")
public class ChatRoomController {
    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;
    private final MemberRepository memberRepository;

    @Autowired
    public ChatRoomController(ChatRoomService chatRoomService, ChatMessageService chatMessageService, MemberRepository memberRepository) {
        this.chatRoomService = chatRoomService;
        this.chatMessageService = chatMessageService;
        this.memberRepository = memberRepository;
    }

    @GetMapping("/{roomId}")
    public String joinRoom(@PathVariable(required = false) Long roomId, Model model, HttpServletRequest request) {
        if (roomId == null) {
            return "redirect:/room/roomList";
        }

        Optional<ChatRoomDto> chatRoom = chatRoomService.findByRoomId(roomId);
        if (chatRoom.isPresent()) {
            List<ChatMessageDto> chatList = chatMessageService.getChatMessagesByRoomId(roomId);

            // 세션에서 memberId 가져오기
            RsData<String> sessionResult = checkSession(request);
            if (sessionResult.isSuccess()) {
                String memberId = sessionResult.getData();

                // MemberRepository를 사용하여 memberId에 해당하는 MemberDto 가져오기
                MemberDto member = memberRepository.findByMemberId(Long.valueOf(memberId));

                model.addAttribute("memberId", memberId);
                model.addAttribute("memberName", member.getUsername());
            }

            model.addAttribute("roomId", roomId);
            model.addAttribute("chatList", chatList);
            return "chat/room";
        } else {
            return "redirect:/room/roomList";
        }
    }

    @PostMapping("/create")
    public String createRoom(@ModelAttribute ChatRoomDto dto) {
        chatRoomService.createChatRoom(dto);
        return "redirect:/room/roomList";
    }

    @GetMapping("/roomList")
    public String roomList(Model model) {
        List<ChatRoomDto> roomList = chatRoomService.getAllChatRooms();
        model.addAttribute("roomList", roomList);
        return "chat/roomList";
    }

    @GetMapping("/roomForm")
    public String roomForm() {
        return "chat/roomForm";
    }

    private RsData<String> checkSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return RsData.of("F-1", "세션이 만료되었습니다.");
        }

        long memberId = (long) session.getAttribute(LOGIN_USER.name());
        return RsData.successOf(String.valueOf(memberId));
    }
}

