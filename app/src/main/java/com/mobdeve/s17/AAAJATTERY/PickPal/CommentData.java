package com.mobdeve.s17.AAAJATTERY.PickPal;

public class CommentData {

    private String username;
    private String commentText;
    private String timePosted;

    public CommentData(String username, String commentText, String timePosted) {
        this.username = username;
        this.commentText = commentText;
        this.timePosted = timePosted;
    }

    public String getUsername() {
        return username;
    }

    public String getCommentText() {
        return commentText;
    }

    public String getTimePosted() {
        return timePosted;
    }
}
