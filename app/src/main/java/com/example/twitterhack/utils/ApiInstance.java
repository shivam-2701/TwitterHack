package com.example.twitterhack.utils;

import android.content.Context;
import com.example.twitterhack.R;
import com.twitter.clientlib.TwitterCredentialsBearer;
import com.twitter.clientlib.api.TwitterApi;

public class ApiInstance {
    private static ApiInstance apiInstance=null;

    public TwitterApi twitterApi;

    private ApiInstance(Context context){
            twitterApi= new TwitterApi(new TwitterCredentialsBearer(context.getString(R.string.BEARER_TOKEN)));

    }
    public static ApiInstance getInstance(Context context){
        if(apiInstance==null){
            apiInstance=new ApiInstance(context);
        }
        return apiInstance;
    }

}
