package com.example.cropcare;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.ViewHolder> {

    private final List<CommunityItem> communityItems;

    public CommunityAdapter(List<CommunityItem> communityItems) {
        this.communityItems = communityItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_community_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CommunityItem item = communityItems.get(position);
        holder.questionView.setText("Q: " + item.getQuestion());
        holder.descriptionView.setText("Description: " + item.getDescription());

        if (item.getImageUri() != null) {
            holder.imageView.setImageURI(item.getImageUri());
        } else {
            holder.imageView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return communityItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView questionView;
        TextView descriptionView;
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            questionView = itemView.findViewById(R.id.questionTextView);
            descriptionView = itemView.findViewById(R.id.descriptionTextView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
