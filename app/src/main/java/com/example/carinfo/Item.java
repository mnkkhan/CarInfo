package com.example.carinfo;

public class Item {
    private String number;
    private String name;
    private String uid;

    public Item(String number, String name,String ud) {
        this.number = number;
        this.name = name;
        this.uid=ud;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}


