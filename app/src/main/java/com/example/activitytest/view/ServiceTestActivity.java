package com.example.activitytest.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.Service.MyService;
import com.example.activitytest.R;

public class ServiceTestActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicetest);

        Button startService= (Button) findViewById(R.id.startservice_btn);
        Button stopService= (Button) findViewById(R.id.stopservice_btn);

        startService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start_intent=new Intent(ServiceTestActivity.this, MyService.class);
                startService(start_intent);


            }
        });
        stopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent stop_intent=new Intent(ServiceTestActivity.this,MyService.class);
                stopService(stop_intent);
            }
        });

    }
}