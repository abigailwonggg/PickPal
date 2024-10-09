package com.mobdeve.s17.AAAJATTERY.PickPal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_welcome_screen);

        // Find the login button by its ID
        LinearLayout loginButton = findViewById(R.id.container_login_button);

        // Set an OnClickListener to the login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the LoginScreen activity
                Intent intent = new Intent(WelcomeScreen.this, LoginScreen.class);
                startActivity(intent);
                finish(); // Close WelcomeScreen so it won't appear when back button is pressed
            }
        });
    }
}
