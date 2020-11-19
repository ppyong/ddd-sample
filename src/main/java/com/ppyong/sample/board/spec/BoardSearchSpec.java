package com.ppyong.sample.board.spec;

import com.ppyong.sample.board.network.SearchReq;
import org.springframework.data.jpa.domain.Specification;

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
//        List<Predicate> predicates = new ArrayList<>();
//        if(ObjectUtils.isEmpty(searchReq.getTitle().isEmpty())){
//            predicates.add(criteriaBuilder.equal(root.get("title"), searchReq.getTitle()));
//        }
//        if(ObjectUtils.isEmpty(searchReq.getContent().isEmpty())){
//            predicates.add(criteriaBuilder.equal(root.get("title"), searchReq.getTitle()));
//        }
//        for(Predicate p : predicates){
//            criteriaBuilder.and(predicates);
//        }
//
//        query.where(predicates.toArray(null));
//        return query.getRestriction();
        return null;
    }
}
