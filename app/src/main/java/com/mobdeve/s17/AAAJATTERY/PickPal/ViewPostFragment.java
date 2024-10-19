package com.mobdeve.s17.AAAJATTERY.PickPal;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ViewPostFragment extends Fragment {

    private RecyclerView recyclerViewComments;
    private Button buttonLeaveComment;
    private TextView textPost, textNumberVotes;
    private ImageButton backButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_post, container, false);

        // Initialize views
        recyclerViewComments = view.findViewById(R.id.recycler_view_comments);
        buttonLeaveComment = view.findViewById(R.id.btn_comment);
        textPost = view.findViewById(R.id.caption);
        textNumberVotes = view.findViewById(R.id.number_votes);
        backButton = view.findViewById(R.id.btn_back);

        // Set up RecyclerView
        recyclerViewComments.setLayoutManager(new LinearLayoutManager(getActivity()));
        // recyclerViewComments.setAdapter(new CommentsAdapter()); // Set your adapter here

        // Set click listener for the Leave Comment button
        buttonLeaveComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Leave a comment clicked", Toast.LENGTH_SHORT).show();
                // Handle leave comment logic here
            }
        });

        // Set click listener for the back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed(); // Go back to the previous fragment
            }
        });

        // Load post data (this is just an example, you should load actual data)
        loadPostData();

        return view;
    }

    private void loadPostData() {
        // Replace this with actual post data loading logic
        textPost.setText("Where should I go on October 17");
        textNumberVotes.setText("1000 votes");
    }
}
