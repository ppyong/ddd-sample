package com.ppyong.test.like.domain;

import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Entity
@DiscriminatorValue("B")
public class BoardLikes extends Likes {
}
