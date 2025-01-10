package com.example.cropcare;

import android.net.Uri;

public class CommunityItem {
    private final String question;
    private final String description;
    private final Uri imageUri;

    public CommunityItem(String question, String description, Uri imageUri) {
        this.question = question;
        this.description = description;
        this.imageUri = imageUri;
    }

    public String getQuestion() {
        return question;
    }

    public String getDescription() {
        return description;
    }

    public Uri getImageUri() {
        return imageUri;
    }
}
