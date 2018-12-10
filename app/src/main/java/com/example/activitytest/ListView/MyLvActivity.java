package com.example.activitytest.ListView;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.activitytest.R;

public class MyLvActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lv);
    }



}

class MyAdapter extends ArrayAdapter<Fruit>{


    public MyAdapter(Context context, int resource, int textViewResourceId, Fruit[] objects) {
        super(context, resource, textViewResourceId, objects);


    }

   /* @Override
    public View getView(int position, View convertView, ViewGroup parent) {


    }*/
}

class Fruit{

    private String name;
    private int ImageId;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int imageId) {
        ImageId = imageId;
    }
}