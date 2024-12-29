package com.unknowndev.musically.Activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.unknowndev.musically.Apaters.PlaylistAdapter;
import com.unknowndev.musically.Apaters.TrendingSongAdapter;
import com.unknowndev.musically.BuildConfig;
import com.unknowndev.musically.Models.PlaylistModel;
import com.unknowndev.musically.Models.TrendingSongModel;
import com.unknowndev.musically.R;
import com.unknowndev.musically.Sevices.RetrofitClient;
import com.unknowndev.musically.Sevices.YoutubeAPIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static String YOUTUBE_API_KEY = BuildConfig.YOUTUBE_API;
    public static String TAG = "My App Debug";
    RecyclerView trendingRecyclerView, playlistRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trendingRecyclerView = findViewById(R.id.trendingRecyclerView);
        playlistRecyclerView = findViewById(R.id.playlistRecyclerView);

        trendingRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        playlistRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        getPlaylist();
        getTrendingMusic();

    }
    void getTrendingMusic(){
        YoutubeAPIService apiService = RetrofitClient.getRetrofitInstance().create(YoutubeAPIService.class);

        Call<TrendingSongModel> call = apiService.getTrendingMusic("snippet",
                "mostPopular",
                "IN",10,7,
                YOUTUBE_API_KEY);

        call.enqueue(new Callback<TrendingSongModel>() {
            @Override
            public void onResponse(Call<TrendingSongModel> call, Response<TrendingSongModel> response) {
                TrendingSongModel trendingSongModel = response.body();
                if (response.isSuccessful() && !trendingSongModel.toString().isEmpty()){
                    Log.d(TAG, "onResponse: " + trendingSongModel.getItems().get(1).getSnippet().getTitle());
                    TrendingSongAdapter adapter = new TrendingSongAdapter(MainActivity.this, trendingSongModel);
                    trendingRecyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(MainActivity.this, "Failed to load songs", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onResponse: Failed" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<TrendingSongModel> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, "Failed to load song", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: Error Occurs" + throwable);
            }
        });

    }

    void getPlaylist(){
        YoutubeAPIService apiService = RetrofitClient.getRetrofitInstance().create(YoutubeAPIService.class);
        Call<PlaylistModel> call = apiService.getPlaylists(
                "snippet",
                "music playlist",
                "playlist",
                6,
                YOUTUBE_API_KEY
        );

        call.enqueue(new Callback<PlaylistModel>() {
            @Override
            public void onResponse(Call<PlaylistModel> call, Response<PlaylistModel> response) {
                if (response.isSuccessful() && !response.toString().isEmpty()){
                    PlaylistModel playlistModel = response.body();
                    Log.d(TAG, "onResponse: Success" + playlistModel);
                    PlaylistAdapter adapter = new PlaylistAdapter(MainActivity.this, playlistModel);
                    playlistRecyclerView.setAdapter(adapter);
                } else{
                    Toast.makeText(MainActivity.this, "Failed to load songs", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onResponse: Failed" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PlaylistModel> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, "Failed to load song", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: Error Occurs" + throwable);
            }
        });

    }
}