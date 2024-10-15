package com.mobdeve.s17.AAAJATTERY.PickPal;

public class AlertData {

    public enum Type {
        FOLLOW_REQUEST,
        COMMENT
    }

    private String username;
    private String text;
    private String comment;
    private String time;
    private int userIcon;
    private Type type;

    // Constructor for follow request
    public AlertData(String username, String text, String time, int userIcon, Type type) {
        this.username = username;
        this.text = text;
        this.time = time;
        this.userIcon = userIcon;
        this.type = type;
    }

    public AlertData(String username, String text, String comment, String time, int userIcon, Type type) {
        this.username = username;
        this.text = text;
        this.comment = comment;
        this.time = time;
        this.userIcon = userIcon;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public String getText() {
        return text;
    }

    public String getComment() {
        return comment;
    }

    public String getTime() {
        return time;
    }

    public int getUserIcon() { // Add getter for user icon
        return userIcon;
    }

    public Type getType() {
        return type;
    }
}