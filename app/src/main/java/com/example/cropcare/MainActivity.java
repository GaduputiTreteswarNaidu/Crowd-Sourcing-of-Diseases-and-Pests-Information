package com.example.cropcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btnEnglish, btnTelugu, btnHindi, btnKannada;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        btnEnglish = findViewById(R.id.btn_english);
        btnTelugu = findViewById(R.id.btn_telugu);
        btnHindi = findViewById(R.id.btn_hindi);
        btnKannada = findViewById(R.id.btn_kannada);
        btnEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EXPLOREPAGE_01.class);
                // start the activity connect to the specified class
                startActivity(intent);
            }
        });
    }
}
