package com.ppyong.test.board.command;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardSearchCommand {
    private String title;

    private String content;

    private String creator;
}
