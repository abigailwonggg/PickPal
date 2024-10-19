package com.mobdeve.s17.AAAJATTERY.PickPal;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ClubsAdapter extends RecyclerView.Adapter<ClubsAdapter.ClubViewHolder> {

    private List<ClubData> clubList;
    private Context context;

    public ClubsAdapter(Context context, List<ClubData> clubList) {
        this.context = context;
        this.clubList = clubList;
    }

    public static class ClubViewHolder extends RecyclerView.ViewHolder {
        public ImageView clubImage;
        public TextView clubName;
        public TextView clubDescription;
        public TextView clubMembers;
        public Button joinButton, viewButton;
        public ImageView backButton;

        public ClubViewHolder(View itemView) {
            super(itemView);
            clubImage = itemView.findViewById(R.id.club_icon);
            clubName = itemView.findViewById(R.id.club_name);
            clubDescription = itemView.findViewById(R.id.club_description);
            clubMembers = itemView.findViewById(R.id.member_count);
            joinButton = itemView.findViewById(R.id.join_button);
            viewButton = itemView.findViewById(R.id.view_button);
            backButton = itemView.findViewById(R.id.back_button);
        }
    }

    @NonNull
    @Override
    public ClubViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.club_list, parent, false);
        return new ClubViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClubViewHolder holder, int position) {
        ClubData club = clubList.get(position);
        holder.clubName.setText(club.getName());
        holder.clubDescription.setText(club.getDescription());
        holder.clubMembers.setText(club.getNumberOfMembers() + " Members");
        holder.clubImage.setImageResource(club.getImageResourceId());

        // Handle view button click
        holder.viewButton.setOnClickListener(v -> {
            // Cast context to AppCompatActivity and start fragment transaction
            AppCompatActivity activity = (AppCompatActivity) context;
            ClubFeedFragment clubFeedFragment = new ClubFeedFragment();

            // Pass the club name and description to the fragment
            Bundle args = new Bundle();
            args.putString("clubName", club.getName());
            args.putString("clubDescription", club.getDescription());
            clubFeedFragment.setArguments(args);

            // Perform fragment transaction
            activity.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment, clubFeedFragment) // Replace with your container ID
                    .addToBackStack(null)
                    .commit();
        });

        // Handle join button click (same as before)
        if (club.isJoined()) {
            holder.joinButton.setText("Joined");
            holder.joinButton.setBackgroundResource(R.drawable.grey_btn);
        } else {
            holder.joinButton.setText("Join");
            holder.joinButton.setBackgroundResource(R.drawable.club_join_button);
            holder.joinButton.setOnClickListener(v -> {
                club.setJoined(true);
                holder.joinButton.setText("Joined");
                holder.joinButton.setBackgroundResource(R.drawable.grey_btn);
            });
        }
    }

    @Override
    public int getItemCount() {
        return clubList.size();
    }
}

