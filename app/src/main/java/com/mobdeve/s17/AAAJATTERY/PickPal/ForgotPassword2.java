package com.mobdeve.s17.AAAJATTERY.PickPal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ForgotPassword2 extends AppCompatActivity {

    private EditText otpInput1, otpInput2, otpInput3, otpInput4, otpInput5, otpInput6;
    private Button btnVerify;

    // Example predefined OTP for verification (for demonstration purposes)
    private final String predefinedOTP = "123456"; // Replace with your logic for verification

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forgot_password2);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize EditTexts for OTP input
        otpInput1 = findViewById(R.id.otp_input1);
        otpInput2 = findViewById(R.id.otp_input2);
        otpInput3 = findViewById(R.id.otp_input3);
        otpInput4 = findViewById(R.id.otp_input4);
        otpInput5 = findViewById(R.id.otp_input5);
        otpInput6 = findViewById(R.id.otp_input6);

        // Initialize the verify button
        btnVerify = findViewById(R.id.btn_verify);

        // Set the onClickListener to verify the OTP when clicked
        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Capture the OTP input
                String otp = otpInput1.getText().toString() +
                        otpInput2.getText().toString() +
                        otpInput3.getText().toString() +
                        otpInput4.getText().toString() +
                        otpInput5.getText().toString() +
                        otpInput6.getText().toString();

                // Check if the entered OTP matches the predefined OTP
                if (otp.equals(predefinedOTP)) {
                    // Navigate to ForgotPassword3 if the OTP is correct
                    Intent intent = new Intent(ForgotPassword2.this, ForgotPassword3.class);
                    startActivity(intent);
                } else {
                    // Show error message if the OTP is incorrect
                    Toast.makeText(ForgotPassword2.this, "Invalid OTP. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
