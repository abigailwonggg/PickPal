package com.mobdeve.s17.AAAJATTERY.PickPal;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    ImageView eyeIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ImageView backButton = findViewById(R.id.back_button);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        eyeIcon = findViewById(R.id.img_eye_icon);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button btn_login = findViewById(R.id.btn_login);
        TextView signup_link = findViewById(R.id.signup_link);
        TextView txt_forgot_password = findViewById(R.id.txt_forgot_password);

        btn_login.setOnClickListener(new View.OnClickListener() {
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

        signup_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUp1Activity.class);
                startActivity(intent);
                finish();
            }
        });

        txt_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPassword1.class);
                startActivity(intent);
                finish();
            }
        });
        eyeIcon.setOnClickListener(new View.OnClickListener() {
            boolean isPasswordVisible = false;

            @Override
            public void onClick(View v) {
                if (isPasswordVisible) {
                    // Set password to hidden
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    eyeIcon.setImageResource(R.drawable.ic_eye_close);
                } else {
                    // Set password to visible
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    eyeIcon.setImageResource(R.drawable.ic_eye_open);
                }
                password.setSelection(password.getText().length());
                isPasswordVisible = !isPasswordVisible;
            }
        });
    }
}