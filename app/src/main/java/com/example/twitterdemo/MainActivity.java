package com.example.twitterdemo;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.twitterdemo.model.Feed;
import com.example.twitterdemo.model.entry.Entry;

import java.util.List;

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
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        FeedApi feedApi = retrofit.create(FeedApi.class);

        Call<Feed> call=feedApi.getFeed();

        call.enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call , Response<Feed> response) {
                Log.e(TAG, "onResponce: feed " + response.body().toString() );
                Log.e(TAG, "onResponce: Server Responce" + response.toString());

                List<Entry> entrys= response.body().getEntrys();
                Log.e(TAG,"onResponse: entrys: "+response.body().getEntrys());

            //    Log.e(TAG,"onResponse: author: "+entrys.get(0).getAuthor().getName());
            //    Log.e(TAG,"onResponse: updated: "+entrys.get(0).getUpdated());
            //    Log.e(TAG,"onResponse: title: "+entrys.get(0).getTitle());

                for(int i=0;i<entrys.size();i++){
                    ExtractXML extractXML1=new ExtractXML(entrys.get(0).getCotent(),"<a href=");
                    List<String> postContent=extractXML1.start();

                    ExtractXML extractXML2=new ExtractXML(entrys.get(0).getCotent(),"<img src=");
                    try {
                        postContent.add(extractXML2.start().get(0));
                    }catch (NullPointerException e){
                        postContent.add(null);
                        Log.e(TAG,"onResponse: NullPointerException(thumbnail):"+e.getMessage());
                    }catch (IndexOutOfBoundsException e){
                    postContent.add(null);
                        Log.e(TAG,"onResponse: IndexOutOfBoundsException(thumbnail):"+e.getMessage());
                    }

                }

            }
            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                Log.e(TAG, "onFailure: Unable to retrive RSS: "+ t.getMessage());
                Toast.makeText(MainActivity.this,"An Error Occured",Toast.LENGTH_SHORT).show();
            }
        });
    }


}
