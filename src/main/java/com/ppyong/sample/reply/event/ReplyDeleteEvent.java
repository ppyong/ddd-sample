package com.ppyong.sample.reply.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReplyDeleteEvent {
    private Long boardId;
}
