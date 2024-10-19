package com.mobdeve.s17.AAAJATTERY.PickPal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ClubFeedFragment extends Fragment {

    private RecyclerView recyclerView;
    private PollAdapter pollAdapter;
    private List<PollData> pollList;
    private String clubName, clubDescription;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the fragment layout
        View view = inflater.inflate(R.layout.fragment_club_feed, container, false);

        // Get the club name and description from the arguments
        if (getArguments() != null) {
            clubName = getArguments().getString("clubName");
            clubDescription = getArguments().getString("clubDescription");
        }

        // Set the club title and description in the UI
        TextView clubTitleView = view.findViewById(R.id.club_title);
        TextView clubDescriptionView = view.findViewById(R.id.club_description);
        clubTitleView.setText(clubName);
        clubDescriptionView.setText(clubDescription);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recycler_view_polls);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

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
        pollAdapter = new PollAdapter(getContext(), filteredPollList, clubName);
        recyclerView.setAdapter(pollAdapter);

        // Set up the "Create a Post" button click listener
        view.findViewById(R.id.post_btn).setOnClickListener(v -> openCreatePostFragment());

        return view;
    }

    private void openCreatePostFragment() {
        // Create a new instance of CreateFragment
        CreateFragment createFragment = new CreateFragment();

        // Pass the current club name as an argument
        Bundle args = new Bundle();
        args.putString("clubName", clubName);
        createFragment.setArguments(args);

        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, createFragment);
        transaction.addToBackStack(null);  // So the user can navigate back
        transaction.commit();
    }
}


