package com.besp.likebesp1.entity;

import com.besp.likebesp1.common.BaseDto;
import lombok.Getter;

@Getter
public class BoardDto extends BaseDto {
    private Long boardId;
    private String boardName;
    private String createdDate;
}
