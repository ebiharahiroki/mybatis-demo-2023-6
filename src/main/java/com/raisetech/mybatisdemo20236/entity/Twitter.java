package com.raisetech.mybatisdemo20236.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class Twitter {

    private int id;
    private Integer likes;
    private String followers;

    public Twitter(Integer likes, String followers) {
        this.likes = likes;
        this.followers = followers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Twitter twitter = (Twitter) o;
        return id == twitter.id
                && Objects.equals(this.likes, twitter.likes)
                && Objects.equals(followers, twitter.followers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, likes, followers);
    }
}

//    public class Twitter {
//        private int id;
//        private Integer likes;
//
//        private String followers;
//
//
//        public Twitter(int id, Integer likes, String followers) {
//            this.id = id;
//            this.likes = likes;
//            this.followers = followers;
//        }
//
//        public int getId() {
//            return id;
//        }
//
//        public Integer getLikes() {
//            return likes;
//        }
//
//        public String getFollowers() {
//            return followers;
//        }
//
//    }
