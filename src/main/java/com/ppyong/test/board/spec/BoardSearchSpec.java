package com.ppyong.test.board.spec;

import com.ppyong.test.board.network.SearchReq;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class BoardSearchSpec<Board> implements Specification<Board> {
    private SearchReq searchReq;

    public BoardSearchSpec(SearchReq searchReq){
        this.searchReq = searchReq;
    }

    @Override
    public Specification<Board> and(Specification<Board> other) {
        return null;
    }

    @Override
    public Specification<Board> or(Specification<Board> other) {
        return null;
    }

    @Override
    public Predicate toPredicate(Root<Board> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = null;
        if(ObjectUtils.isEmpty(searchReq.getTitle().isEmpty())){
            predicate = criteriaBuilder.equal(root.get(searchReq.getTitle());
        }
    }
}
