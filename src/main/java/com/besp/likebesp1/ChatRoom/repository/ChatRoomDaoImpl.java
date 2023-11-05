package com.besp.likebesp1.ChatRoom.repository;

import com.besp.likebesp1.ChatRoom.dto.ChatRoomDto;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("chatRoomDao")
public class ChatRoomDaoImpl implements ChatRoomDao {
    private final SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public ChatRoomDaoImpl(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    @Override
    public void insertChatRoom(ChatRoomDto chatRoom) {
        sqlSessionTemplate.insert("ChatRoom_insert", chatRoom);
    }

    @Override
    public List<ChatRoomDto> selectAllChatRooms() {
        return sqlSessionTemplate.selectList("ChatRoom_selectAll");
    }
}
