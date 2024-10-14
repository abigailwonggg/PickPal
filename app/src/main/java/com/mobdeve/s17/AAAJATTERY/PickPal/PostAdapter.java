package com.mobdeve.s17.AAAJATTERY.PickPal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private List<PostData> postList;

    public PostAdapter(List<PostData> postList) {
        this.postList = postList;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_list, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        PostData post = postList.get(position);
        holder.username.setText(post.getUsername());
        holder.userPic.setImageResource(post.getUserPic());
        holder.itemText.setText(post.getText());

        holder.viewButton.setOnClickListener(v -> {
            // Handle button click event
        });

        holder.editButton.setOnClickListener(v -> {
            // Handle button click event
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        ImageView userPic;
        TextView username;
        TextView itemText;
        Button viewButton;
        Button editButton;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            userPic = itemView.findViewById(R.id.user_icon);
            username = itemView.findViewById(R.id.username);
            itemText = itemView.findViewById(R.id.post_title);
            viewButton = itemView.findViewById(R.id.view_button);
            editButton = itemView.findViewById(R.id.edit_button);
        }
    }
}

