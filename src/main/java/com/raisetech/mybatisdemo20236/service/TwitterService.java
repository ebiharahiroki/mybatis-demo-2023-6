package com.raisetech.mybatisdemo20236.service;

import com.raisetech.mybatisdemo20236.entity.Twitter;
import com.raisetech.mybatisdemo20236.form.TwitterCreateForm;

import java.util.List;

public interface TwitterService {

    //    名前を全部取得する
    List<Twitter> findAll();

    Twitter findById(int id);

    List<Twitter> findByLikes(Integer likes);

    Twitter createTwitter(TwitterCreateForm form);

    void updateTwitter(Twitter updateTwitter);

    void deleteTwitter(int id);
}
