package com.example.twitterdemo;

import com.example.twitterdemo.model.Feed;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FeedApi {

    String BASE_URL="https://www.reddit.com/r/";

    @GET("earthporn/.rss")
    Call<Feed> getFeed();
}
