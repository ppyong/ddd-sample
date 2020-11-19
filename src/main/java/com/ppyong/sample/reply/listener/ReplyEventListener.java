package com.ppyong.sample.reply.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReplyEventListener {
//    private final ReplyRepository replyRepository;
//
//    @TransactionalEventListener
//    public void deleteEventHandler(ReplyDeleteEvent event) {
//        List<Reply> replyList = replyRepository.findByBoardId(event.getBoardId());
//        replyList.forEach(reply->{
//            reply.delete();
//        });
//    }
}
