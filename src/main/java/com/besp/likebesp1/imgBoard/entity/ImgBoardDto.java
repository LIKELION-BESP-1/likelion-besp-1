package com.besp.likebesp1.imgBoard.entity;

import com.besp.likebesp1.common.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImgBoardDto extends BaseDto {
    private long imgBoardId = 0L;
    private String title = "";
    //private String imgFile = "";
    private String writer = "";
    private String createdDate = "";
}
