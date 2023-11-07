package com.besp.likebesp1.ChatRoom.repository;

import com.besp.likebesp1.ChatRoom.dto.ChatRoomDto;

import java.util.List;
import java.util.Optional;

public interface ChatRoomDao {
    void insertChatRoom(ChatRoomDto chatRoom);
    List<ChatRoomDto> selectAllChatRooms();
    Optional<ChatRoomDto> findByRoomId(Long roomId);
}

