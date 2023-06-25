package com.raisetech.mybatisdemo20236.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class Twitter {

    private int id;
    private Integer likes;
    private String followers;


    public Twitter(Integer likes, String followers) {
        this.likes = likes;
        this.followers = followers;
    }
}
