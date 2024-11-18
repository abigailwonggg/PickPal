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
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp3Activity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private EditText password, confirmPassword;
    private ImageView imgEyePassword, imgEyeConfirmPassword;
    private Button btnSignUp;
    private CheckBox checkboxAgreement;
    private boolean isPasswordVisible = false;
    private boolean isConfirmPasswordVisible = false;
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up3);

        // Initialize Firebase Auth and Database
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("users");

        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirm_password);
        imgEyePassword = findViewById(R.id.img_eye_icon);
        imgEyeConfirmPassword = findViewById(R.id.cp_eye_icon);
        btnSignUp = findViewById(R.id.btn_signup);
        checkboxAgreement = findViewById(R.id.checkbox_agreement);
        backButton = findViewById(R.id.btn_back);

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
                String email = getIntent().getStringExtra("email");
                registerUser(email, pass); // Register the user with Firebase Auth
            }
        });

        // Handle back button
        backButton.setOnClickListener(v -> finish());

        // Handle window insets for padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void registerUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                saveUserToDatabase(user.getUid());
                            }
                        } else {
                            Toast.makeText(SignUp3Activity.this, "Registration failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void saveUserToDatabase(String uid) {
        String username = getIntent().getStringExtra("username");
        String firstName = getIntent().getStringExtra("firstName");
        String lastName = getIntent().getStringExtra("lastName");
        String email = getIntent().getStringExtra("email");
        String birthday = getIntent().getStringExtra("birthday");
        String gender = getIntent().getStringExtra("gender");

        User user = new User(uid, username, firstName, lastName, email, birthday, gender);

        mDatabase.child(uid).setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Proceed to the next activity or show success
                        Intent intent = new Intent(SignUp3Activity.this, AccountCreated.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignUp3Activity.this, "Failed to save user data.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
