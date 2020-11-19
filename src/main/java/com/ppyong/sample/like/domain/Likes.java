package com.ppyong.sample.like.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "like_type", length = 1)
public abstract class Likes {
    @GeneratedValue @Id
    @Column(name = "like_id")
    private Long id;

    private Long referenceId;

    private String creator;

    @CreatedDate
    private LocalDateTime createDt;
}
