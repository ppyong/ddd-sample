package com.ppyong.sample.like.infra;

import com.ppyong.sample.reply.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyLikeRepository extends JpaRepository<Reply, Long> {
    @Query(value = "SELECT count(l) FROM Likes l WHERE l.referenceId = :replyId")
    long countByReferenceId(Long boardId);
}
