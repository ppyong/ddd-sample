package com.ppyong.sample.member.domain;

import com.ppyong.sample.board.ui.SearchReq;
import com.ppyong.sample.board.ui.SearchRes;
import com.ppyong.sample.global.utils.ConverterUtil;
import com.ppyong.sample.member.infra.MemberRepository;
import com.ppyong.sample.member.ui.CreateReq;
import com.ppyong.sample.member.ui.UpdateReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberAppService {
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @Transactional
    public Member create(CreateReq req){
        if(memberService.checkDuplicatedLoginId(req.getLoginId())){
            throw new IllegalStateException();
        }

        Member member = ConverterUtil.map(req, Member.class);
        return memberRepository.save(member);
    }

    public List<SearchRes> search(SearchReq req){
        return null;
        //memberRepository.
    }

    @Transactional
    public Member update(Long memberId, UpdateReq req){
        Member member = memberRepository.findById(memberId).orElseThrow(NullPointerException::new);
        ConverterUtil.map(req, member);
        return memberRepository.save(member);
    }
}
