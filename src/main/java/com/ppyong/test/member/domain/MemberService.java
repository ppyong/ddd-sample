package com.ppyong.test.member.domain;

import com.ppyong.test.member.infra.MemberRepository;
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
