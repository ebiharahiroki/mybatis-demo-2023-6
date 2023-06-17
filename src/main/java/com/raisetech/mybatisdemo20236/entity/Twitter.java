package com.raisetech.mybatisdemo20236.entity;

public class Twitter {
    private int id;
    private String likes;

    private String followers;


    public Twitter(int id, String likes, String followers) {
        this.id = id;
        this.likes = likes;
        this.followers = followers;
    }

    public int getId() {
        return id;
    }

    public String getLikes() {
        return likes;
    }

    public String getFollowers() {
        return followers;
    }

}
