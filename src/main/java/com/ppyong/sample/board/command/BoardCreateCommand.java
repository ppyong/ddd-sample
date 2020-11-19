package com.ppyong.sample.board.command;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardCreateCommand {
    private String title;

    private String content;
}
