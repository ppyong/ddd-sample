package com.ppyong.sample.board.ui;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Optional;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PatchReq {
    private Optional<String> title;

    private Optional<String> content;

    public void setTitle(String title){
        this.title = Optional.ofNullable(title);
    }

    public void setContent(String content){
        this.content = Optional.ofNullable(content);
    }
}