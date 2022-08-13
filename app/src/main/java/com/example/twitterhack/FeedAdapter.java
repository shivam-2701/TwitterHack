package com.example.twitterhack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.twitterhack.TweetAdapters.TweetData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

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
        ImagePagerAdapter imagePagerAdapter= new ImagePagerAdapter(tweetData.getMediaUrls());
        holder.mImageViewPager.setAdapter(imagePagerAdapter);
        holder.mDescriptionTv.setText(tweetData.getTweet().getText());
    }

    @Override
    public int getItemCount() {
        if(mTweetDataList==null){
            return 0;
        }
        return mTweetDataList.size();
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

    /*
       TODO: Finish the PagerAdapter
     */
    private class ImagePagerAdapter extends PagerAdapter{

        List<String> mImages=null;
        public ImagePagerAdapter( List<String> imageUrls){
            mImages= imageUrls;
        }


        @Override
        public int getCount() {
            if(mImages==null){
                return 0;
            }
            return mImages.size();
        }


        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((ImageView) object);
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((ImageView) object);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            Context context = container.getContext();
            ImageView imageView = new ImageView(context);
            int padding = context.getResources().getDimensionPixelSize(
                    R.dimen.padding_medium);
            imageView.setPadding(padding, padding, padding, padding);
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            if(mImages!=null && mImages.size()<=position) {
                Picasso.with(context)
                        .load(mImages.get(position))
                        .into(imageView);
            }
            ((ViewPager) container).addView(imageView, 0);
            return imageView;
        }
    }
}
