package com.ppyong.sample.member.domain;

import com.ppyong.sample.global.utils.ConverterUtil;
import com.ppyong.sample.member.command.MemberCreateCommand;
import com.ppyong.sample.member.command.MemberUpdateCommand;
import com.ppyong.sample.member.infra.MemberRepository;
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
