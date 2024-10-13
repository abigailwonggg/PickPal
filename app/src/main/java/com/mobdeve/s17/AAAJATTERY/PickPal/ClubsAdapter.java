package com.mobdeve.s17.AAAJATTERY.PickPal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ClubsAdapter extends RecyclerView.Adapter<ClubsAdapter.ClubViewHolder> {

    private List<ClubData> clubList;

    public static class ClubViewHolder extends RecyclerView.ViewHolder {
        public ImageView clubImage;
        public TextView clubName;
        public TextView clubDescription;
        public TextView clubMembers;

        public ClubViewHolder(View itemView) {
            super(itemView);
            clubImage = itemView.findViewById(R.id.club_icon);
            clubName = itemView.findViewById(R.id.club_name);
            clubDescription = itemView.findViewById(R.id.club_description);
            clubMembers = itemView.findViewById(R.id.member_count);
        }
    }

    public ClubsAdapter(List<ClubData> clubs) {
        this.clubList = clubs;
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
    }

    @Override
    public int getItemCount() {
        return clubList.size();
    }
}

