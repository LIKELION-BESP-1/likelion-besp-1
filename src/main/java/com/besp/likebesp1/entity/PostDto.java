package com.besp.likebesp1.entity;

import com.besp.likebesp1.common.BaseDto;
import lombok.Getter;

@Getter
public class PostDto extends BaseDto {
    private Long postId;
    private String postTitle;
    private String content;
    private String createdDate;
}
