package com.ppyong.sample.member.domain;

import com.ppyong.sample.member.infra.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public boolean checkDuplicatedLoginId(String loginId){
        Member member = memberRepository.findByLoginId(loginId);
        if(member != null)
            return false;
        return true;
    }
}
