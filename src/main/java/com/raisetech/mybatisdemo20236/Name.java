package com.raisetech.mybatisdemo20236;

public class Name {
    private int id;
    private String name;

    private String address;

    private String bankAccountInfo;


    public Name(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getBankAccountInfo() {
        return bankAccountInfo;
    }
}
