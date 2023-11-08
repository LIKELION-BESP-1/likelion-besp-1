package com.besp.likebesp1.imgBoard.entity;

import com.besp.likebesp1.common.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImgBoardDto extends BaseDto {
    private long imgBoardId = 0L;
    private String title = "";
    private String filename = "";
    private String filepath = "";
    private String writer = "";
    private String createdDate = "";
}
