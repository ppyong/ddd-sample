package com.ppyong.sample.board.ui;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UpdateReq {
    private String title;

    private String content;
}
