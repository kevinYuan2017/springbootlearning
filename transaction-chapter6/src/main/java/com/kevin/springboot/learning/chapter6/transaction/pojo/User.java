package com.kevin.springboot.learning.chapter6.transaction.pojo;

import org.apache.ibatis.type.Alias;

@Alias("user")
public class User {
    private long id;
    private String name;
    private String note;

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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
