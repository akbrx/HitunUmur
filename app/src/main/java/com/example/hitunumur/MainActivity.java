package com.example.hitunumur;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnStart, btnEnd;
    EditText edName;
    DatePickerDialog.OnDateSetListener dateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btnStart);
        btnEnd = findViewById(R.id.btnEnd);
        Button btnCalculation = findViewById(R.id.btnCalculation);
        edName = findViewById(R.id.edName);

        btnCalculation.setOnClickListener(this);

        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String datenow = simpleDateFormat.format(calendar.getTime());

        btnEnd.setText(datenow);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        dateSetListener, year, month, day);
                datePickerDialog.show();
            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // Do something when a date is selected
                String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                btnStart.setText(selectedDate);
            }
        };
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnCalculation){
            // Get name from EditText
            String name = edName.getText().toString().trim();

            // Get selected date from btnStart
            String dobString = btnStart.getText().toString();

            // Convert selected date string to Date object
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date dob;
            try {
                dob = format.parse(dobString);
            } catch (ParseException e) {
                e.printStackTrace();
                return;
            }

            // Calculate age
            Calendar dobCal = Calendar.getInstance();
            dobCal.setTime(dob);
            Calendar today = Calendar.getInstance();

            int ageYears = today.get(Calendar.YEAR) - dobCal.get(Calendar.YEAR);
            int ageMonths = today.get(Calendar.MONTH) - dobCal.get(Calendar.MONTH);
            int ageDays = today.get(Calendar.DAY_OF_MONTH) - dobCal.get(Calendar.DAY_OF_MONTH);

            if (ageMonths < 0 || (ageMonths == 0 && ageDays < 0)) {
                ageYears--;
                ageMonths = (12 + ageMonths) % 12;
                ageDays = (today.getActualMaximum(Calendar.DAY_OF_MONTH) - dobCal.get(Calendar.DAY_OF_MONTH)) + today.get(Calendar.DAY_OF_MONTH);
            }

            // Create intent and send data to Hasil activity
            Intent intent = new Intent(this, Hasil.class);
            intent.putExtra("Name", name);
            intent.putExtra("DOB", dobString);
            intent.putExtra("AgeYears", ageYears);
            intent.putExtra("AgeMonths", ageMonths);
            intent.putExtra("AgeDays", ageDays);
            startActivity(intent);
        }
    }
}
