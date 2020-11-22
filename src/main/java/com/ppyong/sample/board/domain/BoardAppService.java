package com.ppyong.sample.board.domain;

import com.ppyong.sample.board.infra.BoardRepository;
import com.ppyong.sample.board.ui.*;
import com.ppyong.sample.global.error.ResourceNotFoundException;
import com.ppyong.sample.global.utils.ConverterUtil;
import com.ppyong.sample.like.infra.BoardLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardAppService {
    private final BoardService boardService;
    private final BoardRepository boardRepository;
    private final BoardLikeRepository boardLikeRepository;

    public List<SearchRes> search(SearchReq req, Pageable pageable) {
        return boardRepository.findAllWithMemberInfo(req, pageable);
    }

    public SearchRes find(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(()-> new ResourceNotFoundException(boardId));
        SearchRes res = ConverterUtil.map(board, SearchRes.class);
        res.setLikeCount(countLikes(boardId));
        return res;
    }

    @Transactional
    public void create(CreateReq req) {
        Board board = ConverterUtil.map(req, Board.class);
        boardRepository.save(board);
    }

    @Transactional
    public void update(Long boardId, UpdateReq req) {
        Board board = boardRepository.findById(boardId).orElseThrow(()-> new ResourceNotFoundException(boardId));
        ConverterUtil.map(req, board);
    }

    @Transactional
    public void patch(Long boardId, PatchReq req) {
        Board board = boardRepository.findById(boardId).orElseThrow(()-> new ResourceNotFoundException(boardId));
        ConverterUtil.map(req, board);
    }

    @Transactional
    public void delete(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(()-> new ResourceNotFoundException(boardId));
        board.delete();
    }

    public long countLikes(Long boardId) {
        return boardLikeRepository.countByReferenceId(boardId);
    }
}
