package com.ppyong.test.board.network;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class SearchRes {
    private Long id;

    private String title;

    private String content;

    private String creator;

    private LocalDateTime createDt;

    private String updater;

    private LocalDateTime updateDt;

    private Long likeCount;
}
