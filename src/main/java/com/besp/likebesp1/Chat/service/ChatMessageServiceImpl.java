package com.besp.likebesp1.Chat.service;

import com.besp.likebesp1.Chat.repository.ChatMessageDao;
import com.besp.likebesp1.Chat.dto.ChatMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("ChatMessageService")
@Transactional
public class ChatMessageServiceImpl implements ChatMessageService {
    private final ChatMessageDao chatMessageDao;

    @Autowired
    public ChatMessageServiceImpl(ChatMessageDao chatMessageDao) {
        this.chatMessageDao = chatMessageDao;
    }

    @Override
    public void saveChatMessage(ChatMessageDto chatMessageDto, Long roomId) {
        chatMessageDto.setRoomId(roomId);
        chatMessageDao.insertChatMessage(chatMessageDto);
    }

    @Override
    public List<ChatMessageDto> getChatMessagesByRoomId(Long roomId) {
        return chatMessageDao.selectChatMessagesByRoomId(roomId);
    }
}

