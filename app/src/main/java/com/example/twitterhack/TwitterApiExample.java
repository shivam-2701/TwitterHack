package com.example.twitterhack;

import android.content.Context;
import android.util.Log;

import com.example.twitterhack.TweetAdapters.TweetAdapter;
import com.example.twitterhack.utils.SearchUtils;
import com.twitter.clientlib.ApiException;
import com.twitter.clientlib.TwitterCredentialsBearer;
import com.twitter.clientlib.api.TwitterApi;
import com.twitter.clientlib.model.Get2TweetsIdResponse;
import com.twitter.clientlib.model.Get2TweetsSearchRecentResponse;
import com.twitter.clientlib.model.ResourceUnauthorizedProblem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TwitterApiExample {

    private   Context context ;
//    public static TwitterApi apiInstance;
    public TwitterApiExample(Context context){
        this.context= context;
    }

    public Context getContext() {
        return context;
    }
    private final String MEDIA_EXPANSION="attachments.media_keys";

    public void CreateApi() {
        TwitterApi apiInstance = new TwitterApi(new TwitterCredentialsBearer(context.getString(R.string.BEARER_TOKEN)));

        Set<String> tweetFields = new HashSet<>();
        tweetFields.add("author_id");
        tweetFields.add("id");
        tweetFields.add("created_at");
        Set<String> mediaFeilds= new HashSet<>();
        mediaFeilds.add("url");
        Set<String> expansions = new HashSet<>();
        expansions.add(MEDIA_EXPANSION);

        // findTweets for a given query
        Get2TweetsSearchRecentResponse result = SearchUtils.SearchTweets("(#Dogememe OR \"doge meme\" ) has:images",apiInstance,tweetFields,expansions,mediaFeilds);


        if (result!=null && result.getErrors() != null && result.getErrors().size() > 0) {
           Log.d("Api Response","Error");

            result.getErrors().forEach(e -> {
                Log.d("Api Response",e.toString());
                if (e instanceof ResourceUnauthorizedProblem) {
                    Log.d("Api Response",((ResourceUnauthorizedProblem) e).getTitle() + " " + ((ResourceUnauthorizedProblem) e).getDetail());
                }
            });
        } else {
//            Log.d("Api Response","findTweetById - Tweet Text: " + result.getData().size());
            StringBuilder stringBuilder = new StringBuilder();
            ArrayList<String> idList =SearchUtils.ExtractIds(result);
            Log.d("Id list", String.valueOf(idList.size()));
           ArrayList<TweetAdapter> tweetsAdapterList= SearchUtils.GetImagesUrl(apiInstance,idList,expansions);



        }
    }
}
