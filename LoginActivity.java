package com.mobdeve.s17.AAAJATTERY.PickPal;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private ImageView eyeIcon;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth and Database
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("users");

        // Initialize views
        username = findViewById(R.id.username);  // Username field
        password = findViewById(R.id.password);
        eyeIcon = findViewById(R.id.img_eye_icon);
        ImageView backButton = findViewById(R.id.btn_back);

        backButton.setOnClickListener(v -> finish());

        Button btn_login = findViewById(R.id.btn_login);
        TextView signup_link = findViewById(R.id.signup_link);
        TextView txt_forgot_password = findViewById(R.id.txt_forgot_password);

        btn_login.setOnClickListener(v -> {
            String usernameInput = username.getText().toString();
            String passwordInput = password.getText().toString();

            if (usernameInput.isEmpty() || passwordInput.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
            } else {
                // Query the database to find the email associated with the username
                mDatabase.orderByChild("username").equalTo(usernameInput)
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    // Retrieve the email associated with the username
                                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                                        String email = userSnapshot.child("email").getValue(String.class);
                                        if (email != null) {
                                            // Authenticate with Firebase using the retrieved email
                                            loginWithEmail(email, passwordInput);
                                        }
                                        break;
                                    }
                                } else {
                                    Toast.makeText(LoginActivity.this, "Username not found", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(LoginActivity.this, "Database error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        signup_link.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignUp1Activity.class);
            startActivity(intent);
            finish();
        });

        txt_forgot_password.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, ForgotPassword1.class);
            startActivity(intent);
            finish();
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

    private void loginWithEmail(String email, String passwordInput) {
        mAuth.signInWithEmailAndPassword(email, passwordInput)
                .addOnCompleteListener(LoginActivity.this, task -> {
                    if (task.isSuccessful()) {
                        // Sign-in success, go to MainActivity
                        FirebaseUser user = mAuth.getCurrentUser();
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    } else {
                        // If sign-in fails, display a message to the user
                        Toast.makeText(LoginActivity.this, "Authentication failed. Please check your credentials.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
