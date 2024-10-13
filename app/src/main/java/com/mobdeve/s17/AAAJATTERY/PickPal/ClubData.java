package com.mobdeve.s17.AAAJATTERY.PickPal;

public class ClubData {
    private String name;
    private String description;
    private int imageResourceId;
    private int numberOfMembers;

    public ClubData(String name, String description, int imageResourceId, int numberOfMembers) {
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
        this.numberOfMembers = numberOfMembers;
    }

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
}

