package com.example.twitterhack.TweetAdapters;

import com.twitter.clientlib.model.Tweet;

import java.util.ArrayList;
import java.util.List;

public class TweetData {
    private Tweet tweet;
    private ArrayList<String> mediaUrls;
    private UserAdapter userData;

    public TweetData(Tweet tweet, ArrayList<String> mediaUrls, UserAdapter userData) {
        this.tweet = tweet;
        this.mediaUrls = mediaUrls;
        this.userData = userData;
    }

    public Tweet getTweet() {
        return tweet;
    }

    public ArrayList<String> getMediaUrls() {
        return mediaUrls;
    }

    public UserAdapter getUserData() {
        return userData;
    }
}
