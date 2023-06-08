package com.raisetech.mybatisdemo20236.entity;

public class Anime {
    private int id;
    private String title;

    private String evaluated_value;


    public Anime(int id, String title, String evaluated_value) {
        this.id = id;
        this.title = title;
        this.evaluated_value = evaluated_value;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getEvaluated_Value() {
        return evaluated_value;
    }

}
