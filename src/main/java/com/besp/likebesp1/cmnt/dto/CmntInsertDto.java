package com.besp.likebesp1.cmnt.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CmntInsertDto {
    private String content = "";
    private String createdDate = "";
    private String postId="";
    private String memberId="";
}
