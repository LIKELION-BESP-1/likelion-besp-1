package com.besp.likebesp1.entity;

import com.besp.likebesp1.common.BaseDto;
import lombok.Getter;

@Getter
public class ChatMessageDto extends BaseDto {
    private final long chatMessageId = 0L;
    private final String username = "";
    private final String content = "";
    private final String createdDate = "";
}