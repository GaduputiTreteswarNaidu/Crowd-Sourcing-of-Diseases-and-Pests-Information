package com.example.cropcare;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EXPLOREPAGE_02 extends AppCompatActivity {
    Button btnskip,btnnext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_explorepage02);
        btnskip = findViewById(R.id.button2);
        btnnext = findViewById(R.id.button3);
        btnskip.setOnClickListener(view -> {
            Intent intent = new Intent(EXPLOREPAGE_02.this, Farmer.class);
            startActivity(intent);
        });
        btnnext.setOnClickListener(view -> {
            Intent intent = new Intent(EXPLOREPAGE_02.this, EXPLOREPAGE_01.class);
            startActivity(intent);
        });
        }

}