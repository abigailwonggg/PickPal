package com.mobdeve.s17.AAAJATTERY.PickPal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginScreen extends AppCompatActivity {
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
                finish(); // Close login screen when back button is pressed
            }
        });

        // Find the login button (assuming there's a login button for logging in)
        TextView loginButton = findViewById(R.id.txt_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start MainActivity after login
                Intent intent = new Intent(LoginScreen.this, MainActivity.class);
                startActivity(intent);
                finish(); // Close LoginScreen so it won't appear when back button is pressed
            }
        });
    }
}

