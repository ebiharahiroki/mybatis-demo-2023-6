package com.raisetech.mybatisdemo20236.service;

import com.raisetech.mybatisdemo20236.entity.Anime;

import java.util.List;

public interface AnimeService {

    //    名前を全部取得する
    List<Anime> findAll();

//    Anime findById(int id);
//
//    Anime findByEvaluated_Value(String evaluated_value);
//
//    Optional<Anime> findByEvaluatedValue(String evaluated_value);とりあえずシンプルにするためにコメントで保留
}
