package com.mobdeve.s17.AAAJATTERY.PickPal;

import java.util.List;

public class PollData {
    private String username;
    private String pollQuestion;
    private int voteCount;
    private List<String> options;
    private List<String> imageOptions;
    private String club;

    // Constructor
    public PollData(String username, String pollQuestion, int voteCount, List<String> options, List<String> imageOptions, String club) {
        this.username = username;
        this.pollQuestion = pollQuestion;
        this.voteCount = voteCount;
        this.options = options;
        this.imageOptions = imageOptions;
        this.club = club;
    }

    public String getUsername() {
        return username;
    }

    public String getPollQuestion() {
        return pollQuestion;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public List<String> getOptions() {
        return options;
    }

    public List<String> getImageOptions() {
        return imageOptions;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    // Determines if the poll has images
    public boolean hasImages() {
        return imageOptions != null && !imageOptions.isEmpty();
    }
}




