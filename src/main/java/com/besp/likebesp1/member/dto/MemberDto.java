package com.besp.likebesp1.member.dto;

import com.besp.likebesp1.common.BaseDto;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
public class MemberDto extends BaseDto {
    private Long memberId;
    private String userId;
    private String password;
    private String username;
    private String email;
    private String phone;
    private LocalDateTime createdDate;

    public MemberDto(String userId, String password, String username, String email, String phone) {
        this.userId = userId;
        this.password = password;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.createdDate = LocalDateTime.now();
    }

    public void updateHashPassword(String newPassword) {
        this.password = newPassword;
    }
}
