package com.ppyong.sample.board.infra;

import com.ppyong.sample.board.ui.SearchReq;
import com.ppyong.sample.board.ui.SearchRes;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardRepositoryCustom {
    List<SearchRes> findAllWithMemberInfo(SearchReq req, Pageable pageable);
}
