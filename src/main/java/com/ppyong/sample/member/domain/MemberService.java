package com.ppyong.sample.member.domain;

import com.ppyong.sample.member.infra.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public boolean checkDuplicatedLoginId(String loginId){
        Optional<Member> member = memberRepository.findByLoginId(loginId);
        return member.isPresent();
    }
}
