package com.mobdeve.s17.AAAJATTERY.PickPal;

public class User {
    public String uid;
    public String username;
    public String firstName;
    public String lastName;
    public String email;
    public String birthday;
    public String gender;

    public User() {}

    public User(String uid, String username, String firstName, String lastName, String email, String birthday, String gender) {
        this.uid = uid;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.gender = gender;
    }
}

