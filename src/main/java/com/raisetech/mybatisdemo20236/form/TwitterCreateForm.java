package com.raisetech.mybatisdemo20236.form;

public class TwitterCreateForm {
    private int id;

    private Integer Likes;

    private String Followers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getLikes() {
        return Likes;
    }

    public void setLikes(Integer likes) {
        this.Likes = likes;
    }

    public String getFollowers() {
        return Followers;
    }

    //
    public void setFollowers(String followers) {
        this.Followers = followers;
    }
}


//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//
//@Getter
//@AllArgsConstructor
//public class TwitterCreateForm {
//
//    @NotBlank
//    private Integer likes;
//
//    @NotNull
//    private String followers;
//
//}エラーが出たため後回し


