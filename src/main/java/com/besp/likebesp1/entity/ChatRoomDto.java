package com.besp.likebesp1.entity;

import com.besp.likebesp1.common.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoomDto extends BaseDto {
    private Long chatRoomId;
    private String chatRoomName;
    private String chatRoomContent;
    private String createdDate;
}
