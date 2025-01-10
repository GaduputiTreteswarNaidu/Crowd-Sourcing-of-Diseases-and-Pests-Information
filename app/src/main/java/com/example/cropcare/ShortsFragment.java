package com.example.cropcare;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.VideoView;

public class ShortsFragment extends Fragment {

    private VideoView videoView1, videoView2, videoView3, videoView4;
    private ImageButton playButton1, pauseButton1, playButton2, pauseButton2, playButton3, pauseButton3, playButton4, pauseButton4;
    private TextView descriptionText1, descriptionText2, descriptionText3, descriptionText4;

    public ShortsFragment() {
        // Required empty public constructor
    }

    public static ShortsFragment newInstance() {
        return new ShortsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_shorts, container, false);

        // Access views for each video and its controls
        videoView1 = rootView.findViewById(R.id.videoView1);
        videoView2 = rootView.findViewById(R.id.videoView2);
        videoView3 = rootView.findViewById(R.id.videoView3);
        videoView4 = rootView.findViewById(R.id.videoView4);

        playButton1 = rootView.findViewById(R.id.playButton1);
        pauseButton1 = rootView.findViewById(R.id.pauseButton1);
        playButton2 = rootView.findViewById(R.id.playButton2);
        pauseButton2 = rootView.findViewById(R.id.pauseButton2);
        playButton3 = rootView.findViewById(R.id.playButton3);
        pauseButton3 = rootView.findViewById(R.id.pauseButton3);
        playButton4 = rootView.findViewById(R.id.playButton4);
        pauseButton4 = rootView.findViewById(R.id.pauseButton4);

        descriptionText1 = rootView.findViewById(R.id.descriptionText1);
        descriptionText2 = rootView.findViewById(R.id.descriptionText2);
        descriptionText3 = rootView.findViewById(R.id.descriptionText3);
        descriptionText4 = rootView.findViewById(R.id.descriptionText4);

        // Set video URIs (replace with actual paths)
        videoView1.setVideoURI(Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.banana_crop));
        videoView2.setVideoURI(Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.grapes_crop));
        videoView3.setVideoURI(Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.lemon_farm));
        videoView4.setVideoURI(Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + R.raw.paddy_farm));

        setupVideoControls(videoView1, playButton1, pauseButton1);
        setupVideoControls(videoView2, playButton2, pauseButton2);
        setupVideoControls(videoView3, playButton3, pauseButton3);
        setupVideoControls(videoView4, playButton4, pauseButton4);

        return rootView;
    }

    private void setupVideoControls(VideoView videoView, ImageButton playButton, ImageButton pauseButton) {
        // Play button logic
        playButton.setOnClickListener(view -> {
            videoView.start();
            playButton.setVisibility(View.GONE);
            pauseButton.setVisibility(View.VISIBLE);
        });

        // Pause button logic
        pauseButton.setOnClickListener(view -> {
            videoView.pause();
            playButton.setVisibility(View.VISIBLE);
            pauseButton.setVisibility(View.GONE);
        });

        // When the video finishes, reset the buttons
        videoView.setOnCompletionListener(mediaPlayer -> {
            playButton.setVisibility(View.VISIBLE);
            pauseButton.setVisibility(View.GONE);
        });
    }
}
