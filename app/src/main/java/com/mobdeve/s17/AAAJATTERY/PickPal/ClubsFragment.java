package com.mobdeve.s17.AAAJATTERY.PickPal;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ClubsFragment extends Fragment {

    private RecyclerView recyclerView;
    private ClubsAdapter clubsAdapter;
    private List<ClubData> clubList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clubs, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_clubs);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        clubList = new ArrayList<>();
        clubList.add(new ClubData("Anime", "A club for anime enthusiasts.", R.drawable.default_image, 30, false));
        clubList.add(new ClubData("Music", "Share your favorite music and artists.", R.drawable.default_image, 45, false));
        clubList.add(new ClubData("Horror Movies", "Discuss and watch horror movies together.", R.drawable.default_image, 2000, false));
        clubList.add(new ClubData("Manila Cafes", "Explore different coffee shops in Manila.", R.drawable.default_image, 152, false));
        clubList.add(new ClubData("Justin Bieber Fans Club", "Stay updated with the latest Justin Bieber updates.", R.drawable.default_image, 250, false));

        // Set up the adapter
        clubsAdapter = new ClubsAdapter(getActivity(), clubList);  // Pass context and clubList
        recyclerView.setAdapter(clubsAdapter);

        return view;
    }
}
