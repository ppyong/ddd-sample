package com.ppyong.sample.board.domain;

import com.ppyong.sample.board.infra.BoardRepository;
import com.ppyong.sample.board.ui.CreateReq;
import com.ppyong.sample.board.ui.SearchReq;
import com.ppyong.sample.board.ui.SearchRes;
import com.ppyong.sample.board.ui.UpdateReq;
import com.ppyong.sample.global.error.ResourceNotFoundException;
import com.ppyong.sample.global.utils.ConverterUtil;
import com.ppyong.sample.like.infra.BoardLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// 기본적으로 application 계층을 위한 데이터는 domain model이 아닌 dto 형태로 반환한다.
@Service
@RequiredArgsConstructor
public class BoardAppService {
    private final BoardService boardService;
    private final BoardRepository boardRepository;
    private final BoardLikeRepository boardLikeRepository;

    @Transactional
    public void create(CreateReq req) {
        Board board = ConverterUtil.map(req, Board.class);
        boardRepository.save(board);
    }

    @Transactional
    public void update(Long boardId, UpdateReq req) {
        Board board = boardRepository.findById(boardId).orElseThrow(()-> new ResourceNotFoundException(boardId));
        ConverterUtil.map(req, board);
        boardRepository.save(board);
    }

    @Transactional
    public void patch(Long boardId, UpdateReq req) {
        Board board = boardRepository.findById(boardId).orElseThrow(()-> new ResourceNotFoundException(boardId));
        ConverterUtil.skipNullMap(req, board);
        boardRepository.save(board);
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

    public List<SearchRes> search(SearchReq req, Pageable pageable) {
        return boardRepository.findAllWithMemberInfo(req, pageable);
    }

    public long countLikes(Long boardId) {
        return boardLikeRepository.countByReferenceId(boardId);
    }
}
