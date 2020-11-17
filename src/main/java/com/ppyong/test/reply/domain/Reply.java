package com.ppyong.test.reply.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reply extends AbstractAggregateRoot {
    @Id @GeneratedValue
    @Column(name = "reply_id")
    private Long id;

    @Column(name = "board_id")
    private String boardId;

    @Column(length = 200)
    private String content;

    @Column(length = 20)
    private String creator;

    @CreatedDate
    private LocalDateTime createDt;

    @Column(length = 20)
    private String updater;

    @LastModifiedDate
    private LocalDateTime updateDt;

    public void changeContent(String content){
        this.content = content;
    }

    public void setUpdater(String updater){
        this.updater = updater;
    }
}
