package com.besp.likebesp1.controller;

import com.besp.likebesp1.entity.ChatMessageDto;
import com.besp.likebesp1.entity.ChatRoomDto;
import com.besp.likebesp1.entity.RoomForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RoomController {

    /**
     * 채팅방 참여하기
     * @param roomId 채팅방 id
     */
    @GetMapping("/{roomId}")
    public String joinRoom(@PathVariable(required = false) Long roomId, Model model) {
        // ChatService를 사용하지 않고 임시 데이터를 생성
        List<ChatMessageDto> chatList = createDummyChatData(roomId);

        model.addAttribute("roomId", roomId);
        model.addAttribute("chatList", chatList);
        return "chat/room";
    }

    /**
     * 채팅방 등록
     * @param form
     */
    @PostMapping("/room")
    public String createRoom(RoomForm form) {
        // ChatService를 사용하지 않고 임시 데이터를 생성
        createDummyRoomData(form.getName());
        return "redirect:/roomList";
    }

    /**
     * 채팅방 리스트 보기
     */
    @GetMapping("/roomList")
    public String roomList(Model model) {
        // ChatService를 사용하지 않고 임시 데이터를 생성
        List<ChatRoomDto> roomList = createDummyRoomList();
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

    // ChatService를 대신할 임시 데이터 생성 메서드들
    private List<ChatMessageDto> createDummyChatData(Long roomId) {
        // 여기에서 임시 데이터 생성 로직을 작성
        return new ArrayList<>();
    }

    private void createDummyRoomData(String roomName) {
        // 여기에서 임시 데이터 생성 로직을 작성
    }

    private List<ChatRoomDto> createDummyRoomList() {
        // 여기에서 임시 데이터 생성 로직을 작성
        return new ArrayList<>();
    }
}

