package com.unknowndev.musically.Apaters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.unknowndev.musically.Models.TrendingSongModel;
import com.unknowndev.musically.R;

public class TrendingSongAdapter extends RecyclerView.Adapter<TrendingSongAdapter.ViewHolder> {

    Context context;
    TrendingSongModel trendingSongModel;

    public TrendingSongAdapter(Context context, TrendingSongModel trendingSongModel) {
        this.context = context;
        this.trendingSongModel = trendingSongModel;
    }

    @NonNull
    @Override
    public TrendingSongAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_tranding_song, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrendingSongAdapter.ViewHolder holder, int position) {
        TrendingSongModel.Snippet snippet = trendingSongModel.getItems().get(position).getSnippet();
        Glide.with(context).load(snippet.getThumbnails().getMedium().getUrl())
                .into(holder.imageSong);

        holder.tvSongTitle.setText(snippet.getTitle());
        holder.tvArtistName.setText(snippet.getChannelTitle());

        holder.itemView.setOnClickListener(v->{
            Toast.makeText(context, "Clicked On" + trendingSongModel.getItems().get(position).getId(), Toast.LENGTH_SHORT).show();
        });

    }

    @Override
    public int getItemCount() {
        return trendingSongModel.getItems().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageSong;
        TextView tvSongTitle, tvArtistName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageSong = itemView.findViewById(R.id.imageSong);
            tvSongTitle = itemView.findViewById(R.id.tvSongTitle);
            tvArtistName = itemView.findViewById(R.id.tvArtistName);
        }
    }
}
