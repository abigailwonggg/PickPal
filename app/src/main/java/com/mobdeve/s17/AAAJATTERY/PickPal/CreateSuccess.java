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

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button viewPostsButton = findViewById(R.id.view_post_button);
        viewPostsButton.setOnClickListener(v -> openProfileActivity());
    }

    private void openProfileActivity() {
        Intent intent = new Intent(CreateSuccess.this, MainActivity.class);
        intent.putExtra("navigateTo", "Profile");
        startActivity(intent);
    }
}
