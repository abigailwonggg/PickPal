package com.mobdeve.s17.AAAJATTERY.PickPal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.imageview.ShapeableImageView;

public class EditProfile extends AppCompatActivity {

    private EditText firstNameEditText, lastNameEditText, usernameEditText;
    private Switch privateAccountSwitch;
    private Button saveButton;
    private ShapeableImageView profileImage;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        profileImage = findViewById(R.id.profile_image);
        firstNameEditText = findViewById(R.id.edit_first_name);
        lastNameEditText = findViewById(R.id.edit_last_name);
        usernameEditText = findViewById(R.id.edit_username);
        privateAccountSwitch = findViewById(R.id.switch_private_account);
        saveButton = findViewById(R.id.save_button);

        // Handle profile image click to open image picker
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagePicker();
            }
        });

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

    // Method to open image picker
    private void openImagePicker() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    // Handle the result of the image picker
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();  // Get the image URI
            profileImage.setImageURI(imageUri);  // Set the selected image in the ImageView
        }
    }
}
