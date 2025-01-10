package com.example.cropcare;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class answerQuestions extends AppCompatActivity {
    private EditText editTextAnswer;
    private Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_answer_questions);
        editTextAnswer = findViewById(R.id.editTextAnswer);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer = editTextAnswer.getText().toString().trim();
                if (!answer.isEmpty()) {
                    saveAnswerToPreferences(answer);
                    Toast.makeText(answerQuestions.this, "Answer saved successfully!", Toast.LENGTH_SHORT).show();
                    finish(); // Close the activity and return to the fragment
                } else {
                    Toast.makeText(answerQuestions.this, "Please enter an answer", Toast.LENGTH_SHORT).show();
                }
            }
        });







    }
    private void saveAnswerToPreferences(String answer) {
        SharedPreferences sharedPreferences = getSharedPreferences("CommunityData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userAnswer", answer); // Save the answer under the key "userAnswer"
        editor.apply();
    }
}