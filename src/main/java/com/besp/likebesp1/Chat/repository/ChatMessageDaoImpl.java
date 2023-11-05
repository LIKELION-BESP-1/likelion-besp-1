package com.besp.likebesp1.Chat.repository;

import com.besp.likebesp1.Chat.dto.ChatMessageDto;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("chatMessageDao")
public class ChatMessageDaoImpl implements ChatMessageDao {

    private final SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public ChatMessageDaoImpl(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    @Override
    public void insertChatMessage(ChatMessageDto chatMessage) {
        sqlSessionTemplate.insert("ChatMessage_insert", chatMessage);
    }

    @Override
    public List<ChatMessageDto> selectChatMessagesByRoomId(Long roomId) {
        return sqlSessionTemplate.selectList("ChatMessage_selectByRoomId", roomId);
    }
}


