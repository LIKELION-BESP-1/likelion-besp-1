package com.besp.likebesp1.entity;

import com.besp.likebesp1.common.BaseDto;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class MemberDto extends BaseDto {
    private Long memberId = 0L;
    private String password = "";
    private String username = "";
    private String email = "";
    private String phone = "";
    private String createdDate = "";
}
