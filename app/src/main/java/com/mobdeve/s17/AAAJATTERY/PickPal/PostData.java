package com.mobdeve.s17.AAAJATTERY.PickPal;

public class PostData {
    private String username;
    private int userPic; // Drawable resource for user's profile picture
    private String text;

    public PostData(String username, int userPic, String text) {
        this.username = username;
        this.userPic = userPic;
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public int getUserPic() {
        return userPic;
    }

    public String getText() {
        return text;
    }

}

