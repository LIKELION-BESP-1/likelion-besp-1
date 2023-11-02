package com.besp.likebesp1.entity;

import com.besp.likebesp1.common.BaseDto;
import lombok.Getter;

@Getter
public class MemberDto extends BaseDto {
    private final Long memberId = 0L;
    private final String userId = "";
    private final String password = "";
    private final String username = "";
    private final String email = "";
    private final String phone = "";
    private final String createdDate = "";
}
