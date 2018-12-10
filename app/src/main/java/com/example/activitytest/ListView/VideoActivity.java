package com.example.activitytest.ListView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.activitytest.R;
import com.example.activitytest.view.BaseActivity;
import com.example.activitytest.view.VideoPlayerActivity;

import java.util.ArrayList;
import java.util.List;

public class VideoActivity extends BaseActivity {


    private ListView video_lv;
    private List<String> list=new ArrayList<>();
    private List<String> pathList=new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        video_lv= (ListView) findViewById(R.id.video_lv);
        initVideo();

        adapter=new ArrayAdapter<>(VideoActivity.this,android.R.layout.simple_list_item_1,list);


        video_lv.setAdapter(adapter);

        video_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(VideoActivity.this, pathList.get(position), Toast.LENGTH_SHORT).show();
            }
        });
        video_lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(VideoActivity.this, VideoPlayerActivity.class);
                intent.putExtra("path",pathList.get(position));
                startActivity(intent);

                return true;
            }
        });



    }

    public void initVideo(){
        Cursor cursor=null;

        try{
            cursor = getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
            if(cursor!=null){

                while (cursor.moveToNext()) {

                    String name = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DISPLAY_NAME));
                    String path=cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));

                    pathList.add(path);
                    list.add(name);
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
}