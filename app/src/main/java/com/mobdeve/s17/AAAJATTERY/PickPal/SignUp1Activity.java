package com.mobdeve.s17.AAAJATTERY.PickPal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignUp1Activity extends AppCompatActivity {

    EditText username;
    EditText firstName;
    EditText lastName;
    EditText email;
    ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up1);

        // Set padding to avoid overlap with system UI
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        username = findViewById(R.id.username);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        email = findViewById(R.id.email);

        // need to fix this it terminates the app
        ImageView backButton = findViewById(R.id.btn_back);
        backButton.setOnClickListener(v -> finish()); // Go back to the previous activity

        // Initialize next button and login link
        Button btn_next = findViewById(R.id.btn_next);
        TextView login_link = findViewById(R.id.login_link);

        // Handle next button click
        btn_next.setOnClickListener(v -> {
            String usernameInput = username.getText().toString();
            String fNameInput = firstName.getText().toString();
            String lNameInput = lastName.getText().toString();
            String emailInput = email.getText().toString();

            // Validate input fields
            if (usernameInput.isEmpty() || fNameInput.isEmpty() || lNameInput.isEmpty() || emailInput.isEmpty()) {
                Toast.makeText(SignUp1Activity.this, "All fields are required. Please complete them.", Toast.LENGTH_SHORT).show();
            } else {
                Intent i = new Intent(SignUp1Activity.this, SignUp2Activity.class);
                i.putExtra("username", usernameInput);
                i.putExtra("firstName", fNameInput);
                i.putExtra("lastName", lNameInput);
                i.putExtra("email", emailInput);
                startActivity(i);
                finish();
            }
        });

        // Handle login link click
        login_link.setOnClickListener(v -> {
            Intent i = new Intent(SignUp1Activity.this, LoginActivity.class);
            startActivity(i);
            finish();
        });
    }
}
