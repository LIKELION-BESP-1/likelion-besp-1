package com.besp.likebesp1.member.service;

import com.besp.likebesp1.member.dto.MemberDto;
import com.besp.likebesp1.member.repository.MemberRepository;
import com.besp.likebesp1.util.EncrpytUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository repository;

    @Transactional
    public void save(MemberDto dto) {
        String password = dto.getPassword();
        String hashPassword = EncrpytUtil.hashPassword(password);
        dto.updateHashPassword(hashPassword);
        repository.save(dto);
    }

    @Transactional(readOnly = true)
    public Long isMember(String userId, String password) {
        MemberDto findMember = repository.findByUserId(userId);
        if (findMember == null || !isEqualPassword(password, findMember.getPassword()))
            return null;
        return findMember.getMemberId();
    }

    private boolean isEqualPassword(String password, String memberPassword) {
        return memberPassword.equals(EncrpytUtil.hashPassword(password));
    }


    @Transactional(readOnly = true)
    public boolean canMemberBeRegistered(MemberDto dto) {
        if (hasRequiredMemberInfo(dto))
            return false;

        MemberDto findUser = repository.findByUserId(dto.getUserId());
        return findUser == null;
    }

    private boolean hasRequiredMemberInfo(MemberDto dto) {
        return !StringUtils.hasText(dto.getPassword()) || !StringUtils.hasText(dto.getUsername());
    }
}
