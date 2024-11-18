package com.mobdeve.s17.AAAJATTERY.PickPal;

public class CommentData {
    private String username;
    private String commentText;
    private String timestamp;
    private int userpic; // User picture resource ID

    public CommentData(String username, String commentText, String timestamp, int userpic) {
        this.username = username;
        this.commentText = commentText;
        this.timestamp = timestamp;
        this.userpic = userpic;
    }

    public String getUsername() {
        return username;
    }

    public String getCommentText() {
        return commentText;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public int getUserpic() {
        return userpic; // Return the user picture resource ID
    }
}
