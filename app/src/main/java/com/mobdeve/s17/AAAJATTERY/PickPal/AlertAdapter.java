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

public class AlertAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<AlertData> notificationList;

    public AlertAdapter(List<AlertData> notificationList) {
        this.notificationList = notificationList;
    }

    // Define view types for the two kinds of notifications
    private static final int VIEW_TYPE_FOLLOW_REQUEST = 0;
    private static final int VIEW_TYPE_COMMENT = 1;

    @Override
    public int getItemViewType(int position) {
        AlertData notification = notificationList.get(position);
        if (notification.getType() == AlertData.Type.FOLLOW_REQUEST) {
            return VIEW_TYPE_FOLLOW_REQUEST;
        } else {
            return VIEW_TYPE_COMMENT;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_FOLLOW_REQUEST) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_follow_request, parent, false);
            return new FollowRequestViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
            return new CommentViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        AlertData notification = notificationList.get(position);

        if (holder instanceof FollowRequestViewHolder) {
            ((FollowRequestViewHolder) holder).bind(notification);
        } else if (holder instanceof CommentViewHolder) {
            ((CommentViewHolder) holder).bind(notification);
        }
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    // ViewHolder for Follow Request notification
    static class FollowRequestViewHolder extends RecyclerView.ViewHolder {
        TextView username, text, time;
        ImageView userIcon; // ImageView for the user's profile picture
        Button acceptButton, declineButton;

        public FollowRequestViewHolder(@NonNull View itemView) {
            super(itemView);
            userIcon = itemView.findViewById(R.id.user_icon); // Initialize user icon
            username = itemView.findViewById(R.id.alert_username);
            text = itemView.findViewById(R.id.alert_text);
            time = itemView.findViewById(R.id.alert_time);
            acceptButton = itemView.findViewById(R.id.btn_accept);
            declineButton = itemView.findViewById(R.id.btn_decline);
        }

        public void bind(AlertData notification) {
            userIcon.setImageResource(notification.getUserIcon()); // Set the user icon
            username.setText(notification.getUsername());
            text.setText(notification.getText());
            time.setText(notification.getTime());

            // Implement click listeners for accept and decline buttons
            acceptButton.setOnClickListener(v -> {
                // Handle the accept action
                // TODO: Implement your accept follow request logic
            });

            declineButton.setOnClickListener(v -> {
                // Handle the decline action
                // TODO: Implement your decline follow request logic
            });
        }
    }

    // ViewHolder for Comment notification
    static class CommentViewHolder extends RecyclerView.ViewHolder {
        TextView username, text, comment, time;
        ImageView userIcon; // ImageView for the user's profile picture

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            userIcon = itemView.findViewById(R.id.user_icon); // Initialize user icon
            username = itemView.findViewById(R.id.alert_username);
            text = itemView.findViewById(R.id.alert_text);
            comment = itemView.findViewById(R.id.alert_content);
            time = itemView.findViewById(R.id.alert_time);
        }

        public void bind(AlertData notification) {
            userIcon.setImageResource(notification.getUserIcon());
            username.setText(notification.getUsername());
            text.setText(notification.getText());
            comment.setText(notification.getComment());
            time.setText(notification.getTime());
        }
    }
}


