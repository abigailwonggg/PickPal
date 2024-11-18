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

import java.util.ArrayList;
import java.util.List;

public class AlertsFragment extends Fragment {

    private RecyclerView recyclerView;
    private AlertAdapter notificationAdapter;
    private List<AlertData> notificationList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_alerts, container, false);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.rv_notifications);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize data and adapter
        notificationList = new ArrayList<>();
        notificationAdapter = new AlertAdapter(notificationList);
        recyclerView.setAdapter(notificationAdapter);

        // Load notifications into the RecyclerView
        loadNotifications();

        return view;
    }

    private void loadNotifications() {
        // Dummy data for testing purposes
        notificationList.add(new AlertData(
                "mikmik",
                "has requested to follow you.",
                "5 mins ago",
                R.drawable.user_pic,
                AlertData.Type.FOLLOW_REQUEST
        ));

        notificationList.add(new AlertData(
                "mikmik",
                "left a comment on your post.",
                "Both of those choices suck bro!",
                "20 mins ago",
                R.drawable.user_pic,  // Replace with actual icon
                AlertData.Type.COMMENT
        ));

        notificationList.add(new AlertData(
                "kiki",
                "left a comment on your post.",
                "Both of those choices suck bro!",
                "20 mins ago",
                R.drawable.user_pic,
                AlertData.Type.COMMENT
        ));

        notificationList.add(new AlertData(
                "tonton",
                "has requested to follow you.",
                "5 mins ago",
                R.drawable.user_pic,
                AlertData.Type.FOLLOW_REQUEST
        ));

        // Notify adapter that the data has changed so it can update the RecyclerView
        notificationAdapter.notifyDataSetChanged();
    }
}