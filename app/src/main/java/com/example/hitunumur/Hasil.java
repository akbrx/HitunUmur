package com.example.hitunumur;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Hasil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);

        Intent intent = getIntent();
        String name = intent.getStringExtra("Name");
        String dob = intent.getStringExtra("DOB");
        int ageYears = intent.getIntExtra("AgeYears", 0);
        int ageMonths = intent.getIntExtra("AgeMonths", 0);
        int ageDays = intent.getIntExtra("AgeDays", 0);

        TextView tvName = findViewById(R.id.tvName);
        TextView tvDOB = findViewById(R.id.tvDOB);
        TextView tvAge = findViewById(R.id.tvAge);

        tvName.setText("nama anda : " + name);
        tvDOB.setText("tgl lahir  : " + dob);
        tvAge.setText("umur anda  : " + ageYears + " tahun, " + ageMonths + " bulan, dan " + ageDays + " hari");
    }
}
