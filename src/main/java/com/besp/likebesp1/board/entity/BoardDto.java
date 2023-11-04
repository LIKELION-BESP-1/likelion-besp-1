package com.besp.likebesp1.board.entity;

import com.besp.likebesp1.common.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BoardDto extends BaseDto {

    private long boardId = 0L;
    private String boardName ="";
    private String createdDate = LocalDate.now().toString();

    public BoardDto() {
        super();
    }

    public BoardDto(long boardId, String boardName, String createdDate) {
        this.boardId = boardId;
        this.boardName = boardName;
        this.createdDate = createdDate;
    }
}