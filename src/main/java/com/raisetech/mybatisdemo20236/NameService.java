package com.raisetech.mybatisdemo20236;

import com.raisetech.mybatisdemo20236.entity.Name;

import java.util.List;

public interface NameService {

    //    名前を全部取得する
    List<Name> findAll();
}
