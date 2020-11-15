package com.ppyong.test.board.command;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardUpdateCommand {
    private String title;

    private String content;
}
