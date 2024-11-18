package com.mobdeve.s17.AAAJATTERY.PickPal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword1 extends AppCompatActivity {

    private EditText emailInput;
    private Button btnReset;
    private ImageButton backButton;
    private FirebaseAuth mAuth; // Firebase authentication instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password1);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Initialize views
        emailInput = findViewById(R.id.email);
        btnReset = findViewById(R.id.btn_reset);
        backButton = findViewById(R.id.btn_back);

        // Set click listener for the reset button
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the inputted email
                String email = emailInput.getText().toString().trim();

                // Check if email is empty
                if (email.isEmpty()) {
                    Toast.makeText(ForgotPassword1.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                } else {
                    // Send password reset email
                    mAuth.sendPasswordResetEmail(email)
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    Toast.makeText(ForgotPassword1.this, "Password reset email sent", Toast.LENGTH_SHORT).show();

                                    // Move to ForgotPassword2 after email is sent
                                    Intent intent = new Intent(ForgotPassword1.this, ForgotPassword2.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(ForgotPassword1.this, "Failed to send reset email. Please try again.", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });

        // Back button functionality
        backButton.setOnClickListener(v -> onBackPressed());
    }
}
