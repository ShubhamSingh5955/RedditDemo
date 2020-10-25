package com.example.twitterdemo;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.twitterdemo.model.Feed;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG ="MainActivity";
    private static final String BASE_URL="https://www.reddit.com/r/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.reddit.com/r/")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        FeedApi feedApi = retrofit.create(FeedApi.class);

        Call<Feed> call=feedApi.getFeed();

        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call , Response<Feed> response) {
                Log.e(TAG, "onResponce: feed " + response.body().toString() );
                Log.e(TAG, "onResponce: Server Responce" + response.toString());

            }
            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                Log.e(TAG, "onFailure: Unable to retrive RSS: "+ t.getMessage());
                Toast.makeText(MainActivity.this,"An Error Occured",Toast.LENGTH_SHORT).show();
            }
        });
    }


}
