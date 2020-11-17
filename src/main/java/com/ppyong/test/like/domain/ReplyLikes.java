package com.ppyong.test.like.domain;

import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Entity
@DiscriminatorValue("R")
public class ReplyLikes extends Likes {
}
