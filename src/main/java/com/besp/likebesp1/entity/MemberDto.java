package com.besp.likebesp1.entity;

import com.besp.likebesp1.common.BaseDto;
import lombok.Getter;

@Getter
public class MemberDto extends BaseDto {
    private Long memberId = 0L;
    private String userId = "";
    private String password = "";
    private String username = "";
    private String email = "";
    private String phone = "";
    private String createdDate = "";
}
