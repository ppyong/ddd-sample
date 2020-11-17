package com.ppyong.test.reply.listener;

import com.ppyong.test.reply.domain.Reply;
import com.ppyong.test.reply.event.ReplyDeleteEvent;
import com.ppyong.test.reply.infra.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;
import java.util.stream.IntStream;

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
