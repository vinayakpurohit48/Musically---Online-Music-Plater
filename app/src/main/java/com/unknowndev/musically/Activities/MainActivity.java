package com.unknowndev.musically.Activities;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.unknowndev.musically.Apaters.ListMusicTrackAdapter;
import com.unknowndev.musically.Apaters.ListSearchSuggetion;
import com.unknowndev.musically.Apaters.PlaylistAdapter;
import com.unknowndev.musically.Apaters.TrendingSongAdapter;
import com.unknowndev.musically.BuildConfig;
import com.unknowndev.musically.Models.YoutubeSearchResponse;
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
    RecyclerView searchItemListView;
    LinearLayout songLayout;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trendingRecyclerView = findViewById(R.id.trendingRecyclerView);
        playlistRecyclerView = findViewById(R.id.playlistRecyclerView);
        searchItemListView = findViewById(R.id.searchItemListView);
        searchView = findViewById(R.id.searchView);
        songLayout = findViewById(R.id.songLayout);

        trendingRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        playlistRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        searchItemListView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        isSongSearching(false);

        Handler handler = new Handler();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!newText.isEmpty()){
                    handler.removeCallbacksAndMessages(null);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            isSongSearching(true);
                            fetchSuggestions(newText);
                        }
                    }, 400);
                } else {
                    isSongSearching(false);
                }
                return false;
            }
        });

        getPlaylist();
        getTrendingMusic();

    }

    void fetchSuggestions(String query) {
        YoutubeAPIService apiService = RetrofitClient.getRetrofitInstance().create(YoutubeAPIService.class);
        Call<YoutubeSearchResponse> call = apiService.getSearchSuggestion(
                "snippet",
                query,
                "video",
                10,
                3,
                "IN",
                YOUTUBE_API_KEY
        );

        call.enqueue(new Callback<YoutubeSearchResponse>() {
            @Override
            public void onResponse(Call<YoutubeSearchResponse> call, Response<YoutubeSearchResponse> response) {
                if (response.isSuccessful() && !response.toString().isEmpty()){
                    YoutubeSearchResponse playlistModel = response.body();
                    ListSearchSuggetion searchSuggetion = new ListSearchSuggetion(MainActivity.this, playlistModel);
                    searchItemListView.setAdapter(searchSuggetion);

                } else{
                    Toast.makeText(MainActivity.this, "Failed to load songs", Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, "!! Daily limit Reached !!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<YoutubeSearchResponse> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, "Failed to load song", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: Error Occurs" + throwable);
            }
        });
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
                    //Log.d(TAG, "onResponse: " + trendingSongModel.getItems().get(1).getSnippet().getTitle());
                    TrendingSongAdapter adapter = new TrendingSongAdapter(MainActivity.this, trendingSongModel);
                    trendingRecyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(MainActivity.this, "!! Daily limit Reached !!", Toast.LENGTH_SHORT).show();
                    //Log.d(TAG, "onResponse: Failed" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<TrendingSongModel> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, "Failed to load song", Toast.LENGTH_SHORT).show();
                //Log.d(TAG, "onFailure: Error Occurs" + throwable);
            }
        });

    }

    void getPlaylist(){
        YoutubeAPIService apiService = RetrofitClient.getRetrofitInstance().create(YoutubeAPIService.class);
        Call<YoutubeSearchResponse> call = apiService.getPlaylists(
                "snippet",
                "music playlist",
                "playlist",
                6,
                "IN",
                YOUTUBE_API_KEY
        );

        call.enqueue(new Callback<YoutubeSearchResponse>() {
            @Override
            public void onResponse(Call<YoutubeSearchResponse> call, Response<YoutubeSearchResponse> response) {
                if (response.isSuccessful() && !response.toString().isEmpty()){
                    YoutubeSearchResponse playlistModel = response.body();
                    //Log.d(TAG, "onResponse: Success" + playlistModel);
                    PlaylistAdapter adapter = new PlaylistAdapter(MainActivity.this, playlistModel);
                    playlistRecyclerView.setAdapter(adapter);
                } else{
                    Toast.makeText(MainActivity.this, "!! Daily limit Reached !!", Toast.LENGTH_SHORT).show();
                    //Log.d(TAG, "onResponse: Failed" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<YoutubeSearchResponse> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, "Failed to load song", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: Error Occurs" + throwable);
            }
        });

    }
    void isSongSearching(Boolean isSongSearching) {
        if (isSongSearching) {
            searchItemListView.setVisibility(View.VISIBLE);
            songLayout.setVisibility(View.GONE);
        } else{
            searchItemListView.setVisibility(View.GONE);
            songLayout.setVisibility(View.VISIBLE);
        }
    }
}