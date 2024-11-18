package com.mobdeve.s17.AAAJATTERY.PickPal;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class ModeratorModuleViewMore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_moderator_module_view_more);

        // Set window insets for proper padding with status/navigation bars (optional)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set up the Delete and Dismiss buttons for the single post
        findViewById(R.id.delete_button).setOnClickListener(v -> showDeleteDialog());
        findViewById(R.id.dismiss_button).setOnClickListener(v -> showDismissDialog());  // Corrected the dismiss button ID
    }

    private void showDeleteDialog() {
        // Create and inflate the delete dialog
        final Dialog dialog = new Dialog(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.mod_delete_dialog_box, null);
        dialog.setContentView(dialogView);

        // Set up Cancel and Delete buttons from the inflated layout
        Button btnCancel = dialogView.findViewById(R.id.btn_cancel);
        Button btnDelete = dialogView.findViewById(R.id.btn_delete);

        // Handle Cancel button click
        btnCancel.setOnClickListener(v -> dialog.dismiss());

        // Handle Delete button click
        btnDelete.setOnClickListener(v -> {
            // Handle the action for deleting the post (e.g., remove from the database or UI)
            Toast.makeText(ModeratorModuleViewMore.this, "Post deleted", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
            finish();
        });

        // Show the dialog
        dialog.show();
    }

    // Method to show the dismiss dialog
    private void showDismissDialog() {
        final Dialog dialog = new Dialog(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.mod_dismiss_dialog_box, null);
        dialog.setContentView(dialogView);

        Button btnCancel = dialogView.findViewById(R.id.btn_cancel);
        Button btnDismiss = dialogView.findViewById(R.id.btn_delete);  // Corrected the button ID to btn_dismiss

        btnCancel.setOnClickListener(v -> dialog.dismiss());

        btnDismiss.setOnClickListener(v -> {
            // Handle the action for dismissing the post (e.g., mark as dismissed in the database)
            Toast.makeText(ModeratorModuleViewMore.this, "Post dismissed", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
            finish();
        });

        dialog.show();
    }

    public void bckBtn(View v) {
        finish();
    }
}


