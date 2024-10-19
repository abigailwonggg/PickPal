package com.mobdeve.s17.AAAJATTERY.PickPal;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignUp3Activity extends AppCompatActivity {

    EditText password, confirmPassword;
    ImageView imgEyePassword, imgEyeConfirmPassword;
    Button btnSignUp;
    CheckBox checkboxAgreement;
    boolean isPasswordVisible = false;
    boolean isConfirmPasswordVisible = false;
    ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up3);

        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirm_password);
        imgEyePassword = findViewById(R.id.img_eye_icon);
        imgEyeConfirmPassword = findViewById(R.id.cp_eye_icon);
        btnSignUp = findViewById(R.id.btn_signup);
        checkboxAgreement = findViewById(R.id.checkbox_agreement);
        backButton = findViewById(R.id.btn_back); // Initialize backButton

        // Toggle password visibility for Password
        imgEyePassword.setOnClickListener(v -> {
            if (isPasswordVisible) {
                password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                imgEyePassword.setImageResource(R.drawable.ic_eye_close);
            } else {
                password.setInputType(InputType.TYPE_CLASS_TEXT);
                imgEyePassword.setImageResource(R.drawable.ic_eye_open);
            }
            isPasswordVisible = !isPasswordVisible;
            password.setSelection(password.getText().length());
        });

        // Toggle password visibility for Confirm Password
        imgEyeConfirmPassword.setOnClickListener(v -> {
            if (isConfirmPasswordVisible) {
                confirmPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                imgEyeConfirmPassword.setImageResource(R.drawable.ic_eye_close);
            } else {
                confirmPassword.setInputType(InputType.TYPE_CLASS_TEXT);
                imgEyeConfirmPassword.setImageResource(R.drawable.ic_eye_open);
            }
            isConfirmPasswordVisible = !isConfirmPasswordVisible;
            confirmPassword.setSelection(confirmPassword.getText().length());
        });

        // Sign Up button click listener
        btnSignUp.setOnClickListener(v -> {
            String pass = password.getText().toString();
            String confirmPass = confirmPassword.getText().toString();

            // Check if passwords match
            if (!pass.equals(confirmPass)) {
                Toast.makeText(SignUp3Activity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            } else if (!checkboxAgreement.isChecked()) {
                Toast.makeText(SignUp3Activity.this, "Please agree to the terms", Toast.LENGTH_SHORT).show();
            } else {
                Intent i = new Intent(SignUp3Activity.this, AccountCreated.class);
                i.putExtra("password", pass);
                i.putExtra("birthday", getIntent().getStringExtra("birthday"));
                i.putExtra("gender", getIntent().getStringExtra("gender"));
                i.putExtra("username", getIntent().getStringExtra("username"));
                i.putExtra("firstName", getIntent().getStringExtra("firstName"));
                i.putExtra("lastName", getIntent().getStringExtra("lastName"));
                i.putExtra("email", getIntent().getStringExtra("email"));

                startActivity(i);
                finish(); // Close the current activity
            }
        });

        // need to fix this it terminates the app
        backButton.setOnClickListener(v -> {
            Toast.makeText(SignUp3Activity.this, "Back Button Clicked", Toast.LENGTH_SHORT).show();
            finish();
        });

        // Handle window insets for padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
