package com.besp.likebesp1.ChatRoom.dto;

import com.besp.likebesp1.common.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoomDto extends BaseDto {
    private Long roomId;
    private String chatRoomName;
    private String chatRoomContent;
    private String createdDate;
}
