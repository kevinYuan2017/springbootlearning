package com.kevin.springboot.learning.databasechapter5.enumeration;

public enum Gender {
    MALE(1, "male"),
    FEMALE(0, "female");
    private int id;
    private String name;

    Gender(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Gender getGenderById(int id){
        for (Gender gender: Gender.values()){
            if (gender.id == id){
                return gender;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
