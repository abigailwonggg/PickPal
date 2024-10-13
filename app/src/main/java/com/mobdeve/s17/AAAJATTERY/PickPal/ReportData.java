package com.mobdeve.s17.AAAJATTERY.PickPal;

public class ReportData {
    private int userIcon; // Drawable resource ID for user icon
    private String userName;
    private String postTitle;

    public ReportData(int userIcon, String userName, String postTitle) {
        this.userIcon = userIcon;
        this.userName = userName;
        this.postTitle = postTitle;
    }

    // Getters
    public int getUserIcon() {
        return userIcon;
    }

    public String getUserName() {
        return userName;
    }

    public String getPostTitle() {
        return postTitle;
    }
}

