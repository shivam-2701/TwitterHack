package com.example.twitterhack.TweetAdapters;

import com.twitter.clientlib.model.AnimatedGif;
import com.twitter.clientlib.model.Media;
import com.twitter.clientlib.model.Photo;
import com.twitter.clientlib.model.Video;

public class MediaEntityAdapter {
    private final Media mediaEntity;
    private final String EMPTY_STRING="";

    public MediaEntityAdapter(Media entity) {
        assert (entity != null);
        this.mediaEntity = entity;
    }

    public String getText() {
        if (mediaEntity instanceof Photo) {
            Photo v= (Photo) mediaEntity;
            return v.getAltText() != null ? v.getAltText() : EMPTY_STRING;
        }
        return EMPTY_STRING;
    }


    public String getType() {
        return mediaEntity.getType();
    }


    public String getURL() {
        if (mediaEntity instanceof Video) {
            Video v= (Video) mediaEntity;
            return v.getPreviewImageUrl() != null ? v.getPreviewImageUrl().toString() : EMPTY_STRING;
        }
        if (mediaEntity instanceof Photo) {
            Photo v= (Photo) mediaEntity;
            return v.getUrl() != null ? v.getUrl().toString() : EMPTY_STRING;
        }
        if (mediaEntity instanceof AnimatedGif) {
            AnimatedGif v = (AnimatedGif) mediaEntity;
            return v.getPreviewImageUrl() != null ? v.getPreviewImageUrl().toString() : EMPTY_STRING;
        }

        return EMPTY_STRING;
    }
}
