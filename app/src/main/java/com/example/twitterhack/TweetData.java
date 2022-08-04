package com.example.twitterhack;

import android.net.UrlQuerySanitizer;

import java.util.List;

public class TweetData {
        private String mediaKey;
        private String tweetId;
        private List<String> imageUrl;

    public TweetData(String tweetId, String mediaKey, List<String> imageUrl) {
        this.mediaKey = mediaKey;
        this.tweetId = tweetId;
        this.imageUrl = imageUrl;
    }

    public String getMediaKey() {
        return mediaKey;
    }

    public void setMediaKey(String mediaKey) {
        this.mediaKey = mediaKey;
    }

    public String getTweetId() {
        return tweetId;
    }

    public void setTweetId(String tweetId) {
        this.tweetId = tweetId;
    }

    public List<String> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(List<String> imageUrl) {
        this.imageUrl = imageUrl;
    }
}
