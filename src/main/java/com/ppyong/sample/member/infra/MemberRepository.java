package com.ppyong.sample.member.infra;

import com.ppyong.sample.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
    Optional<Member> findByLoginIdOrNickName(String loginId, String nickName);

    Optional<Member> findByLoginId(String loginId);
}
