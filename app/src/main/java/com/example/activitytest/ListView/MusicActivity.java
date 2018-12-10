package com.example.activitytest.ListView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.activitytest.R;
import com.example.activitytest.view.BaseActivity;
import com.example.activitytest.view.MusicPlayerActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MusicActivity extends BaseActivity {

    //int music_position;
    private SimpleAdapter adapter;
    private List<Map<String,Object>> list=new ArrayList<>();
    private ListView music_lv;
     Map<String,Object> map;
    private  String path;
    private List<String> music_path=new ArrayList<>();
    private List<String> MusicName=new ArrayList<>();
  //private List<String> MusicPosition=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        music_lv= (ListView) findViewById(R.id.music_lv);

        //设置标题栏以及导航按钮
        Toolbar toolbar= (Toolbar) findViewById(R.id.music_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);

        }
        
        String[] from={"image","name","singer"};
        int[] to={R.id.music_image,R.id.music_name,R.id.music_singer};
        adapter=new SimpleAdapter(MusicActivity.this,list,R.layout.activity_music_item,from,to);

        openMedia();

        music_lv.setAdapter(adapter);

        music_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                //点击歌曲名,显示对应的歌曲路径
                Toast.makeText(MusicActivity.this, "音乐路径:" + music_path.get(position), Toast.LENGTH_SHORT).show();


            }
        });
        music_lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int music_position, long id) {

                Toast.makeText(MusicActivity.this,"歌曲名:"+MusicName.get(music_position),Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(MusicActivity.this, MusicPlayerActivity.class);
                intent.putExtra("position",music_position);
                intent.putExtra("name",MusicName.get(music_position));
                intent.putExtra("path",music_path.get(music_position));
                startActivity(intent);



                return true;
            }
        });


    }
    private void openMedia(){
        Cursor cursor=null;
        try {
            cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    //String image = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
                    String name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                    String singer = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                    path=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                    music_path.add(path);
                    MusicName.add(name);

                    map = new HashMap<>();
                    map.put("image", R.mipmap.ic_launcher);
                    map.put("name", name);
                    map.put("singer", "歌手:"+singer);
                    list.add(map);
                }


                adapter.notifyDataSetChanged();
            }
        }catch (Exception e){
            e.printStackTrace();

        }finally {
            if (cursor!=null){
                cursor.close();
            }
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;

            default:
                break;

        }
        return true;
    }
}