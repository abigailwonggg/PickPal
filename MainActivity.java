package com.mobdeve.s17.AAAJATTERY.PickPal;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Move this line before setContentView
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Window settings
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, android.R.color.black));

        // Initialize the bottom navigation view and the frame layout
        bottomNavigationView = findViewById(R.id.bottom_nav);
        frameLayout = findViewById(R.id.nav_host_fragment);

        // Handle Intent to navigate to ProfileFragment
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("navigateTo")) {
            String navigateTo = intent.getStringExtra("navigateTo");
            if ("Profile".equals(navigateTo)) {
                loadFragment(new ProfileFragment(), false); // Load ProfileFragment
            }
        } else {
            // Load the home fragment initially
            loadFragment(new HomeFragment(), true);
        }

        // Set up bottom navigation listener
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemID = item.getItemId();
            if (itemID == R.id.nav_home) {
                loadFragment(new HomeFragment(), false);
            } else if (itemID == R.id.nav_clubs) {
                loadFragment(new ClubsFragment(), false);
            } else if (itemID == R.id.nav_create) {
                loadFragment(new CreateFragment(), false);
            } else if (itemID == R.id.nav_alerts) {
                loadFragment(new AlertsFragment(), false);
            } else if (itemID == R.id.nav_profile) {
                loadFragment(new ProfileFragment(), false);
            }
            return true;
        });
    }

    // Method to load fragment into the frame layout
    private void loadFragment(Fragment fragment, boolean isAppInitialized) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (isAppInitialized) {
            fragmentTransaction.add(R.id.nav_host_fragment, fragment);
        } else {
            fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
        }
        fragmentTransaction.commit();
    }
}

