package com.example.activitytest.view;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.activitytest.R;

import java.io.File;
import java.io.IOException;

public class MusicPlayerActivity extends BaseActivity implements View.OnClickListener {
    MediaPlayer mediaPlayer=new MediaPlayer();
    private int music_position;
   private String music_path;
    private String MusicName;
    private TextView musicplayer_name;
    private Button play,pause,stop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        //设置折叠标题栏
        CollapsingToolbarLayout collapsingToolbarLayout= (CollapsingToolbarLayout) findViewById(R.id.M_collapsing_toolbar);
        ImageView M_beijig= (ImageView) findViewById(R.id.M_beijing);
        musicplayer_name= (TextView) findViewById(R.id.musicplayer_name);
        collapsingToolbarLayout.setTitle("音乐播放器--标题栏");
        M_beijig.setImageResource(R.drawable.beijing);
       // Toolbar toolbar= (Toolbar) findViewById(R.id.M_toolbar);

        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);

        }

        Intent intent=getIntent();
        MusicName=intent.getStringExtra("name");
        music_path=intent.getStringExtra("path");
        //用Intent传int类型的值
        music_position=intent.getExtras().getInt("position");
        Toast.makeText(MusicPlayerActivity.this,"这是第 "+music_position+1+" 条歌",Toast.LENGTH_SHORT).show();


        musicplayer_name.setText(MusicName);

        initMediaPlayer();

        play= (Button) findViewById(R.id.play);
        pause= (Button) findViewById(R.id.pause);
        stop= (Button) findViewById(R.id.stop);
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);




    }

    private void initMediaPlayer(){
       try {
           //File file=new File(Environment.getExternalStorageDirectory(),music_path);
           mediaPlayer.setDataSource(music_path);
           mediaPlayer.prepare();
       } catch (IOException e) {

           e.printStackTrace();
       }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.back,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.Menuback:
                finish();
            break;
            case android.R.id.home:
                finish();
                break;
            default:
                break;

        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.play:
                if (!mediaPlayer.isPlaying()){
                    mediaPlayer.start();//开始播放
                }
                break;
            case R.id.pause:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();//暂停播放
                }
                break;
            case R.id.stop:
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.reset();//停止播放
                    initMediaPlayer();
                }
                break;

            default:
                break;

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer!=null){
            mediaPlayer.stop();//调用这个方法,MediaPlayer不能被使用了
            mediaPlayer.release();//释放资源

        }
    }
}