package com.raisetech.mybatisdemo20236.controller;

import com.raisetech.mybatisdemo20236.entity.Twitter;
//import jakarta.validation.constraints.NotBlank;
//import org.hibernate.validator.constraints.Length;


public class TwitterResponse {
//    @NotBlank(message = "Nullもしくは空白文字です。")
//    @Length(min = 1, max = 100, message = "100文字以内で入力してください")エラーが出たためとりあえずコメントアウトして後回し

    private Integer likes;

    public TwitterResponse(Twitter likes) {
        this.likes = likes.getLikes();
    }

    public Integer getLikes() {
        return likes;
    }
}
