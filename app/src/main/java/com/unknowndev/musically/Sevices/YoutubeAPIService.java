package com.unknowndev.musically.Sevices;

import com.unknowndev.musically.Models.YoutubeSearchResponse;
import com.unknowndev.musically.Models.PlaylistSongModel;
import com.unknowndev.musically.Models.TrendingSongModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface YoutubeAPIService {
    @GET("v3/videos")
    Call<TrendingSongModel> getTrendingMusic(
      @Query("part") String part,
      @Query("chart") String chart,
      @Query("regionCode") String regionCode,
      @Query("videoCategoryId") int videoCategoryId,
      @Query("maxResults") int maxResults,
      @Query("key") String ApiKey
    );

    @GET("v3/search")
    Call<YoutubeSearchResponse> getPlaylists(
            @Query("part") String part,
            @Query("q") String query,
            @Query("type") String type,
            @Query("maxResults") int maxResults,
            @Query("regionCode") String regionCode,
            @Query("key") String apiKey
    );

    @GET("v3/search")
    Call<YoutubeSearchResponse> getSearchSuggestion(
            @Query("part") String part,
            @Query("q") String query,
            @Query("type") String type,
            @Query("category") int category,
            @Query("maxResults") int maxResults,
            @Query("regionCode") String regionCode,
            @Query("key") String apiKey
    );

    @GET("v3/playlistItems")
    Call<PlaylistSongModel> getPlaylistMusic(
            @Query("part") String part,
            @Query("playlistId") String playlistId,
            @Query("maxResults") int maxResults,
            @Query("key") String apiKey,
            @Query("pageToken") String pageToken
    );
    @GET("dl")
    Call<ResponseBody> getMp3Url(
            @Query("id") String videoID,
            @Header("x-rapidapi-key") String rapidApiKey,
            @Header("x-rapidapi-host") String rapidApiHost
    );
}
