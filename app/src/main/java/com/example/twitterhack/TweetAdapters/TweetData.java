package com.example.twitterhack.TweetAdapters;

import com.twitter.clientlib.model.Tweet;

import java.util.List;

public class TweetData {
    private Tweet tweet;
    private List<String> mediaUrls;
    private UserAdapter userData;

    public TweetData(Tweet tweet, List<String> mediaUrls, UserAdapter userData) {
        this.tweet = tweet;
        this.mediaUrls = mediaUrls;
        this.userData = userData;
    }

    public Tweet getTweet() {
        return tweet;
    }

    public List<String> getMediaUrls() {
        return mediaUrls;
    }

    public UserAdapter getUserData() {
        return userData;
    }
}
