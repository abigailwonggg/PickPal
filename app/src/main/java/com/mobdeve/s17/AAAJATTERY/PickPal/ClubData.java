package com.mobdeve.s17.AAAJATTERY.PickPal;

public class ClubData {
    private String name;
    private String description;
    private int imageResourceId;
    private int numberOfMembers;
    private boolean isJoined; // New field to track joined state

    // Constructor
    public ClubData(String name, String description, int imageResourceId, int numberOfMembers, boolean isJoined) {
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
        this.numberOfMembers = numberOfMembers;
        this.isJoined = isJoined; // Initialize the joined state
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public int getNumberOfMembers() {
        return numberOfMembers;
    }

    public boolean isJoined() {
        return isJoined;
    }

    public void setJoined(boolean joined) {
        isJoined = joined;
    }
}
