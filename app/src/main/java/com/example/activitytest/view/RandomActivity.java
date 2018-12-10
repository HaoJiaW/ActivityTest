package com.example.activitytest.view;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.activitytest.R;

import java.util.ArrayList;
import java.util.List;


public class RandomActivity extends AppCompatActivity {

    private int[] list={1,2,3,4,5,6};
    //int position;
    private TextView tv;
    private Button click;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv= (TextView) findViewById(R.id.tv);
        click=(Button)findViewById(R.id.button);
        //position=1+(int)Math.random()*5;


        Log.i("TAG", "hha:" + list[1]);


        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                tv.setText("你的数字是:");
            }
        });
    }


 /*   @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.click:
                position=1+(int)Math.random()*5;
                tv.setText(list[position]);
                break;


        }


    }*/
}
