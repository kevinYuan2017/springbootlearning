package com.kevin.springboot.learning.databasechapter5.pojo;

import com.kevin.springboot.learning.databasechapter5.converter.GenderConverter;
import com.kevin.springboot.learning.databasechapter5.enumeration.Gender;

import javax.persistence.*;

@Entity(name = "user")
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Convert(converter = GenderConverter.class)
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
