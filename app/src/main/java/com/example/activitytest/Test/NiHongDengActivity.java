package com.example.activitytest.Test;

import android.os.Bundle;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.activitytest.R;

import java.util.Timer;
import java.util.TimerTask;


public class NiHongDengActivity extends AppCompatActivity {

    private int currentColor = 0;
    private Chronometer chronometer;
    private Button start,stop;

    private ImageView imageView;
    private Button Plus,Minus,Next;
    final int[] images={R.drawable.imagetest1,R.drawable.imagetest2,R.drawable.imagetest3,R.drawable.imagetest4};
    int currentImg=2;
    private int alpha=255;

    final int[] colors =new int[]{
            R.color.blue,
            R.color.pink,
            R.color.green,
            R.color.grey,
            R.color.light_grey,
            R.color.light_blue
    };
    final int[] names=new int[]{
            R.id.view01,
            R.id.view02,
            R.id.view03,
            R.id.view04,
            R.id.view05,
            R.id.view06
    };

    TextView[] views =new TextView[names.length];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nihongdeng);
        for (int i=0;i<names.length;i++){
            views[i]= (TextView) findViewById(names[i]);
        }
        //定义一个线程周期改变currentColor的变量值
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(1);
            }
        },0,200);

        chronometer= (Chronometer) findViewById(R.id.chronometer_test);
        start= (Button) findViewById(R.id.btn_start);
        stop= (Button) findViewById(R.id.btn_stop);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                start.setEnabled(false);
                stop.setEnabled(true);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.stop();
                start.setEnabled(true);
                stop.setEnabled(false);
            }
        });

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if (SystemClock.elapsedRealtime()-chronometer.getBase()>10*1000){
                    chronometer.stop();
                    start.setEnabled(true);
                }
            }
        });


        //测试ImageView
        imageView= (ImageView) findViewById(R.id.image_test);
        Plus= (Button) findViewById(R.id.add_toumingdu);
        Minus= (Button) findViewById(R.id.reduce_toumingdu);
        Next= (Button) findViewById(R.id.btn_next);
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageResource(images[++currentImg%images.length]);
            }
        });
        Plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alpha=alpha+20;
                if (alpha>=255){
                    alpha=255;
                }
                imageView.setAlpha(alpha);
            }
        });
        Minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alpha=alpha-20;
                if (alpha<=0){
                    alpha=0;
                }
                imageView.setAlpha(alpha);

            }
        });

    }

    android.os.Handler handler=new android.os.Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==1){
                for (int i=0;i<names.length;i++){
                    views[i].setBackgroundResource(colors[(i+currentColor)%names.length]);
                }
                currentColor++;
            }
            super.handleMessage(msg);
        }
    };
}