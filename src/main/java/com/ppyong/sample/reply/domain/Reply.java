package com.ppyong.sample.reply.domain;

import com.ppyong.sample.global.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reply extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "reply_id")
    private Long id;

    @Column(name = "board_id")
    private String boardId;

    @Column(length = 200)
    private String content;

    public void changeContent(String content){
        this.content = content;
    }
}
