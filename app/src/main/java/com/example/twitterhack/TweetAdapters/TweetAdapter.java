package com.example.twitterhack.TweetAdapters;

import android.util.Log;

import com.twitter.clientlib.model.Expansions;
import com.twitter.clientlib.model.Media;
import com.twitter.clientlib.model.Tweet;
import com.twitter.clientlib.model.TweetAttachments;
import com.twitter.clientlib.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TweetAdapter {
    private final Tweet tweet;
    private final Set<MediaEntityAdapter> media = new HashSet<>();
    private UserAdapter user;

    public Tweet getTweet() {
        return tweet;
    }

    public Set<MediaEntityAdapter> getMedia() {
        return media;
    }

    public UserAdapter getUser() {
        return user;
    }

    public TweetAdapter(Tweet tweet, Expansions expansions){
        assert(tweet!=null);
        assert (expansions!=null);
        this.tweet = tweet;
        this.media.addAll(collectMedia(expansions));
        try {
            this.user = collectUser(expansions);
        } catch (TwitterDataException e) {
            Log.e("Twitter Adapter error",e.getMessage());
        }

    }
    private UserAdapter collectUser(Expansions expansions) throws TwitterDataException {
        if (expansions != null && expansions.getUsers() != null) {
            for (User user : expansions.getUsers()) {
                if (user.getId().equals(tweet.getAuthorId())) {
                    return new UserAdapter(user);
                }
            }
        }
        throw new TwitterDataException("Could not extract Tweet's author. Tweet with ID = " + tweet.getId());

    }

    private Set<MediaEntityAdapter> collectMedia(Expansions expansions) {
        final Set<MediaEntityAdapter> result = new HashSet<>();
        if (expansions != null) {
            final TweetAttachments attachments = tweet.getAttachments();
            final List<Media> media = expansions.getMedia();
            /*
                Extracting Media of each tweet from list of media from Expansions
            */

            if (attachments != null && media != null) {
                if (attachments.getMediaKeys() != null) {
                    for (String mKey : attachments.getMediaKeys()) {
                        for (Media m : media) {
                            if (m.getMediaKey() != null && m.getMediaKey().equals(mKey)) {
                                result.add(new MediaEntityAdapter(m));
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    private class TwitterDataException extends Throwable {
        private String error;
        public TwitterDataException(String s) {
            error= s;
        }
        public String getMessage(){
            return error;
        }
    }
}
