package com.raisetech.mybatisdemo20236.mapper;

import com.raisetech.mybatisdemo20236.entity.Twitter;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TwitterMapper {
    @Select("SELECT * FROM twitter")
    List<Twitter> findAll();

    @Select("SELECT * FROM twitter WHERE id = #{id}")
    Optional<Twitter> findById(int id);

    @Select("SELECT * FROM twitter WHERE likes > #{likes}")
    List<Twitter> findByLikesGreaterThan(int likes);

    @Insert("INSERT INTO twitter (likes, followers) VALUES (#{likes}, #{followers})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void createTwitter(Twitter twitter);

    @Update("UPDATE twitter SET likes = #{likes}, followers = #{followers} where id = #{id}")
    void updateTwitter(Twitter updateTwitter);

//    Optional<Anime> findById(int id);
//
//    Optional<Anime> findByEvaluatedValue(String evaluated_value);問題が出たためコメントアウト
}
