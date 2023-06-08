package com.raisetech.mybatisdemo20236.controller;

import com.raisetech.mybatisdemo20236.entity.Anime;
//import jakarta.validation.constraints.NotBlank;
//import org.hibernate.validator.constraints.Length;


public class AnimeResponse {
//    @NotBlank(message = "Nullもしくは空白文字です。")
//    @Length(min = 1, max = 100, message = "100文字以内で入力してください")エラーが出たためとりあえずコメントアウトして後回し

    private String title;

    public AnimeResponse(Anime title) {
        this.title = title.getTitle();
    }

    public String getTitle() {
        return title;
    }
}
