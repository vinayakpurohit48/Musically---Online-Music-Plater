package com.unknowndev.musically.Activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.unknowndev.musically.Apaters.ListMusicTrackAdapter;
import com.unknowndev.musically.BuildConfig;
import com.unknowndev.musically.Models.YoutubeSearchResponse;
import com.unknowndev.musically.Models.PlaylistSongModel;
import com.unknowndev.musically.R;
import com.unknowndev.musically.Sevices.RetrofitClient;
import com.unknowndev.musically.Sevices.YoutubeAPIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListSongActivity extends AppCompatActivity {

    private String TAG = "My App Debug";
    private String YOUTUBE_API_KEY = BuildConfig.YOUTUBE_API;
    private YoutubeSearchResponse.Item item;
    private LinearLayout nowPlayingBar;
    private ImageView nowPlayingImage, playPauseButton, playlistCover;
    private TextView nowPlayingTitle, nowPlayingArtist, playlistTitle, playlistDescription;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_song);
        nowPlayingBar = findViewById(R.id.now_playing_bar);
        nowPlayingImage = findViewById(R.id.now_playing_image);
        nowPlayingTitle = findViewById(R.id.now_playing_title);
        nowPlayingArtist = findViewById(R.id.now_playing_artist);
        playPauseButton = findViewById(R.id.play_pause_button);
        playlistCover = findViewById(R.id.playlist_cover);
        playlistTitle = findViewById(R.id.playlist_title);
        playlistDescription = findViewById(R.id.playlist_description);
        recyclerView = findViewById(R.id.track_list);

        if (getIntent() == null) {
            Toast.makeText(this, "Something getting wrong", Toast.LENGTH_SHORT).show();
            super.onBackPressed();
            return;
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        item = (YoutubeSearchResponse.Item) getIntent().getSerializableExtra("ItemModel");
        Glide.with(this).load(item.getSnippet()
                        .getThumbnails().getHigh().getUrl())
                .into(playlistCover);

        getMusicList(item.getId().getPlaylistId());
        playlistTitle.setText(item.getSnippet().getTitle());
        playlistDescription.setText(item.getSnippet().getChannelTitle());
    }

    void getMusicList(String playlistID) {
        YoutubeAPIService youtubeAPIService = RetrofitClient.getRetrofitInstance().create(YoutubeAPIService.class);

        Call<PlaylistSongModel> call = youtubeAPIService.getPlaylistMusic("snippet", playlistID, 15, YOUTUBE_API_KEY, "");

        call.enqueue(new Callback<PlaylistSongModel>() {
            @Override
            public void onResponse(Call<PlaylistSongModel> call, Response<PlaylistSongModel> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getItems() != null && !response.body().getItems().isEmpty()) {
                    PlaylistSongModel playlistSongModel = response.body();
                    Log.d(TAG, "onResponse: Success");
                    ListMusicTrackAdapter musicTrackAdapter = new ListMusicTrackAdapter(ListSongActivity.this, playlistSongModel.getItems());
                    recyclerView.setAdapter(musicTrackAdapter);
                } else {
                    Log.d(TAG, "onResponse: Empty Response or No Items Found!");
                    Toast.makeText(ListSongActivity.this, "No videos found in this playlist!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PlaylistSongModel> call, Throwable throwable) {
                Log.d(TAG, "onFailure: Error Received: " + throwable);
                Toast.makeText(ListSongActivity.this, "Error Occurs: " + throwable,Toast.LENGTH_SHORT).show();
            }
        });
    }
}