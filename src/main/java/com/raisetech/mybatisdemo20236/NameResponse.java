package com.raisetech.mybatisdemo20236;

public class NameResponse {

    private String name;

    public NameResponse(Name name) {
        this.name = name.getName();
    }

    public String getName() {
        return name;
    }
}
