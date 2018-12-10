package com.example.activitytest.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import com.example.activitytest.R;

public class VideoPlayerActivity extends BaseActivity implements View.OnClickListener{

    private VideoView videoView;
    private String path;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vedioplayer);
        Intent intent=getIntent();
        path=intent.getStringExtra("path");



        videoView= (VideoView) findViewById(R.id.videoview);
        Button video_play= (Button) findViewById(R.id.video_play);
        Button video_pause= (Button) findViewById(R.id.video_pause);
        Button video_replay= (Button) findViewById(R.id.video_replay);

        videoView.setVideoPath(path);

        video_play.setOnClickListener(this);
        video_pause.setOnClickListener(this);
        video_replay.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.video_play:
                if (!videoView.isPlaying()){
                    videoView.start();
                }
                break;
            case R.id.video_pause:
                if (videoView.isPlaying()){

                    videoView.pause();
                }
                break;
            case R.id.video_replay:
                if (videoView.isPlaying()){
                    videoView.resume();

                }
                break;

            default:
                break;

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoView.isPlaying()){
            videoView.suspend();
        }
    }
}