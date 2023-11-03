package com.besp.likebesp1.member.service;

import com.besp.likebesp1.member.dto.MemberDto;
import com.besp.likebesp1.member.repository.MemberRepository;
import com.besp.likebesp1.util.EncrpytUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository repository;

    public void save(MemberDto dto) {
        String password = dto.getPassword();
        String hashPassword = EncrpytUtil.hashPassword(password);
        dto.updateHashPassword(hashPassword);
        repository.save(dto);
    }

    public MemberDto findByMemberId(String userId) {
        return repository.findByUserId(userId);
    }


}
