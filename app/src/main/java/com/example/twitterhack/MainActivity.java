package com.example.twitterhack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.twitterhack.utils.ApiInstance;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ApiInstance apiInstance= ApiInstance.getInstance(this);



    }
    public void buttonCall(View view){
        TwitterApiExample twitterApiExample =new TwitterApiExample(this);
        TwitterAsyncTask twitterAsyncTask =new TwitterAsyncTask();
        twitterAsyncTask.execute(twitterApiExample);

    }

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


}