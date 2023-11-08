package com.besp.likebesp1.ChatRoom.service;

import com.besp.likebesp1.ChatRoom.dto.ChatRoomDto;

import java.util.List;
import java.util.Optional;

public interface ChatRoomService {
    void createChatRoom(ChatRoomDto chatRoom);
    List<ChatRoomDto> getAllChatRooms();
    Optional<ChatRoomDto> findByRoomId(Long roomId);
}

