package com.mobdeve.s17.AAAJATTERY.PickPal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp1Activity extends AppCompatActivity {

    private EditText username;
    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up1);


        username = findViewById(R.id.username);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        email = findViewById(R.id.email);
        backButton = findViewById(R.id.btn_back);

        Button btn_next = findViewById(R.id.btn_next);
        TextView login_link = findViewById(R.id.login_link);

        // Back button functionality
        backButton.setOnClickListener(v -> finish());

        // Next button click listener to pass data to SignUp2Activity
        btn_next.setOnClickListener(v -> {
            String usernameInput = username.getText().toString();
            String fNameInput = firstName.getText().toString();
            String lNameInput = lastName.getText().toString();
            String emailInput = email.getText().toString();

            if (usernameInput.isEmpty() || fNameInput.isEmpty() || lNameInput.isEmpty() || emailInput.isEmpty()) {
                Toast.makeText(SignUp1Activity.this, "All fields are required. Please complete them.", Toast.LENGTH_SHORT).show();
            } else {
                // Passing user data to SignUp2Activity
                Intent i = new Intent(SignUp1Activity.this, SignUp2Activity.class);
                i.putExtra("username", usernameInput);
                i.putExtra("firstName", fNameInput);
                i.putExtra("lastName", lNameInput);
                i.putExtra("email", emailInput);
                startActivity(i);
                finish();
            }
        });

        // Redirect to login if user clicks login link
        login_link.setOnClickListener(v -> {
            Intent i = new Intent(SignUp1Activity.this, LoginActivity.class);
            startActivity(i);
            finish();
        });
    }
}
