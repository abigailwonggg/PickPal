package com.mobdeve.s17.AAAJATTERY.PickPal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignUp1Activity extends AppCompatActivity {

    EditText username;
    EditText firstName;
    EditText lastName;
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        username = findViewById(R.id.username);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        email = findViewById(R.id.email);

        ImageView backButton = findViewById(R.id.back_button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button btn_next = findViewById(R.id.btn_next);
        TextView login_link = findViewById(R.id.login_link);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usernameInput = username.getText().toString();
                String fNameInput = firstName.getText().toString();
                String lNameInput = lastName.getText().toString();
                String emailInput = email.getText().toString();

                if (usernameInput.isEmpty() || fNameInput.isEmpty() || lNameInput.isEmpty() || emailInput.isEmpty())  {
                    Toast.makeText(SignUp1Activity.this, "All fields are required. Please complete them.", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(SignUp1Activity.this, SignUp2Activity.class); //Change to Signup2Activity
                    i.putExtra("username", usernameInput);
                    i.putExtra("firstName", fNameInput);
                    i.putExtra("lastName", lNameInput);
                    i.putExtra("email", emailInput);
                    startActivity(i);
                    finish();
                }
            }
        });

        login_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUp1Activity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
