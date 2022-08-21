package com.example.twitterhack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.twitterhack.TweetAdapters.TweetData;
import com.example.twitterhack.utils.ApiInstance;
import com.example.twitterhack.utils.SearchUtils;
import com.twitter.clientlib.api.TwitterApi;
import com.twitter.clientlib.model.Get2TweetsSearchRecentResponse;
import com.twitter.clientlib.model.ResourceUnauthorizedProblem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<TweetData> mTweetList;
    private FeedAdapter mfeedAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView= (RecyclerView)findViewById(R.id.feed_rv);
        ApiInstance apiInstance= ApiInstance.getInstance(this);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);
        mfeedAdapter= new FeedAdapter();
        mRecyclerView.setAdapter(mfeedAdapter);
        TwitterAsyncTask twitterAsyncTask= new TwitterAsyncTask();
        twitterAsyncTask.execute(apiInstance);
    }

    public class TwitterAsyncTask extends AsyncTask<ApiInstance, Void, ArrayList<TweetData>> {


        @Override
        protected ArrayList<TweetData> doInBackground(ApiInstance... apiInstances) {

            TwitterApi apiInstance= apiInstances[0].twitterApi;
           String MEDIA_EXPANSION = "attachments.media_keys";
            Set<String> tweetFields = new HashSet<>();
            tweetFields.add("author_id");
            tweetFields.add("id");
            tweetFields.add("created_at");
            Set<String> mediaFeilds = new HashSet<>();
            mediaFeilds.add("url");
            Set<String> expansions = new HashSet<>();
            expansions.add(MEDIA_EXPANSION);

            // findTweets for a given query
            Get2TweetsSearchRecentResponse result = SearchUtils.SearchTweets("(#Dogememe OR \"doge meme\" ) has:images",apiInstance, tweetFields, expansions, mediaFeilds);


            if (result != null && result.getErrors() != null && result.getErrors().size() > 0) {
                Log.d("Api Response", "Error");

                result.getErrors().forEach(e -> {
                    Log.d("Api Response", e.toString());
                    if (e instanceof ResourceUnauthorizedProblem) {
                        Log.d("Api Response", ((ResourceUnauthorizedProblem) e).getTitle() + " " + ((ResourceUnauthorizedProblem) e).getDetail());
                    }
                });
            } else {

                ArrayList<String> idList = SearchUtils.ExtractIds(result);
                Log.d("Tweets Id", "" + idList.size());
                ArrayList<TweetData> tweetsDataList = SearchUtils.GetImagesUrl(apiInstance, idList, expansions);
                Log.d("TweetData", String.valueOf(tweetsDataList.size()));
                return tweetsDataList;
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<TweetData> tweetData) {
            super.onPostExecute(tweetData);
            mTweetList= tweetData;
            Log.d("TweetDataSize","" + mTweetList.size());
            mfeedAdapter.setmTweetDataList(mTweetList);
        }
    }


}