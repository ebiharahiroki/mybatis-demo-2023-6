package com.raisetech.mybatisdemo20236.form;

import com.raisetech.mybatisdemo20236.entity.Twitter;
import lombok.Getter;

@Getter
public class TwitterUpdateForm {

    private Integer likes;

    private String followers;

    public Twitter convertToTwitter(int id) {
        Twitter updateTwitter = new Twitter(likes, followers);
        return updateTwitter;
    }
}
