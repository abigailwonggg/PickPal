package com.mobdeve.s17.AAAJATTERY.PickPal;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton; // Import ImageButton
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ForgotPassword3 extends AppCompatActivity {

    EditText password, confirmPassword;
    ImageView imgEyePassword, imgEyeConfirmPassword;
    Button btnUpdatePassword;
    ImageButton backButton; // Declare backButton
    boolean isPasswordVisible = false;
    boolean isConfirmPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forgot_password3);

        // Initialize views
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirm_password);
        imgEyePassword = findViewById(R.id.img_eye_icon);
        imgEyeConfirmPassword = findViewById(R.id.cp_eye_icon);
        btnUpdatePassword = findViewById(R.id.btn_update_password);
        backButton = findViewById(R.id.btn_back); // Initialize backButton

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
            } else {
                // Implement password update logic here
                // For example: send the new password to the server or save it locally
                Toast.makeText(ForgotPassword3.this, "Password updated successfully", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ForgotPassword3.this, PasswordReset.class);
                intent.putExtra("password", pass);
                startActivity(intent);
                finish(); // Close the current activity
            }
        });

        // need to fix this it terminates the app
        backButton.setOnClickListener(v -> onBackPressed()); // Go back to the previous activity

        // Adjust padding for the main layout to avoid content overlap with system UI
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
