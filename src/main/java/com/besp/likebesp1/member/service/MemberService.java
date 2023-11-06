package com.besp.likebesp1.member.service;

import com.besp.likebesp1.member.dto.MemberDto;
import com.besp.likebesp1.member.repository.MemberRepository;
import com.besp.likebesp1.util.EncrpytUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
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

    public Long isMember(String userId, String password) {
        MemberDto findMember = repository.findByUserId(userId);
        if (findMember == null || !isEqualPassword(password, findMember.getPassword()))
            return null;
        return findMember.getMemberId();
    }

    private boolean isEqualPassword(String password, String memberPassword) {
        return memberPassword.equals(EncrpytUtil.hashPassword(password));
    }


}
