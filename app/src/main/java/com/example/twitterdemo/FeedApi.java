package com.example.twitterdemo;

import com.example.twitterdemo.model.Feed;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FeedApi {

    String BASE_URL="https://www.reddit.com/r/";
    //static feed name
    @GET("{feed_name}/.rss")
    Call<Feed> getFeed(@Path("feed_name") String feed_name);

    //static feed name
    //@GET("earthporn/.rss")
    //Call<Feed> getFeed();
}
