package com.besp.likebesp1.Chat.service;

import com.besp.likebesp1.Chat.dto.ChatMessageDto;

import java.util.List;

public interface ChatMessageService {
    void saveChatMessage(ChatMessageDto chatMessage);
    List<ChatMessageDto> getChatMessagesByRoomId(Long roomId);
}

