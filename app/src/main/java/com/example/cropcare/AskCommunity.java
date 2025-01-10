package com.example.cropcare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AskCommunity extends AppCompatActivity {

    private Button buttonAddImage, buttonSend;
    private EditText editTextQuestion, editTextDescription;
    private ImageView imageViewPreview;
    private Bitmap selectedBitmap;

    private final ActivityResultLauncher<String> galleryLauncher =
            registerForActivityResult(new ActivityResultContracts.GetContent(), result -> {
                if (result != null) {
                    try {
                        selectedBitmap = decodeBitmap(result);
                        imageViewPreview.setImageBitmap(selectedBitmap);
                    } catch (IOException e) {
                        Toast.makeText(this, "Failed to load image", Toast.LENGTH_SHORT).show();
                        Log.e("ImageError", "Error decoding image: ", e);
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        if (!isLoggedIn) {
            // Redirect to LoginActivity if not logged in
            Intent intent = new Intent(AskCommunity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Close AskCommunity activity


        }

        setContentView(R.layout.activity_askcommunity);

        buttonAddImage = findViewById(R.id.buttonAddImage);
        buttonSend = findViewById(R.id.buttonSend);
        editTextQuestion = findViewById(R.id.editTextQuestion);
        editTextDescription = findViewById(R.id.editTextDescription);
        imageViewPreview = findViewById(R.id.imageViewPreview);

        buttonAddImage.setOnClickListener(v -> galleryLauncher.launch("image/*"));

        buttonSend.setOnClickListener(v -> {
            String question = editTextQuestion.getText().toString().trim();
            String description = editTextDescription.getText().toString().trim();

            if (question.isEmpty() || description.isEmpty() || selectedBitmap == null) {
                Toast.makeText(this, "Please fill all fields and select an image!", Toast.LENGTH_SHORT).show();
                return;
            }

            saveDataToPreferences(question, description, selectedBitmap);
        });
    }

    private Bitmap decodeBitmap(Uri uri) throws IOException {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            ImageDecoder.Source source = ImageDecoder.createSource(getContentResolver(), uri);
            return ImageDecoder.decodeBitmap(source, (decoder, info, source1) -> decoder.setAllocator(ImageDecoder.ALLOCATOR_SOFTWARE));
        } else {
            return MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
        }
    }

    private void saveDataToPreferences(String question, String description, Bitmap bitmap) {
        // Convert the bitmap to Base64 string
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
        String base64Image = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);

        // Save data in SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("CommunityData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("image", base64Image);
        editor.putString("question", question);
        editor.putString("description", description);
        editor.apply();

        Toast.makeText(this, "Data saved successfully!", Toast.LENGTH_SHORT).show();
        finish(); // Close activity
    }
}





