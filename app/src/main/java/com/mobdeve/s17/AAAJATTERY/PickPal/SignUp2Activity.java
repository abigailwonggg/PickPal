package com.mobdeve.s17.AAAJATTERY.PickPal;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SignUp2Activity extends AppCompatActivity {

    private EditText birthdayEditText;
    private Spinner genderSpinner;
    private Button btnNext;
    private ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);

        // Initialize UI elements
        genderSpinner = findViewById(R.id.spinner_gender);
        birthdayEditText = findViewById(R.id.birthday);
        btnNext = findViewById(R.id.btn_next);
        backButton = findViewById(R.id.btn_back);

        // Set up gender spinner with an adapter
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(adapter);

        // Default selection
        genderSpinner.setSelection(0);

        // Set up date picker for the birthday field
        birthdayEditText.setOnClickListener(v -> showDatePickerDialog());

        // need to fix this it terminates the app
        backButton.setOnClickListener(v -> finish());

        btnNext.setOnClickListener(v -> {
            String birthdayInput = birthdayEditText.getText().toString();
            String genderInput = genderSpinner.getSelectedItem().toString();

            if (birthdayInput.isEmpty() || genderInput.isEmpty()) {
                Toast.makeText(SignUp2Activity.this, "Please complete all fields.", Toast.LENGTH_SHORT).show();
            } else {
                Intent i = new Intent(SignUp2Activity.this, SignUp3Activity.class);
                i.putExtra("birthday", birthdayInput);
                i.putExtra("gender", genderInput);

                i.putExtra("username", getIntent().getStringExtra("username"));
                i.putExtra("firstName", getIntent().getStringExtra("firstName"));
                i.putExtra("lastName", getIntent().getStringExtra("lastName"));
                i.putExtra("email", getIntent().getStringExtra("email"));

                startActivity(i);
                finish();
            }
        });
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                SignUp2Activity.this,
                (view, selectedYear, selectedMonth, selectedDay) -> {

                    Calendar selectedDate = Calendar.getInstance();
                    selectedDate.set(selectedYear, selectedMonth, selectedDay);

                    // Format the selected date (e.g., "October 17, 2024")
                    SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy", Locale.getDefault());
                    String formattedDate = sdf.format(selectedDate.getTime());

                    birthdayEditText.setText(formattedDate);
                },
                year, month, day
        );
        datePickerDialog.show();
    }
}
