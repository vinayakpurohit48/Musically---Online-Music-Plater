package com.unknowndev.musically.Apaters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.unknowndev.musically.Models.PlaylistSongModel;
import com.unknowndev.musically.R;

import java.util.List;
public class ListMusicTrackAdapter extends RecyclerView.Adapter<ListMusicTrackAdapter.ViewHolder>  {
    Context context;
    List<PlaylistSongModel.PlaylistItem> PlaylistItem;

    public ListMusicTrackAdapter(Context context, List<PlaylistSongModel.PlaylistItem> PlaylistItem) {
        this.context = context;
        this.PlaylistItem = PlaylistItem;
    }

    @NonNull
    @Override
    public ListMusicTrackAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_track, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListMusicTrackAdapter.ViewHolder holder, int position) {
        PlaylistSongModel.PlaylistItem.Snippet snippet = PlaylistItem.get(position).getSnippet();

        if (snippet != null && snippet.getThumbnails() != null && snippet.getThumbnails().getMedium().getUrl() != null) {
            Glide.with(context).load(snippet.getThumbnails().getMedium().getUrl())
                    .into(holder.albumArt);
        } else {
            // Set a default image in case thumbnail is null
            Glide.with(context).load(R.drawable.ic_launcher_background) // Ensure you have a default image
                    .into(holder.albumArt);
        }

        holder.songTitle.setText(snippet != null ? snippet.getTitle() : "Unknown Title");
        holder.artistName.setText(snippet != null ? snippet.getChannelTitle() : "Unknown Artist");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return PlaylistItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView albumArt;
        TextView songTitle, artistName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            albumArt = itemView.findViewById(R.id.album_art);
            songTitle = itemView.findViewById(R.id.song_title);
            artistName = itemView.findViewById(R.id.artist_name);

        }
    }
}
