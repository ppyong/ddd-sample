package com.ppyong.sample.member.infra;

import com.ppyong.sample.member.domain.QMember;
import com.ppyong.sample.member.ui.SearchReq;
import com.ppyong.sample.member.ui.SearchRes;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory query;

    @Override
    public List<SearchRes> findAllWithSearchReq(SearchReq req) {
        QMember member = QMember.member;

        List<Predicate> predicates = new ArrayList<>();
        if(!ObjectUtils.isEmpty(req.getLoginId())) {
            predicates.add(member.loginId.eq(req.getLoginId()));
        }
        if(!ObjectUtils.isEmpty(req.getName())) {
            predicates.add(member.name.eq(req.getName()));
        }
        if(!ObjectUtils.isEmpty(req.getNickName())) {
            predicates.add(member.nickName.eq(req.getNickName()));
        }

        return query
                .select(Projections.constructor(SearchRes.class, member.loginId, member.name, member.nickName, member.phoneNumber, member.address, member.grade))
                .from(member)
                .where(ExpressionUtils.anyOf(predicates))
                .fetch();
    }
}
