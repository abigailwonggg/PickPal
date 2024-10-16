package com.mobdeve.s17.AAAJATTERY.PickPal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotPassword1 extends AppCompatActivity {

    private EditText emailInput;
    private Button btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password1);

        // Initialize views
        emailInput = findViewById(R.id.email);
        btnReset = findViewById(R.id.btn_reset);

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
                    // Proceed to the next activity
                    Intent intent = new Intent(ForgotPassword1.this, ForgotPassword2.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
