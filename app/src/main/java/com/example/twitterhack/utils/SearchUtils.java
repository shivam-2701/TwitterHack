package com.example.twitterhack.utils;

import android.util.Log;

import com.example.twitterhack.TweetAdapters.MediaEntityAdapter;
import com.example.twitterhack.TweetAdapters.TweetAdapter;
import com.example.twitterhack.TweetAdapters.TweetData;
import com.example.twitterhack.TweetAdapters.TwitterEntityFields;
import com.twitter.clientlib.ApiException;
import com.twitter.clientlib.api.TwitterApi;
import com.twitter.clientlib.model.Expansions;
import com.twitter.clientlib.model.Get2TweetsResponse;
import com.twitter.clientlib.model.Get2TweetsSearchRecentResponse;
import com.twitter.clientlib.model.ResourceUnauthorizedProblem;
import com.twitter.clientlib.model.Tweet;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class SearchUtils {

    public static Get2TweetsSearchRecentResponse SearchTweets(String query, TwitterApi apiInstance, Set<String> tweetFeilds,Set<String> expansions, Set<String> mediaFeilds){
        Get2TweetsSearchRecentResponse result=null;
        try{
            result=apiInstance.tweets().tweetsRecentSearch(query)
                    .tweetFields(tweetFeilds)
                    .expansions(expansions)
                    .mediaFields(mediaFeilds)
                    .maxResults(50)
                    .sortOrder("recency")
                    .execute();


        }catch (ApiException e){
            Log.e("Searching api",e.toString());
        }
        return result;
    }
    public static ArrayList<String> ExtractIds(Get2TweetsSearchRecentResponse response){
        ArrayList<String> idList= new ArrayList<>();
        List<Tweet> tweets= response.getData();
        if(tweets!=null){
        for ( Tweet tweet: tweets) {
                idList.add(tweet.getId());
        }}
        return idList;
    }
    public static ArrayList<TweetData> GetImagesUrl(TwitterApi twitterApi, ArrayList<String> idList, Set<String> expansions){
        //Tweet feilds;


        Get2TweetsResponse tweetsIdResponse;
        tweetsIdResponse = null;
        try {
             tweetsIdResponse= twitterApi.tweets().findTweetsById(idList)
                    .tweetFields(TwitterEntityFields.getTweetFields())
                    .mediaFields(TwitterEntityFields.getMediaFields())
                    .expansions(TwitterEntityFields.getExpansions()).
                     execute();
        } catch (ApiException e) {
           Log.e("Fetching tweets error" ,e.toString());
        }
        if(tweetsIdResponse!=null && tweetsIdResponse.getErrors()!=null && tweetsIdResponse.getErrors().size()>0){
            Log.d("Api Response2","Error");

            tweetsIdResponse.getErrors().forEach(e -> {
                Log.d("Api Response",e.toString());
                if (e instanceof ResourceUnauthorizedProblem) {
                    Log.d("Api Response",e.getTitle() + " " +  e.getDetail());
                }
            });
        }else{
            Expansions included=null;
            //Tweet expansions from the response
            if(tweetsIdResponse!=null) {
                included = tweetsIdResponse.getIncludes();
            }

            ArrayList<TweetAdapter> tweetAdapterList= new ArrayList<>();
            List<Tweet> data=null;
            if(tweetsIdResponse!=null) {
                //List of Tweets from the response
                data = tweetsIdResponse.getData();
            }
            if(data!=null) {
                //Getting Hydrated tweetObject List
                for (Tweet tweet : data) {
                    tweetAdapterList.add(new TweetAdapter(tweet, included));
                }
            }

            //List of tweetData object
            ArrayList<TweetData> tweetDataList = new ArrayList<>();


            Log.d("TweetData",""+ tweetAdapterList.size());

            for(TweetAdapter tweetAdapter: tweetAdapterList){
                if(tweetAdapter.getMedia().size()!=0){
                    List<String> Urls=new ArrayList<>();
                    for(MediaEntityAdapter media: tweetAdapter.getMedia()){
                        Urls.add(media.getURL());
                    }
                    tweetDataList.add(new TweetData(tweetAdapter.getTweet(),Urls,tweetAdapter.getUser()));
                }
            }
            return tweetDataList;



        }
        Log.e("ImageListEmpty","Null list return from GetImagesUrl");
        return null;
    }


}
