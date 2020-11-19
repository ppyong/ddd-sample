package com.ppyong.test.board.domain;

import com.ppyong.test.global.domain.BaseEntity;
import com.ppyong.test.reply.domain.Reply;
import com.ppyong.test.reply.event.ReplyDeleteEvent;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Access(AccessType.PROPERTY)
@Where(clause = "deleted = 0")
public class Board extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 2000)
    private String content;

    private Boolean deleted = Boolean.FALSE;

    public void changeTitle(String title){
        this.title = title;
    }

    public void changeContent(String content){
        this.content = content;
    }

    public void delete(){
        this.deleted = true;
    }

    public void recovery(){
        this.deleted = false;
    }

    public Board(String title, String content){
        this.title = title;
        this.content = content;
    }

    //registerEvent(new ReplyDeleteEvent(getId())); -> aggregate 추상클래스 구현 시

    //@DomainEvents -> 엔티티 저장 후 발생하는 이벤트 (save를 통한 저장일 경우만 발생)

    //@AfterDomainEventPublication -> @DomainEvents 이벤트 발생되어 게시된 후에 실행
}
