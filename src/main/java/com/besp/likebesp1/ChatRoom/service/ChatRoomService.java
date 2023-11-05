package com.besp.likebesp1.ChatRoom.service;

import com.besp.likebesp1.ChatRoom.dto.ChatRoomDto;

import java.util.List;

public interface ChatRoomService {
    void createChatRoom(ChatRoomDto chatRoom);
    List<ChatRoomDto> getAllChatRooms();
}

