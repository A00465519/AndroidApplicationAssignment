package com.example.stayeasyhalifax.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.stayeasyhalifax.R;
import com.example.stayeasyhalifax.ui.fragments.SearchHomeFragment;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Begin the transaction
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        // Replace the contents of the container with the new fragment
        fragmentTransaction.replace(R.id.frame_layout, new SearchHomeFragment());

        // Complete the changes added above
        fragmentTransaction.commit();


        // In your main activity or layout
//        EditText checkInDateEditText = findViewById(R.id.check_in_date_edit_text);
//        EditText checkOutDateEditText = findViewById(R.id.check_out_date_edit_text);
//        Button checkInDateButton = findViewById(R.id.check_in_date_button);
//        Button checkOutDateButton = findViewById(R.id.check_out_date_button

//        checkInDateButton.setOnClickListener(view -> {
//            // Create a new DatePickerDialog for check-in date
//            DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
//                @Override
//                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//                    // Update the check-in date text field with the selected date
//                    @SuppressLint("DefaultLocale") String selectedDate = String.format("%02d/%02d/%d", day, month + 1, year);
//                    checkInDateEditText.setText(selectedDate);
//                }
//            }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
//            // Set the title and show the dialog
//            datePickerDialog.setTitle("Select check-in date");
//            datePickerDialog.show();
//        });
//
//        checkOutDateButton.setOnClickListener(view -> {
//            // Create a new DatePickerDialog for check-out date
//            DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
//                @Override
//                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//                    // Update the check-out date text field with the selected date
//                    @SuppressLint("DefaultLocale") String selectedDate = String.format("%02d/%02d/%d", day, month + 1, year);
//                    checkOutDateEditText.setText(selectedDate);
//                }
//            }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
//            // Set the title and show the dialog
//            datePickerDialog.setTitle("Select check-out date");
//            datePickerDialog.show();
//        });

    }
}