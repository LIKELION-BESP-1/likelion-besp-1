package com.besp.likebesp1.ChatRoom.service;

import com.besp.likebesp1.ChatRoom.dto.ChatRoomDto;
import com.besp.likebesp1.ChatRoom.repository.ChatRoomDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {
    private final ChatRoomDao chatRoomDao;

    @Autowired
    public ChatRoomServiceImpl(ChatRoomDao chatRoomDao) {
        this.chatRoomDao = chatRoomDao;
    }

    @Override
    public void createChatRoom(ChatRoomDto chatRoom) {
        chatRoomDao.insertChatRoom(chatRoom);
    }

    @Override
    public List<ChatRoomDto> getAllChatRooms() {
        return chatRoomDao.selectAllChatRooms();
    }
}

