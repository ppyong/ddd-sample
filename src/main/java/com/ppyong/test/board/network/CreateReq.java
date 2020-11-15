package com.ppyong.test.board.network;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateReq {
    private String title;

    private String content;
}
