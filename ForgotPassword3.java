package com.mobdeve.s17.AAAJATTERY.PickPal;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ForgotPassword3 extends AppCompatActivity {

    private EditText password, confirmPassword;
    private ImageView imgEyePassword, imgEyeConfirmPassword;
    private Button btnUpdatePassword;
    private ImageButton backButton;
    private boolean isPasswordVisible = false;
    private boolean isConfirmPasswordVisible = false;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password3);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Initialize views
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirm_password);
        imgEyePassword = findViewById(R.id.img_eye_icon);
        imgEyeConfirmPassword = findViewById(R.id.cp_eye_icon);
        btnUpdatePassword = findViewById(R.id.btn_update_password);
        backButton = findViewById(R.id.btn_back);

        // Set up password visibility toggle for the password field
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

        // Set up password visibility toggle for the confirm password field
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

        // Handle update password button click
        btnUpdatePassword.setOnClickListener(v -> {
            String pass = password.getText().toString();
            String confirmPass = confirmPassword.getText().toString();

            // Check if passwords match
            if (!pass.equals(confirmPass)) {
                Toast.makeText(ForgotPassword3.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            } else if (pass.length() < 6) {
                Toast.makeText(ForgotPassword3.this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            } else {
                updatePassword(pass);
            }
        });

        // Back button functionality
        backButton.setOnClickListener(v -> onBackPressed());
    }

    private void updatePassword(String newPassword) {
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            // Update the password
            user.updatePassword(newPassword)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(ForgotPassword3.this, "Password updated successfully", Toast.LENGTH_SHORT).show();

                            // Redirect to a confirmation screen or login page
                            Intent intent = new Intent(ForgotPassword3.this, PasswordReset.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(ForgotPassword3.this, "Failed to update password. Try logging in again.", Toast.LENGTH_SHORT).show();

                            // Fallback option: send reset email again if update fails
                            mAuth.sendPasswordResetEmail(user.getEmail())
                                    .addOnCompleteListener(resetTask -> {
                                        if (resetTask.isSuccessful()) {
                                            Toast.makeText(ForgotPassword3.this, "Password reset email re-sent. Check your email.", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(ForgotPassword3.this, "Could not re-send reset email.", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    });
        } else {
            Toast.makeText(ForgotPassword3.this, "User not authenticated. Please log in again.", Toast.LENGTH_SHORT).show();
        }
    }

}
