package com.mobdeve.s17.AAAJATTERY.PickPal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AccountCreated extends AppCompatActivity {

    private String username, firstName, lastName, email, birthday, gender, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_account_created);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Retrieve data passed from previous activities
        Intent intent = getIntent();
        if (intent != null) {
            username = intent.getStringExtra("username");
            firstName = intent.getStringExtra("firstName");
            lastName = intent.getStringExtra("lastName");
            email = intent.getStringExtra("email");
            birthday = intent.getStringExtra("birthday");
            gender = intent.getStringExtra("gender");
            password = intent.getStringExtra("password");
        }

        Button btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(AccountCreated.this, LoginActivity.class);
                loginIntent.putExtra("username", username);
                loginIntent.putExtra("firstName", firstName);
                loginIntent.putExtra("lastName", lastName);
                loginIntent.putExtra("email", email);
                loginIntent.putExtra("birthday", birthday);
                loginIntent.putExtra("gender", gender);
                loginIntent.putExtra("password", password);
                startActivity(loginIntent);
                finish();
            }
        });
    }
}
