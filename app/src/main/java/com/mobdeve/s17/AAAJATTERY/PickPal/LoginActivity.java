package com.mobdeve.s17.AAAJATTERY.PickPal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ImageView backButton = findViewById(R.id.img_back_button);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView loginButton = findViewById(R.id.txt_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usernameInput = username.getText().toString();
                String passwordInput = password.getText().toString();

                if (usernameInput.isEmpty() || passwordInput.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
                } else {

                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    i.putExtra("username", usernameInput);
                    i.putExtra("password", passwordInput);
                    startActivity(i);
                    finish();
                }
            }
        });
    }
}
