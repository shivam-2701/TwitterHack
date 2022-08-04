package com.example.twitterhack.TweetAdapters;

import com.twitter.clientlib.model.User;
import com.twitter.clientlib.model.UserEntities;

public class UserAdapter {
    private final User user;

    public UserAdapter(User user) {
        assert(user!=null);
        this.user = user;
    }
    public String getUserid(){
        return user.getId();
    }
    public String getfriendlyName(){
        return user.getName();
    }
    public UserEntities getUserEntities(){
        return user.getEntities();
    }
    public String getUserUrl(){
        return user.getUrl();
    }
}
