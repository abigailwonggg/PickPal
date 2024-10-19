package com.mobdeve.s17.AAAJATTERY.PickPal;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private PollAdapter pollAdapter;
    private List<PollData> pollList;
    private String currentClub = "Feed"; // Set this to "Feed" or a specific club like "Manila Cafes"

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the fragment layout
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize RecyclerView
        recyclerView = rootView.findViewById(R.id.recycler_view_polls);

        // Initialize the poll list with all different types for testing
        pollList = new ArrayList<>();

        // 2-option word poll for "Feed"
        pollList.add(new PollData("mikmik", "Where should I go on October 17?", 1000,
                Arrays.asList("Yes", "No"), new ArrayList<>(), "Feed"));

        // 2-option word + image poll for "Manila Cafes"
        pollList.add(new PollData("labubu", "Do you like coffee?", 500,
                Arrays.asList("Yes", "No"),
                Arrays.asList("default_image", "default_image"), "Manila Cafes"));

        // 3-option word poll for "Feed"
        pollList.add(new PollData("lalisa", "What’s your favorite season?", 250,
                Arrays.asList("Winter", "Spring", "Summer"), new ArrayList<>(), "Feed"));

        // 3-option word + image poll for "Manila Cafes"
        pollList.add(new PollData("dina lily go", "What's your favorite fruit?", 300,
                Arrays.asList("Apple", "Banana", "Orange"),
                Arrays.asList("default_image", "default_image", "default_image"), "Manila Cafes"));

        // 4-option word poll for "Feed"
        pollList.add(new PollData("mila", "Where should we go for dinner?", 150,
                Arrays.asList("Italian", "Mexican", "Chinese", "Indian"), new ArrayList<>(), "Feed"));

        // 4-option word + image poll for "Manila Cafes"
        pollList.add(new PollData("dave", "Choose a vacation destination:", 400,
                Arrays.asList("Beach", "Mountain", "City", "Countryside"),
                Arrays.asList("default_image", "default_image", "default_image", "default_image"), "Feed"));

        // Filter the poll list based on the current club, with null check for poll.getClub()
        List<PollData> filteredPollList = pollList.stream()
                .filter(poll -> poll.getClub() != null && poll.getClub().equals(currentClub))
                .collect(Collectors.toList());

        // Set up the RecyclerView adapter and layout manager with the filtered list
        pollAdapter = new PollAdapter(getContext(), filteredPollList, currentClub);  // Pass the filtered list and currentClub
        recyclerView.setAdapter(pollAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootView;  // Return the inflated view
    }
}