package com.ppyong.sample.board.infra;

import com.ppyong.sample.board.domain.QBoard;
import com.ppyong.sample.board.ui.SearchReq;
import com.ppyong.sample.board.ui.SearchRes;
import com.ppyong.sample.member.domain.QMember;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class BoardRepositoryCustomImpl implements BoardRepositoryCustom {
    private final JPAQueryFactory query;

    @Override
    public List<SearchRes> findAllWithMemberInfo(SearchReq req, Pageable pageable) {
        QBoard board = QBoard.board;
        QMember member = QMember.member;

        List<Predicate> predicates = new ArrayList<>();
        if(!ObjectUtils.isEmpty(req.getTitle())) {
            predicates.add(board.title.like(req.getTitle()));
        }
        if(!ObjectUtils.isEmpty(req.getContent())) {
            predicates.add(board.content.like(req.getContent()));
        }

        return query
                .select(Projections.constructor(SearchRes.class, board.id, board.title, board.content, board.createBy, member.nickName, board.createDt))
                .from(board)
                .join(member)
                .on(board.createBy.eq(member.loginId))
                .where(ExpressionUtils.anyOf(predicates))
                .fetch();
    }
}
