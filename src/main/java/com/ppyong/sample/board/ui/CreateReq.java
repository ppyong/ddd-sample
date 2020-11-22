package com.ppyong.sample.board.ui;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateReq {
    @NotEmpty
    private String title;

    @NotEmpty
    private String content;
}
