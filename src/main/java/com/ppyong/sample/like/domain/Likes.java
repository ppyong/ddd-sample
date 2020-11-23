package com.ppyong.sample.like.domain;

import com.ppyong.sample.global.domain.BaseEntity;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "like_type", length = 1)
public abstract class Likes extends BaseEntity {
    @GeneratedValue @Id
    @Column(name = "like_id")
    private Long id;

    private Long referenceId;
}
