package com.ppyong.sample.board.infra;

import com.ppyong.sample.board.domain.Board;
import com.ppyong.sample.board.network.SearchRes;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>, JpaSpecificationExecutor<Board> {
    @Query("select " +
           "    new com.ppyong.sample.board.network.SearchRes(b.id, b.title, b.content, b.createBy, m.nickName, b.createDt) " +
           "from Board b " +
           "inner join Member m " +
           "on b.createBy = m.loginId")
    List<SearchRes> findAllWithMemberInfo(Pageable pageable);
}
