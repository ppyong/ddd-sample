package com.ppyong.test.board.network;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchRes {
    private Long id;

    private String title;

    private String content;

    private String createBy;

    private String createNickName;

    private LocalDateTime createDt;

    private Long likeCount;

    public SearchRes(Long id, String title, String content, String createBy, String createNickName, LocalDateTime createDt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createBy = createBy;
        this.createNickName = createNickName;
        this.createDt = createDt;
    }
}
