package com.ppyong.test.board.domain;

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
@Where(clause = "deleted = 0")
public class Board extends AbstractAggregateRoot {
    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    private String content;

    @Column(length = 20)
    private String creator;

    @CreatedDate
    private LocalDateTime createDt;

    @Column(length = 20)
    private String updater;

    @LastModifiedDate
    private LocalDateTime updateDt;

    // FIXME  어떨때 객체를 통한 매핑을 할지, 별도 ID를 통해 리스트를 조회할지 아직 판단이 되지 않는다.
    @OneToMany(targetEntity = Reply.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "board_id")
    private List<Reply> replyList = new ArrayList<>();

    private boolean deleted = false;

    public Board(String title, String content){
        this.title = title;
        this.content = content;
    }

    public void changeTitle(String title){
        this.title = title;
    }

    public void changeContent(String content){
        this.content = content;
    }

    public void changeCreator(String creator){
        this.creator = creator;
    }

    public void delete(){
        this.deleted = true;
        //registerEvent(new ReplyDeleteEvent(getId()));
    }

    public void recovery(){
        this.deleted = false;
    }

    public void changeUpdater(String updater){
        //registerEvent() -> AbstractAggregateRoot 기능, 이벤트 발생
        this.updater = updater;
    }

    //@DomainEvents -> 엔티티 저장 후 발생하는 이벤트 (save를 통한 저장일 경우만 발생)

    //@AfterDomainEventPublication -> @DomainEvents 이벤트 발생되어 게시된 후에 실행

}
