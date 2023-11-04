package com.besp.likebesp1.entity;

import com.besp.likebesp1.common.BaseDto;
import lombok.Getter;

@Getter
public class CommentDto extends BaseDto {
    private Long commentId;
    private String content;
    private String createdDate;
}
