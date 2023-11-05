package com.besp.likebesp1.ChatRoom.repository;

import com.besp.likebesp1.ChatRoom.dto.ChatRoomDto;

import java.util.List;

public interface ChatRoomDao {
    void insertChatRoom(ChatRoomDto chatRoom);
    List<ChatRoomDto> selectAllChatRooms();
}

