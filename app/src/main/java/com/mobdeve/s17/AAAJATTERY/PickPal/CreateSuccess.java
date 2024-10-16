package com.mobdeve.s17.AAAJATTERY.PickPal;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;

public class CreateSuccess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_success);

        // Apply window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Find the button by its ID and set an onClickListener
        Button viewPostsButton = findViewById(R.id.view_post_button);
        viewPostsButton.setOnClickListener(v -> openProfileActivity());
    }

    private void openProfileActivity() {
        // Start the activity that hosts the ProfileFragment (MainActivity in this case)
        Intent intent = new Intent(CreateSuccess.this, MainActivity.class); // Replace MainActivity.class with your actual activity
        intent.putExtra("navigateTo", "Profile"); // Optional: Pass data to tell MainActivity to open ProfileFragment
        startActivity(intent);
    }
}
