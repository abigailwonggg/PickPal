package com.mobdeve.s17.AAAJATTERY.PickPal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class WelcomeScreen extends MainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_welcome_screen);

        // Find the FrameLayout by its ID
        LinearLayout loginButton = findViewById(R.id.container_login_button);

        // Set an OnClickListener to the login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the LoginScreen activity
                Intent intent = new Intent(WelcomeScreen.this, LoginScreen.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onViewCreated() {
    }
}
