package com.ppyong.test.board.domain;

import com.ppyong.test.board.command.BoardCreateCommand;
import com.ppyong.test.board.command.BoardSearchCommand;
import com.ppyong.test.board.command.BoardUpdateCommand;
import com.ppyong.test.board.infra.BoardRepository;
import com.ppyong.test.board.network.SearchRes;
import com.ppyong.test.global.error.ResourceNotFoundException;
import com.ppyong.test.global.utils.ConverterUtil;
import com.ppyong.test.like.infra.BoardLikeRepository;
import com.ppyong.test.member.domain.Member;
import com.ppyong.test.member.infra.MemberRepository;
import com.ppyong.test.reply.domain.Reply;
import com.ppyong.test.reply.infra.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public SearchRes find(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(()-> new ResourceNotFoundException(boardId));
        SearchRes res = ConverterUtil.map(board, SearchRes.class);
        res.setLikeCount(countLikes(boardId));
        return res;
    }

    public List<SearchRes> search(BoardSearchCommand command, Pageable pageable) {
        // FIXME!!
        // command 에서 데이터를 지우고 싶은 경우와 수정한 값만 변경하고 싶을 때 어떤식으로 구분해야할까?
        return boardRepository.findAllWithMemberInfo(pageable);
    }

    public long countLikes(Long boardId) {
        return boardLikeRepository.countByReferenceId(boardId);
    }
}
