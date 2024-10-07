package com.mobdeve.s17.AAAJATTERY.PickPal;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class LoginScreen extends MainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_login);

        // Find the back button by its ID
        ImageView backButton = findViewById(R.id.img_back_button);

        // Set an OnClickListener to the back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onViewCreated() {
        // Additional view setup can go here if needed
    }
}
