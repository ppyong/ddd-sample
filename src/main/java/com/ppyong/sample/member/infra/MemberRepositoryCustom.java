package com.ppyong.sample.member.infra;

import com.ppyong.sample.member.ui.SearchReq;
import com.ppyong.sample.member.ui.SearchRes;

import java.util.List;

public interface MemberRepositoryCustom {
    List<SearchRes> findAllWithSearchReq(SearchReq req);
}
