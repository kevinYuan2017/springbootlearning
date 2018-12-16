package com.kevin.springboot.learning.databasechapter5.pojo;

import com.kevin.springboot.learning.databasechapter5.enumeration.Gender;

public class User {
    private long id;
    private String name;
    private Gender gender;
    private String note;

    public User() {
    }

    public User(long id, String name, Gender gender, String note) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.note = note;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
