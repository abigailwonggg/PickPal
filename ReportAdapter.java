package com.mobdeve.s17.AAAJATTERY.PickPal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ReportViewHolder> {

    private List<ReportData> reportList;
    private OnReportActionListener actionListener;

    // Define the interface for report actions
    public interface OnReportActionListener {
        void onDeleteClicked(int position);
        void onDismissClicked(int position);
        void onViewMoreClicked(int position);  // New method for handling View More
    }

    public ReportAdapter(List<ReportData> reportList, OnReportActionListener actionListener) {
        this.reportList = reportList;
        this.actionListener = actionListener;
    }

    @NonNull
    @Override
    public ReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_list, parent, false);
        return new ReportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportViewHolder holder, int position) {
        ReportData report = reportList.get(position);
        holder.userIcon.setImageResource(report.getUserIcon());
        holder.userName.setText(report.getUserName());
        holder.postTitle.setText(report.getPostTitle());

        // Handle the View More button click
        holder.viewMoreButton.setOnClickListener(v -> {
            if (actionListener != null) {
                actionListener.onViewMoreClicked(position);  // Notify the listener
            }
        });

        // Handle the Delete button click
        holder.deleteButton.setOnClickListener(v -> {
            if (actionListener != null) {
                actionListener.onDeleteClicked(position);
            }
        });

        // Handle the Dismiss button click
        holder.dismissButton.setOnClickListener(v -> {
            if (actionListener != null) {
                actionListener.onDismissClicked(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return reportList.size();
    }

    public static class ReportViewHolder extends RecyclerView.ViewHolder {
        ShapeableImageView userIcon;
        TextView userName, postTitle;
        Button viewMoreButton, deleteButton, dismissButton;

        public ReportViewHolder(@NonNull View itemView) {
            super(itemView);
            userIcon = itemView.findViewById(R.id.user_icon);
            userName = itemView.findViewById(R.id.user_name);
            postTitle = itemView.findViewById(R.id.post_title);
            viewMoreButton = itemView.findViewById(R.id.view_more_button);
            deleteButton = itemView.findViewById(R.id.delete_button);
            dismissButton = itemView.findViewById(R.id.dismiss_button);
        }
    }
}



