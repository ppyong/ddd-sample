package com.ppyong.test.board.domain;

import com.ppyong.test.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {
    @Id @GeneratedValue
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    private String content;

    @Column(length = 20)
    @JoinColumn(name = "member_id")
    private Member creator;

    @CreatedDate
    private LocalDateTime createDt;

    @Column(length = 20)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member updater;

    @LastModifiedDate
    private LocalDateTime updateDt;

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

    public void changeCreator(Member creator){
        this.creator = creator;
    }

    public void changeUpdater(Member updater){
        this.updater = updater;
    }
}
