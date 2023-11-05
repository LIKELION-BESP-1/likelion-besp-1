package com.besp.likebesp1.ChatRoom.controller;

import com.besp.likebesp1.Chat.dto.ChatMessageDto;
import com.besp.likebesp1.ChatRoom.dto.ChatRoomDto;
import com.besp.likebesp1.entity.RoomForm;
import com.besp.likebesp1.Chat.service.ChatMessageService;
import com.besp.likebesp1.ChatRoom.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ChatRoomController {
    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;

    @Autowired
    public ChatRoomController(ChatRoomService chatRoomService, ChatMessageService chatMessageService) {
        this.chatRoomService = chatRoomService;
        this.chatMessageService = chatMessageService;
    }

    /**
     * 채팅방 참여하기
     *
     * @param roomId 채팅방 id
     */
    @GetMapping("/{roomId}")
    public String joinRoom(@PathVariable(required = false) Long roomId, Model model) {
        List<ChatMessageDto> chatList = chatMessageService.getChatMessagesByRoomId(roomId);

        model.addAttribute("roomId", roomId);
        model.addAttribute("chatList", chatList);
        return "chat/room";
    }

    /**
     * 채팅방 등록
     *
     * @param form
     */
    @PostMapping("/room")
    public String createRoom(RoomForm form) {
        ChatRoomDto chatRoomDto = new ChatRoomDto();
        chatRoomDto.setChatRoomName(form.getName());
        // Set other properties as needed

        chatRoomService.createChatRoom(chatRoomDto);
        return "redirect:/roomList";
    }

    /**
     * 채팅방 리스트 보기
     */
    @GetMapping("/roomList")
    public String roomList(Model model) {
        List<ChatRoomDto> roomList = chatRoomService.getAllChatRooms();
        model.addAttribute("roomList", roomList);
        return "chat/roomList";
    }

    /**
     * 방만들기 폼
     */
    @GetMapping("/roomForm")
    public String roomForm() {
        return "chat/roomForm";
    }
}
