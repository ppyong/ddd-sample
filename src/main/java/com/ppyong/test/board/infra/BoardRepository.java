package com.ppyong.test.board.infra;

import com.ppyong.test.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
//    void findBySearchCommand(BoardSearchCommand command);
}
