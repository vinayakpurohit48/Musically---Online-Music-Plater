package com.unknowndev.musically.Apaters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.unknowndev.musically.Activities.ListSongActivity;
import com.unknowndev.musically.Models.PlaylistModel;
import com.unknowndev.musically.R;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> {

    Context context;
    PlaylistModel playlistModel;

    public PlaylistAdapter(Context context, PlaylistModel playlistModel) {
        this.context = context;
        this.playlistModel = playlistModel;
    }

    @NonNull
    @Override
    public PlaylistAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_playlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistAdapter.ViewHolder holder, int position) {
        PlaylistModel.Item item = playlistModel.getItems().get(position);

        if (item != null) {
            holder.songTitle.setText(item.getSnippet().getTitle());

            // Set artist/channel name (from snippet.channelTitle)
            holder.artistName.setText(item.getSnippet().getChannelTitle());

            // Load album cover (from snippet.thumbnails.url) using Glide
            if (item.getSnippet().getThumbnails() != null && item.getSnippet().getThumbnails().getHigh().getUrl() != null) {
                String imageUrl = item.getSnippet().getThumbnails().getHigh().getUrl(); // Get the URL of the thumbnail
                Glide.with(holder.albumCover.getContext())
                        .load(imageUrl)
                        .into(holder.albumCover);
            } else {
                // Handle missing thumbnails (optional)
                //holder.albumCover.setImageResource(R.drawable.placeholder_image); // Placeholder image
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ListSongActivity.class);
                    intent.putExtra("ItemModel", item);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return playlistModel.getItems().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView albumCover;
        TextView songTitle;
        TextView artistName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            albumCover = itemView.findViewById(R.id.albumCover);
            songTitle = itemView.findViewById(R.id.songTitle);
            artistName = itemView.findViewById(R.id.channelName);
        }
    }
}
