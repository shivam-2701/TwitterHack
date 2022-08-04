package com.example.twitterhack;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class TwitterAsyncTask extends AsyncTask<TwitterApiExample, Void, Void> {

    private Context context;
    @Override
    protected Void doInBackground(TwitterApiExample... twitterApiExamples) {
        twitterApiExamples[0].CreateApi();
        context=twitterApiExamples[0].getContext();
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        Toast.makeText(context, "Api Call succesful", Toast.LENGTH_SHORT).show();
    }
}
