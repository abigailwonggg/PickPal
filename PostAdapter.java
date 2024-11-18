package com.mobdeve.s17.AAAJATTERY.PickPal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private List<PostData> postList;
    private FragmentManager fragmentManager;

    public PostAdapter(List<PostData> postList, FragmentManager fragmentManager) {
        this.postList = postList;
        this.fragmentManager = fragmentManager;
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

        // View button click event to open ViewPostFragment
        holder.viewButton.setOnClickListener(v -> {
            // Create a new instance of ViewPostFragment
            ViewPostFragment viewPostFragment = new ViewPostFragment();

            // Bundle to pass data (e.g., username and post text)
            Bundle bundle = new Bundle();
            bundle.putString("username", post.getUsername());
            bundle.putString("postTitle", post.getText());
            viewPostFragment.setArguments(bundle);

            // Start the fragment transaction to navigate to ViewPostFragment
            fragmentManager.beginTransaction()
                    .replace(R.id.root, viewPostFragment)  // Replace with the container ID of your fragment
                    .addToBackStack(null)  // Add to back stack so user can navigate back
                    .commit();
        });

        // Delete button click event (if required later)
        holder.delButton.setOnClickListener(v -> {
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
        Button delButton;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            userPic = itemView.findViewById(R.id.user_icon);
            username = itemView.findViewById(R.id.username);
            itemText = itemView.findViewById(R.id.post_title);
            viewButton = itemView.findViewById(R.id.view_button);
            delButton = itemView.findViewById(R.id.del_button);
        }
    }
}


