package com.example.cropcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // Find the ImageButton elements
        ImageButton grapesButton = rootView.findViewById(R.id.imageButton2);
        ImageButton bananaButton = rootView.findViewById(R.id.imageButton3);
        ImageButton orangeButton = rootView.findViewById(R.id.imageButton4);

        // Set click listeners for each ImageButton
        grapesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), grapesActivity.class);
                startActivity(intent);
            }
        });

        bananaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), bananaActivity.class);
                startActivity(intent);

            }
        });

        orangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), lemonActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

    // Method to open CropDetailsActivity and pass crop information
    private void openCropDetails(String cropName, String cropInfo) {
        Intent intent = new Intent(getActivity(), CropDetailsActivity.class);
        intent.putExtra("CROP_NAME", cropName);
        intent.putExtra("CROP_DESCRIPTION", cropInfo);
        startActivity(intent);
    }
}
