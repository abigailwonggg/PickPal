package com.mobdeve.s17.AAAJATTERY.PickPal;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import androidx.recyclerview.widget.GridLayoutManager;

public class ProfileFragment extends Fragment {

    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private List<PostData> postList;
    private Button moderatorDashboardButton;
    private Button editProfileButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Find the moderator dashboard button and set the OnClickListener
        moderatorDashboardButton = view.findViewById(R.id.modButton);
        moderatorDashboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModDash();
            }
        });

        editProfileButton = view.findViewById(R.id.editProfBtn);
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewEditProfile();
            }
        });

        recyclerView = view.findViewById(R.id.recycler_view);

        // Set up the GridLayoutManager with 2 columns
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        // Initialize post list and adapter
        postList = new ArrayList<>();
        postAdapter = new PostAdapter(postList);
        recyclerView.setAdapter(postAdapter);

        // Populate the list with dummy data or fetch from your data source
        loadPosts();

        return view;
    }

    private void loadPosts() {
        // Add dummy posts for testing
        postList.add(new PostData("Purple", R.drawable.user_pic, "Pedigree or Alpo..."));
        postList.add(new PostData("Blue", R.drawable.user_pic, "This or That..."));
        postList.add(new PostData("Green", R.drawable.user_pic, "Chimken or beef..."));
        postList.add(new PostData("Yellow", R.drawable.user_pic, "Ball or frisbee..."));
        postList.add(new PostData("Orange", R.drawable.user_pic, "Treats or toys..."));
        postList.add(new PostData("Red", R.drawable.user_pic, "Walks or car rides..."));

        // Notify the adapter of the data change
        postAdapter.notifyDataSetChanged();
    }

    private void viewModDash() {
        // This method starts the ModeratorModule activity
        Intent intent = new Intent(getActivity(), ModeratorModule.class);
        startActivity(intent);
    }

    private void viewEditProfile() {
        // This method starts the ModeratorModule activity
        Intent intent = new Intent(getActivity(), EditProfile.class);
        startActivity(intent);
    }
}