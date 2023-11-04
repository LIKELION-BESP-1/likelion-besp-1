package com.besp.likebesp1.entity;

import com.besp.likebesp1.common.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageDto extends BaseDto {
    private Long chatMessageId;
    private String username;
    private String content;
    private String createdDate;
}