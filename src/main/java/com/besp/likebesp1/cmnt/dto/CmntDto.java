package com.besp.likebesp1.cmnt.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CmntDto {
    private long cmntId = 0L;
    private String content = "";
    private String createdDate = "";
    private String postId="";
    private String memberId="";
}
