package com.mobdeve.s17.AAAJATTERY.PickPal;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        Button viewModDashBtn = view.findViewById(R.id.viewdashboard);
        viewModDashBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModDash(v);
            }
        });

        return view;
    }

    public void viewModDash(View view) {
        Intent intent = new Intent(getActivity(), ModeratorModule.class);
        startActivity(intent);
    }
}