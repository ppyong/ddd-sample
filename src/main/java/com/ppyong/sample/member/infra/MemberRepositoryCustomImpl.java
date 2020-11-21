package com.ppyong.sample.member.infra;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory query;

//    @Override
//    public List<Member> findByMemberIds(List<Long> memberIdList) {
//        QMember member = QMember.member;
//
//        return query
//                .select(member)
//                .from(member)
//                .where(member.id.in(memberIdList))
//                .fetch();
//    }
}
