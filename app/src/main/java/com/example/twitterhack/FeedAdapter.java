package com.example.twitterhack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.twitterhack.TweetAdapters.TweetData;

import java.util.ArrayList;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedAdapterViewHolder>{


    private ArrayList<TweetData> mTweetDataList;

    public FeedAdapter(ArrayList<TweetData> mTweetDataList) {
        this.mTweetDataList = mTweetDataList;
    }

    @NonNull
    @Override
    public FeedAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context= parent.getContext();
        int layoutIdForlistItem= R.layout.list_item;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view=layoutInflater.inflate(layoutIdForlistItem,parent,false);

        return new FeedAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedAdapterViewHolder holder, int position) {
        TweetData tweetData=mTweetDataList.get(position);


    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class FeedAdapterViewHolder extends RecyclerView.ViewHolder {

        public final ViewPager mImageViewPager;
        public final TextView mDescriptionTv;
        public FeedAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageViewPager=(ViewPager)itemView.findViewById(R.id.ImageViewPager);
            mDescriptionTv=(TextView)itemView.findViewById(R.id.description_tv);
        }

    }

}
