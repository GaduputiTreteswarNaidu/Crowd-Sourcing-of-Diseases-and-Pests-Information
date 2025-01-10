package com.example.cropcare;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CropDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_details);

        // Get the crop name and description passed from the previous Activity
        String cropName = getIntent().getStringExtra("CROP_NAME");
        String cropDescription = getIntent().getStringExtra("CROP_DESCRIPTION");

        // Set the crop name and description in TextViews
        TextView titleText = findViewById(R.id.cropTitle);
        TextView descriptionText = findViewById(R.id.cropDescription);

        titleText.setText(cropName);
        descriptionText.setText(cropDescription);
    }
}
