package com.raisetech.mybatisdemo20236.service;

import com.raisetech.mybatisdemo20236.entity.Twitter;
import com.raisetech.mybatisdemo20236.form.TwitterCreateForm;

import java.util.List;

public interface TwitterService {

    //    名前を全部取得する
    List<Twitter> findAll();

    //    IDを取得する
    Twitter findById(int id);

    //    いいねの数を取得する
    List<Twitter> findByLikes(Integer likes);

    //ツイート情報を登録する
    Twitter createTwitter(TwitterCreateForm form);

    //    ツイート情報を更新する
    void updateTwitter(Twitter updateTwitter);

    //    指定したIDのツイート情報を削除する
    void deleteTwitter(int id);
}
