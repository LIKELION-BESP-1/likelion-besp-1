package com.besp.likebesp1.Chat.repository;

import com.besp.likebesp1.Chat.dto.ChatMessageDto;

import java.util.List;

public interface ChatMessageDao {
    void insertChatMessage(ChatMessageDto chatMessage);
    List<ChatMessageDto> selectChatMessagesByRoomId(Long roomId);
}

