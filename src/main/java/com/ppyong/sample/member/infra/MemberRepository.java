package com.ppyong.sample.member.infra;

import com.ppyong.sample.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByLoginId(String loginId);

    List<Member> findByMemberIds(List<Long> memberIdList);
}
