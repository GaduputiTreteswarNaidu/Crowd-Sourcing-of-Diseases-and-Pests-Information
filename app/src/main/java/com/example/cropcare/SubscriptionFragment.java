////package com.example.cropcare;//package com.example.cropcare;
////
////import android.content.Intent;
////import android.os.Bundle;
////import android.view.LayoutInflater;
////import android.view.View;
////import android.view.ViewGroup;
////import androidx.annotation.NonNull;
////import androidx.annotation.Nullable;
////import androidx.fragment.app.Fragment;
////import com.google.android.material.floatingactionbutton.FloatingActionButton;
////
////public class SubscriptionFragment extends Fragment {
////
////    public SubscriptionFragment() {
////        // Required empty public constructor
////    }
////
////    @Nullable
////    @Override
////    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
////        // Inflate the layout for this fragment
////        View rootView = inflater.inflate(R.layout.fragment_subscription, container, false);
////
////        // Initialize the FloatingActionButton
////        FloatingActionButton btn = rootView.findViewById(R.id.add);
////
////        // Set OnClickListener for the button
////        btn.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                // Navigate to AskCommunity activity
////                Intent intent = new Intent(getActivity(), AskCommunity.class);
////                startActivity(intent);
////            }
////        });
////
////        return rootView;
////    }
////}
/*package com.example.cropcare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SubscriptionFragment extends Fragment {

    private LinearLayout questionsLayout;

    public SubscriptionFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_subscription, container, false);

        questionsLayout = rootView.findViewById(R.id.recyclerView);
        FloatingActionButton btn = rootView.findViewById(R.id.fab);

        btn.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AskCommunity.class);
            startActivity(intent);
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadSavedData();
    }

    private void loadSavedData() {
        questionsLayout.removeAllViews();

        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("CommunityData", Context.MODE_PRIVATE);
        String question = sharedPreferences.getString("question", "");
        String description = sharedPreferences.getString("description", "");
        String imageUriString = sharedPreferences.getString("imageUri", "");

        if (!question.isEmpty() && !description.isEmpty()) {
            TextView questionView = new TextView(getActivity());
            questionView.setText("Q: " + question);
            questionView.setTextSize(18);
            questionsLayout.addView(questionView);

            TextView descriptionView = new TextView(getActivity());
            descriptionView.setText("Description: " + description);
            descriptionView.setTextSize(16);
            questionsLayout.addView(descriptionView);

            if (!imageUriString.isEmpty()) {
                try {
                    Uri imageUri = Uri.parse(imageUriString);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT, 500));
                    imageView.setImageURI(imageUri); // Display the image
                    questionsLayout.addView(imageView);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}*/

package com.example.cropcare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SubscriptionFragment extends Fragment {

    private ImageView imageView;
    private TextView textViewQuestion, textViewDescription, textViewUsername, textViewAnswer;
    private FloatingActionButton fab;
    private Button ans_btn;

    public SubscriptionFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_subscription, container, false);

        imageView = rootView.findViewById(R.id.imageView3);
        textViewQuestion = rootView.findViewById(R.id.textViewQuestion);
        textViewDescription = rootView.findViewById(R.id.textViewDescription);
        textViewAnswer = rootView.findViewById(R.id.textViewAnswer);
        textViewUsername = rootView.findViewById(R.id.textViewUsername);
        fab = rootView.findViewById(R.id.fab);
        ans_btn = rootView.findViewById(R.id.buttonAnswer);

        loadDataFromPreferences();

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AskCommunity.class);
            startActivity(intent);
        });

        ans_btn.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), answerQuestions.class);
            startActivity(intent);
        });

        return rootView;
    }

    private void loadDataFromPreferences() {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("CommunityData", Context.MODE_PRIVATE);

        String base64Image = sharedPreferences.getString("image", null);
        String question = sharedPreferences.getString("question", "No question found");
        String description = sharedPreferences.getString("description", "No description found");
        String answer = sharedPreferences.getString("userAnswer", "No answer provided");

        if (base64Image != null) {
            byte[] imageBytes = Base64.decode(base64Image, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            imageView.setImageBitmap(bitmap);
        }

        textViewQuestion.setText(question);
        textViewDescription.setText(description);
        textViewAnswer.setText(answer); // Display the saved answer

        SharedPreferences userPrefs = requireActivity().getSharedPreferences("UserSession", Context.MODE_PRIVATE);
        String username = userPrefs.getString("username", "Unknown User");
        textViewUsername.setText(username); // Set the username
    }
}
