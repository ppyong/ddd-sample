package com.ppyong.test.reply.infra;

import com.ppyong.test.reply.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findByBoardId(Long boardId);

    @Query("SELECT r FROM Reply r WHERE r.boardId in :boardIdList")
    List<Reply> findByBoardIds(List<Long> boardIdList);
}
