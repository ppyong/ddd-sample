package com.ppyong.test.board.domain;

import com.ppyong.test.board.command.BoardCreateCommand;
import com.ppyong.test.board.command.BoardSearchCommand;
import com.ppyong.test.board.command.BoardUpdateCommand;
import com.ppyong.test.board.infra.BoardRepository;
import com.ppyong.test.global.utils.ConverterUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardAppService {
    private final BoardService boardService;
    private final BoardRepository boardRepository;

    @Transactional
    public Board create(BoardCreateCommand command) {
        Board board = ConverterUtil.map(command, Board.class);
        return boardRepository.save(board);
    }

    @Transactional
    public Board update(Long boardId, BoardUpdateCommand command) {
        Board board = boardRepository.findById(boardId).orElseThrow(NullPointerException::new);
        ConverterUtil.map(command, board);
        return boardRepository.save(board);
    }

    @Transactional
    public void delete(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(NullPointerException::new);
        boardRepository.delete(board);
    }

    public Board search(BoardSearchCommand command) {
        return new Board(command.getTitle(), command.getContent());//boardRepository.findBySearchCommand(command);
    }
}
