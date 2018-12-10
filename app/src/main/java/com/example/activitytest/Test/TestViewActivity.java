package com.example.activitytest.Test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.activitytest.R;
import com.example.activitytest.UI.DrawView;

public class TestViewActivity extends AppCompatActivity {

    int[] images={R.drawable.back2,R.drawable.back2_mini,R.drawable.beijing,R.drawable.background1};
    private int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testview);

        LinearLayout linearLayout= (LinearLayout) findViewById(R.id.linear_layout);
     /*   final ImageView image=new ImageView(this);
        linearLayout.addView(image);
        image.setImageResource(images[0]);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                try {
                    image.setImageResource(images[count]);

                }catch (Exception e){
                    e.printStackTrace();
                    Log.i("ERROR",e.getMessage());
                }
            }
        });*/
        final DrawView draw=new DrawView(this);
        draw.setMinimumWidth(300);
        draw.setMinimumHeight(300);
        linearLayout.addView(draw);
    }
}