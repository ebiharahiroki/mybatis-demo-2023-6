package com.raisetech.mybatisdemo20236.mapper;

import com.raisetech.mybatisdemo20236.entity.Anime;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AnimeMapper {
    @Select("SELECT * FROM anime")
    List<Anime> findAll();

//    Optional<Anime> findById(int id);
//
//    Optional<Anime> findByEvaluatedValue(String evaluated_value);問題が出たためコメントアウト
}
