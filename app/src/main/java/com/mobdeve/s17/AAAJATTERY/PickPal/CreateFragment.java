package com.mobdeve.s17.AAAJATTERY.PickPal;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CreateFragment extends Fragment {

    private LinearLayout option3, option4;
    private Button btnAddOption, btnPostPoll;
    private EditText option1Text, option2Text, option3Text, option4Text;
    private Spinner spinnerClub;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create, container, false);

        // Initialize views
        option3 = view.findViewById(R.id.option3);
        option4 = view.findViewById(R.id.option4);
        btnAddOption = view.findViewById(R.id.btn_add_option);
        btnPostPoll = view.findViewById(R.id.btn_post_poll);

        option1Text = view.findViewById(R.id.option_1);
        option2Text = view.findViewById(R.id.option_2);
        option3Text = view.findViewById(R.id.option_3);
        option4Text = view.findViewById(R.id.option_4);

        ImageButton removeOption3 = view.findViewById(R.id.remove_option_3);
        ImageButton removeOption4 = view.findViewById(R.id.remove_option_4);

        // Spinner for selecting clubs
        spinnerClub = view.findViewById(R.id.spinner_club);
        String[] clubs = {"Select Club", "Club A", "Club B", "Club C"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, clubs);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClub.setAdapter(adapter);

        // Set OnItemSelectedListener for the spinner
        spinnerClub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedClub = parent.getItemAtPosition(position).toString();
                // Do something with the selected club (e.g., display it or use it later)
                Toast.makeText(getContext(), "Selected Club: " + selectedClub, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case when nothing is selected (optional)
            }
        });

        // Set OnClickListeners for buttons
        btnAddOption.setOnClickListener(v -> addOption());

        removeOption3.setOnClickListener(v -> {
            option3.setVisibility(View.GONE);
            option3Text.setText("");  // Clear text when option is removed
        });

        removeOption4.setOnClickListener(v -> {
            option4.setVisibility(View.GONE);
            option4Text.setText("");  // Clear text when option is removed
        });

        // Set OnClickListeners for buttons
        btnAddOption.setOnClickListener(v -> addOption());

        removeOption3.setOnClickListener(v -> {
            option3.setVisibility(View.GONE);
            option3Text.setText("");  // Clear text when option is removed
            checkAddButtonVisibility(); // Check visibility after removing
        });

        removeOption4.setOnClickListener(v -> {
            option4.setVisibility(View.GONE);
            option4Text.setText("");  // Clear text when option is removed
            checkAddButtonVisibility(); // Check visibility after removing
        });

        btnPostPoll.setOnClickListener(v -> postPoll());

        // Check button visibility initially
        checkAddButtonVisibility();

        return view;
    }

    // Function to add options
    private void addOption() {
        if (option3.getVisibility() == View.GONE) {
            option3.setVisibility(View.VISIBLE);
        } else if (option4.getVisibility() == View.GONE) {
            option4.setVisibility(View.VISIBLE);
        }

        // Check button visibility after adding
        checkAddButtonVisibility();
    }

    // Function to handle poll posting
    private void postPoll() {
        // Retrieve user input
        String option1 = option1Text.getText().toString().trim();
        String option2 = option2Text.getText().toString().trim();
        String option3 = option3Text.getText().toString().trim();
        String option4 = option4Text.getText().toString().trim();

        // Check if required fields are filled (basic validation)
        if (option1.isEmpty() || option2.isEmpty()) {
            Toast.makeText(getContext(), "Please fill at least the first two options.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Code to handle poll posting goes here
        // You could send data to your backend or update the UI accordingly
        Toast.makeText(getContext(), "Poll posted with options: " + option1 + ", " + option2, Toast.LENGTH_SHORT).show();

        Intent i = new Intent(getActivity(), CreateSuccess.class);
        startActivity(i);
    }

    // Function to check and set visibility of the add button
    private void checkAddButtonVisibility() {
        if (option3.getVisibility() == View.VISIBLE && option4.getVisibility() == View.VISIBLE) {
            btnAddOption.setVisibility(View.GONE);
        } else {
            btnAddOption.setVisibility(View.VISIBLE);
        }
    }
}