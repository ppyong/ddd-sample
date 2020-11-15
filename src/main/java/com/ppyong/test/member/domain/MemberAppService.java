package com.ppyong.test.member.domain;

import com.ppyong.test.global.utils.ConverterUtil;
import com.ppyong.test.member.command.MemberCreateCommand;
import com.ppyong.test.member.command.MemberUpdateCommand;
import com.ppyong.test.member.infra.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberAppService {
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @Transactional
    public Member create(MemberCreateCommand command){
        if(memberService.checkDuplicatedLoginId(command.getLoginId())){
            throw new IllegalStateException();
        }

        Member member = ConverterUtil.map(command, Member.class);
        return memberRepository.save(member);
    }

    @Transactional
    public Member update(Long memberId, MemberUpdateCommand command){
        Member member = memberRepository.findById(memberId).orElseThrow(NullPointerException::new);
        ConverterUtil.map(command, member);
        return memberRepository.save(member);
    }
}
