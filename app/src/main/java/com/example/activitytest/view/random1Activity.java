package com.example.activitytest.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.activitytest.R;
import com.example.activitytest.view.BaseActivity;

import java.util.List;

public class random1Activity extends BaseActivity {

    Button bt;
    TextView tv;
    int[] a={1,2,3,4,5,6};
    List<String> list;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);
        bt= (Button) findViewById(R.id.click);
        tv= (TextView) findViewById(R.id.tv);
        position=(int)(Math.random()*6);


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tv.setText("你好" + a[position]);

        }
        });
    }

}