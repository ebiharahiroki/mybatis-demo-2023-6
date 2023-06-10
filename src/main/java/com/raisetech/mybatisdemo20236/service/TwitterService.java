package com.raisetech.mybatisdemo20236.service;

import com.raisetech.mybatisdemo20236.entity.Twitter;

import java.util.List;

public interface TwitterService {

    //    名前を全部取得する
    List<Twitter> findAll();

//    Anime findById(int id);
//
//    Anime findByEvaluated_Value(String evaluated_value);
//
//    Optional<Anime> findByEvaluatedValue(String evaluated_value);とりあえずシンプルにするためにコメントで保留
}
