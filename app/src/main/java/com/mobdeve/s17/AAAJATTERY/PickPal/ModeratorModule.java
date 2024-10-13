package com.mobdeve.s17.AAAJATTERY.PickPal;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ModeratorModule extends AppCompatActivity implements ReportAdapter.OnReportActionListener {

    private RecyclerView recyclerView;
    private ReportAdapter reportAdapter;
    private List<ReportData> reportList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moderator_module);

        recyclerView = findViewById(R.id.recycler_view_reports);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the report list with dummy data
        reportList = new ArrayList<>();
        reportList.add(new ReportData(R.drawable.user_pic, "Dingdong Dantes", "Which Justin Bieber album is better?"));
        reportList.add(new ReportData(R.drawable.user_pic, "Jenny Chua", "What dress should I wear for date night?"));
        reportList.add(new ReportData(R.drawable.user_pic, "Dina Lily Go", "Choose the right answer!"));

        // Set up the adapter with the listener
        reportAdapter = new ReportAdapter(reportList, this);
        recyclerView.setAdapter(reportAdapter);
    }

    @Override
    public void onDeleteClicked(int position) {
        showDeleteDialog(position);
    }

    @Override
    public void onDismissClicked(int position) {
        showDismissDialog(position);
    }

    // Method to show the delete dialog
    private void showDeleteDialog(int position) {
        // Create and inflate the delete dialog
        final Dialog dialog = new Dialog(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.mod_delete_dialog_box, null);
        dialog.setContentView(dialogView);

        // Set up Cancel and Delete buttons
        Button btnCancel = dialogView.findViewById(R.id.btn_cancel);
        Button btnDelete = dialogView.findViewById(R.id.btn_delete);

        // Handle Cancel button click
        btnCancel.setOnClickListener(v -> dialog.dismiss());

        // Handle Delete button click
        btnDelete.setOnClickListener(v -> {
            // Remove the item from the list
            reportList.remove(position);
            reportAdapter.notifyItemRemoved(position);
            Toast.makeText(ModeratorModule.this, "Report deleted", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

        // Show the dialog
        dialog.show();
    }

    // Method to show the dismiss dialog
    private void showDismissDialog(int position) {
        // Create and inflate the dismiss dialog
        final Dialog dialog = new Dialog(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.mod_dismiss_dialog_box, null);
        dialog.setContentView(dialogView);

        // Set up Cancel and Dismiss buttons
        Button btnCancel = dialogView.findViewById(R.id.btn_cancel);
        Button btnDismiss = dialogView.findViewById(R.id.btn_delete);

        // Handle Cancel button click
        btnCancel.setOnClickListener(v -> dialog.dismiss());

        // Handle Dismiss button click
        btnDismiss.setOnClickListener(v -> {
            // You can also remove the item or take another action here
            Toast.makeText(ModeratorModule.this, "Report dismissed", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

        // Show the dialog
        dialog.show();
    }

    public void bckBtn(View v) {
        finish();
    }
}