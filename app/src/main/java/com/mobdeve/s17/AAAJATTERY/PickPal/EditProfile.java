package com.mobdeve.s17.AAAJATTERY.PickPal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditProfile extends AppCompatActivity {

    private EditText firstNameEditText, lastNameEditText, usernameEditText;
    private Switch privateAccountSwitch;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        firstNameEditText = findViewById(R.id.edit_first_name);
        lastNameEditText = findViewById(R.id.edit_last_name);
        usernameEditText = findViewById(R.id.edit_username);
        privateAccountSwitch = findViewById(R.id.switch_private_account);
        saveButton = findViewById(R.id.save_button);

        saveButton.setOnClickListener(v -> {
            saveProfileChanges();
        });
    }

    private void saveProfileChanges() {
        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        String username = usernameEditText.getText().toString();
        boolean isPrivate = privateAccountSwitch.isChecked();

        Intent resultIntent = new Intent();
        //Logic here...............
        /*resultIntent.putExtra("firstName", firstName);
        resultIntent.putExtra("lastName", lastName);
        resultIntent.putExtra("username", username);
        resultIntent.putExtra("isPrivate", isPrivate);*/
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    public void bckBtn(View v){
        finish();
    }
}
