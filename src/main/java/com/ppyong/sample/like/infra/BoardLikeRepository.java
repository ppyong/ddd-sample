package com.ppyong.sample.like.infra;

import com.ppyong.sample.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardLikeRepository extends JpaRepository<Board, Long> {
    @Query(value = "SELECT count(l) FROM Likes l WHERE l.referenceId = :boardId")
    long countByReferenceId(Long boardId);
}
