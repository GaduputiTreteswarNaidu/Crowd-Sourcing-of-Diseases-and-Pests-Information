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

public class EXPLOREPAGE_01 extends AppCompatActivity {

    Button btnchangelanguage,btn_exploremore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_explorepage01);
        btnchangelanguage = findViewById(R.id.button1);
        btn_exploremore = findViewById(R.id.button);
        btnchangelanguage.setOnClickListener(view -> {
            Intent intent = new Intent(EXPLOREPAGE_01.this, MainActivity.class);
            startActivity(intent);
        });
        btn_exploremore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EXPLOREPAGE_01.this, EXPLOREPAGE_02.class);
                startActivity(intent);
            }
        });



    }
}