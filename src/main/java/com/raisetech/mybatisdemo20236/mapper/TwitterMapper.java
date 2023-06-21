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
    List<Twitter> findByLikesGreaterThan(Integer likes);

    @Insert("INSERT INTO twitter (likes, followers) VALUES (#{likes}, #{followers})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void createTwitter(Twitter twitter);

    @Update("UPDATE twitter SET likes = #{likes}, followers = #{followers} where id = #{id}")
    void updateTwitter(Twitter updateTwitter);

    @Delete("DELETE FROM twitter WHERE id = #{id}")
    void deleteTwitter(int id);
}
