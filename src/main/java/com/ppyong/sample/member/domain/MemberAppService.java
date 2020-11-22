package com.ppyong.sample.member.domain;

import com.ppyong.sample.global.error.ResourceNotFoundException;
import com.ppyong.sample.global.utils.ConverterUtil;
import com.ppyong.sample.member.infra.MemberRepository;
import com.ppyong.sample.member.ui.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberAppService implements UserDetailsService {
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    public List<SearchRes> search(SearchReq req){
        return memberRepository.findAllWithSearchReq(req);
    }

    public SearchRes find(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(()-> new ResourceNotFoundException(memberId));
        return ConverterUtil.map(member, SearchRes.class);
    }

    @Transactional
    public void create(CreateReq req){
        if(memberService.checkDuplicatedLoginId(req.getLoginId())){
            throw new IllegalStateException();
        }
        Member member = ConverterUtil.map(req, Member.class);
        memberRepository.save(member);
    }

    @Transactional
    public void update(Long memberId, UpdateReq req){
        Member member = memberRepository.findById(memberId).orElseThrow(()-> new ResourceNotFoundException(memberId));
        ConverterUtil.map(req, member);
    }

    @Transactional
    public void patch(Long memberId, PatchReq req){
        Member member = memberRepository.findById(memberId).orElseThrow(()-> new ResourceNotFoundException(memberId));
        ConverterUtil.map(req, member);
    }

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Member member = memberRepository.findByLoginId(loginId).orElseThrow(()->new UsernameNotFoundException(loginId));
        return new User(member.getLoginId(), member.getPassword(), List.of(new SimpleGrantedAuthority(member.getGrade().name())));
    }
}
