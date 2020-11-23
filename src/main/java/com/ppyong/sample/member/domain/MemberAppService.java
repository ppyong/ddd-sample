package com.ppyong.sample.member.domain;

import com.ppyong.sample.global.error.ResourceNotFoundException;
import com.ppyong.sample.global.utils.ConverterUtil;
import com.ppyong.sample.member.converter.AddressConverter;
import com.ppyong.sample.member.converter.PhoneNumberConverter;
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
        memberService.checkDuplicated(req.getLoginId(), req.getNickName());
        Member member = new Member(
                req.getLoginId(),
                req.getName(),
                req.getNickName(),
                req.getPassword(),
                new PhoneNumberConverter().convertToEntityAttribute(req.getPhoneNumber()),
                new AddressConverter().convertToEntityAttribute(req.getAddress()),
                Grade.USER
        );
        memberRepository.save(member);
    }

    @Transactional
    public void update(Long memberId, UpdateReq req){
        Member member = memberRepository.findById(memberId).orElseThrow(()-> new ResourceNotFoundException(memberId));
        member.changeName(req.getName());
        member.changeNickName(req.getNickName());
        member.changePassword(req.getPassword());
        member.changePhoneNumber(new PhoneNumberConverter().convertToEntityAttribute(req.getPhoneNumber()));
        member.changeAddress(new AddressConverter().convertToEntityAttribute(req.getAddress()));
    }

    @Transactional
    public void patch(Long memberId, PatchReq req){
        Member member = memberRepository.findById(memberId).orElseThrow(()-> new ResourceNotFoundException(memberId));
        if(req.getName() != null) member.changeName(req.getName().orElse(null));
        if(req.getNickName() != null) member.changeNickName(req.getNickName().orElse(null));
        if(req.getPassword() != null) member.changePassword(req.getPassword().orElse(null));
        if(req.getAddress() != null) {
            member.changeAddress(new AddressConverter().convertToEntityAttribute(req.getAddress().orElse(null)));
        }
        if(req.getPhoneNumber() != null) {
            member.changePhoneNumber(new PhoneNumberConverter().convertToEntityAttribute(req.getAddress().orElse(null)));
        }
    }

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Member member = memberRepository.findByLoginId(loginId).orElseThrow(()->new UsernameNotFoundException(loginId));
        return new User(member.getLoginId(), member.getPassword(), List.of(new SimpleGrantedAuthority(member.getGrade().name())));
    }
}
