package com.mobdeve.s17.AAAJATTERY.PickPal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ClubFeed extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PollAdapter pollAdapter;
    private List<PollData> pollList;  // Full poll list
    private String clubName, clubDescription;  // Club name and description to show

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_feed);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            v.setPadding(insets.getSystemWindowInsetLeft(), insets.getSystemWindowInsetTop(),
                    insets.getSystemWindowInsetRight(), insets.getSystemWindowInsetBottom());
            return insets;
        });

        // Get the club name and description from the Intent
        Intent intent = getIntent();
        clubName = intent.getStringExtra("clubName");
        clubDescription = intent.getStringExtra("clubDescription");

        // Set the club title and description in the UI
        TextView clubTitleView = findViewById(R.id.club_title);
        TextView clubDescriptionView = findViewById(R.id.club_description);
        clubTitleView.setText(clubName);
        clubDescriptionView.setText(clubDescription);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recycler_view_polls);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Prepare dummy data for polls
        pollList = new ArrayList<>();
        pollList.add(new PollData("mikmik", "Where should I go on October 17?", 1000, Arrays.asList("Yes", "No"), new ArrayList<>(), "Feed"));
        pollList.add(new PollData("lalisa", "What's your favorite season?", 250, Arrays.asList("Winter", "Spring", "Summer"), new ArrayList<>(), "Feed"));
        pollList.add(new PollData("anime_fan", "Who is your favorite anime character?", 400, Arrays.asList("Naruto", "Luffy", "Goku"), new ArrayList<>(), "Anime"));
        pollList.add(new PollData("otaku", "Which anime series are you watching now?", 300, Arrays.asList("Attack on Titan", "Demon Slayer"), new ArrayList<>(), "Anime"));
        pollList.add(new PollData("music_lover", "What's your favorite genre?", 500, Arrays.asList("Rock", "Pop", "Jazz", "Classical"), new ArrayList<>(), "Music"));
        pollList.add(new PollData("guitarist", "Do you play any musical instruments?", 300, Arrays.asList("Yes", "No"), Arrays.asList("default_image", "default_image"), "Music"));
        pollList.add(new PollData("scary_movie_buff", "What's the scariest movie you've seen?", 700, Arrays.asList("The Conjuring", "Hereditary", "It"), new ArrayList<>(), "Horror Movies"));
        pollList.add(new PollData("labubu", "Do you like coffee?", 500, Arrays.asList("Yes", "No"), Arrays.asList("default_image", "default_image"), "Manila Cafes"));
        pollList.add(new PollData("dina", "What's your favorite fruit?", 300, Arrays.asList("Apple", "Banana", "Orange"), Arrays.asList("default_image", "default_image", "default_image"), "Manila Cafes"));
        pollList.add(new PollData("bieber_fan", "What's your favorite Justin Bieber song?", 600, Arrays.asList("Baby", "Sorry", "Peaches"), new ArrayList<>(), "Justin Bieber Fans Club"));
        pollList.add(new PollData("belieber", "Are you excited for the next Justin Bieber concert?", 450, Arrays.asList("Yes", "No"), new ArrayList<>(), "Justin Bieber Fans Club"));

        // Filter polls that belong to the club
        List<PollData> filteredPollList = pollList.stream()
                .filter(poll -> poll.getClub() != null && poll.getClub().equals(clubName))
                .collect(Collectors.toList());

        // Set up the adapter with the filtered list
        pollAdapter = new PollAdapter(this, filteredPollList, clubName);
        recyclerView.setAdapter(pollAdapter);

        // Set up the "Create a Post" button click listener
        findViewById(R.id.post_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreatePostFragment();
            }
        });
    }

    private void openCreatePostFragment() {
        // Create a new instance of CreateFragment
        CreateFragment createFragment = new CreateFragment();

        // Pass the current club name as an argument
        Bundle args = new Bundle();
        args.putString("clubName", clubName);
        createFragment.setArguments(args);

        findViewById(R.id.recycler_view_polls).setVisibility(View.GONE);
        findViewById(R.id.club_head).setVisibility(View.GONE);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, createFragment);
        transaction.addToBackStack(null);  // So the user can navigate back
        transaction.commit();

        findViewById(R.id.fragment_container).setVisibility(View.VISIBLE);
    }

    public void bckBtn(View v) {
        finish();
    }
}

