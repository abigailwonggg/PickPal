package com.mobdeve.s17.AAAJATTERY.PickPal;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ClubsFragment extends Fragment {

    private RecyclerView recyclerView;
    private ClubsAdapter clubsAdapter;
    private List<ClubData> clubList;
    private DatabaseReference clubsRef;  // Firebase database reference for clubs

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clubs, container, false);

        // Initialize Firebase reference
        clubsRef = FirebaseDatabase.getInstance().getReference("clubs");

        recyclerView = view.findViewById(R.id.recycler_view_clubs);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        clubList = new ArrayList<>();

        // Fetch clubs from Firebase
        fetchClubsFromFirebase();

        // Set up the adapter
        clubsAdapter = new ClubsAdapter(getActivity(), clubList);
        recyclerView.setAdapter(clubsAdapter);

        return view;
    }

    private void fetchClubsFromFirebase() {
        clubsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                clubList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Get the club ID (e.g., "club1") and data
                    String clubId = snapshot.getKey();
                    ClubData club = snapshot.getValue(ClubData.class);
                    if (club != null) {
                        club.setClubId(clubId);  // Set the ID in ClubData
                        clubList.add(club);
                    }

                }
                clubsAdapter.notifyDataSetChanged();  // Refresh RecyclerView with new data
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getContext(), "Failed to load clubs. Please try again later.", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
