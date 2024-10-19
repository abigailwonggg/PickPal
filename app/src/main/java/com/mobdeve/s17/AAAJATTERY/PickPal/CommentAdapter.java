package com.mobdeve.s17.AAAJATTERY.PickPal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    private List<CommentData> commentList;
    private Context context;

    // Constructor
    public CommentAdapter(Context context, List<CommentData> commentList) {
        this.context = context;
        this.commentList = commentList;
    }

    // ViewHolder class for comment items
    public static class CommentViewHolder extends RecyclerView.ViewHolder {
        public ImageView userPic;
        public TextView username, commentText, timestamp;

        public CommentViewHolder(View itemView) {
            super(itemView);
            userPic = itemView.findViewById(R.id.comment_user_pic);
            username = itemView.findViewById(R.id.comment_username);
            commentText = itemView.findViewById(R.id.comment_text);
            timestamp = itemView.findViewById(R.id.comment_time_stamp);
        }
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate your comment item layout
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_list, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        // Get the current comment data
        CommentData comment = commentList.get(position);

        // Set data to the ViewHolder's views
        holder.username.setText(comment.getUsername());
        holder.commentText.setText(comment.getCommentText());
        holder.timestamp.setText(comment.getTimestamp());

        // Load the user's picture from the resource ID
        holder.userPic.setImageResource(comment.getUserpic());
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }
}