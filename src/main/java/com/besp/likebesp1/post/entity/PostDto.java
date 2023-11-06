package com.besp.likebesp1.post.entity;

import com.besp.likebesp1.common.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto extends BaseDto {
    private long postId = 0L;
    private long boardId = 0L;
    private String postTitle = "";
    private String content = "";
    private String createdDate = "";

    public PostDto(long postId, long boardId, String postTitle, String content, String createdDate) {
        this.postId = postId;
        this.boardId = boardId;
        this.postTitle = postTitle;
        this.content = content;
        this.createdDate = createdDate;
    }

    public PostDto() {
        super();
    }
}
