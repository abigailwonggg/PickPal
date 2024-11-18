package com.mobdeve.s17.AAAJATTERY.PickPal;

public class ClubData {
    private String clubId;
    private String name;
    private String description;
    private int noOfMembers; // Match this name exactly with Firebase
    private boolean isJoined;

    // Empty constructor for Firebase
    public ClubData() {}

    // Constructor
    public ClubData(String clubId, String name, String description, int noOfMembers, boolean isJoined) {
        this.clubId = clubId;
        this.name = name;
        this.description = description;
        this.noOfMembers = noOfMembers; // Ensure this matches Firebase
        this.isJoined = isJoined;
    }

    // Getters and setters
    public String getClubId() { return clubId; }
    public void setClubId(String clubId) { this.clubId = clubId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getNoOfMembers() { return noOfMembers; } // Updated to match Firebase
    public void setNoOfMembers(int noOfMembers) { this.noOfMembers = noOfMembers; }

    public boolean isJoined() { return isJoined; }
    public void setJoined(boolean joined) { isJoined = joined; }
}
