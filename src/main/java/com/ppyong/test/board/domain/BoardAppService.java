package com.ppyong.test.board.domain;

import com.ppyong.test.board.command.BoardCreateCommand;
import com.ppyong.test.board.command.BoardSearchCommand;
import com.ppyong.test.board.command.BoardUpdateCommand;
import com.ppyong.test.board.infra.BoardRepository;
import com.ppyong.test.global.error.ResourceNotFoundException;
import com.ppyong.test.global.utils.ConverterUtil;
import com.ppyong.test.like.infra.BoardLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardAppService {
    private final BoardService boardService;
    private final BoardRepository boardRepository;
    private final BoardLikeRepository boardLikeRepository;

    @Transactional
    public Board create(BoardCreateCommand command) {
        Board board = ConverterUtil.map(command, Board.class);
        return boardRepository.save(board);
    }

    @Transactional
    public Board update(Long boardId, BoardUpdateCommand command) {
        Board board = boardRepository.findById(boardId).orElseThrow(()-> new ResourceNotFoundException(boardId));
        ConverterUtil.map(command, board);
        return boardRepository.save(board);
    }

    @Transactional
    public void delete(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(()-> new ResourceNotFoundException(boardId));
        board.delete();
    }

    public List<Board> search(BoardSearchCommand command) {
        return Arrays.asList(new Board(command.getTitle(), command.getContent()));//boardRepository.findBySearchCommand(command);
    }

    //FIXME!! join 혹은 in 절로 풀어 나가자
    public long countLikes(Long boardId) {
        return boardLikeRepository.countByReferenceId(boardId);
    }
}
