package com.example.yogaclub.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.yogaclub.R;

public class MainActivityVideo extends AppCompatActivity {

    static int video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_video);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setTitle("Yoga Workout");
        VideoView videoView = findViewById(R.id.videoViewFragment);
        videoView.setVideoURI(Uri.parse("android.resource://" +
                getPackageName()+"/" + video));
        videoView.setMediaController(new MediaController(this));
        videoView.start();
        videoView.requestFocus();
    }

    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }

    public static void setVideo(int video) {
        MainActivityVideo.video = video;
    }
}