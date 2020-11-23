package com.ppyong.sample.member.domain;

import com.ppyong.sample.member.infra.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void checkDuplicated(String loginId, String nickName){
        Optional<Member> member = memberRepository.findByLoginIdOrNickName(loginId, nickName);
        if(member.isPresent()){
            throw new IllegalStateException();
        }
    }
}
