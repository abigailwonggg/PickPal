package com.mobdeve.s17.AAAJATTERY.PickPal;

public class Post {

    private String userId;
    private String club;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    // Required empty constructor for Firebase
    public Post() {}

    public Post(String userId, String club, String option1, String option2, String option3, String option4) {
        this.userId = userId;
        this.club = club;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3.isEmpty() ? null : option3;
        this.option4 = option4.isEmpty() ? null : option4;
    }

    // Getters
    public String getUserId() { return userId; }
    public String getClub() { return club; }
    public String getOption1() { return option1; }
    public String getOption2() { return option2; }
    public String getOption3() { return option3; }
    public String getOption4() { return option4; }
}

