package com.example.activitytest.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.activitytest.R;

public class ThreadTestActivity extends BaseActivity {

    TextView thread_tv;

    private Handler handler=new Handler(){
        @Override
        //接受信息:handleMessage()方法
        public void handleMessage(Message msg) {

            switch (msg.what){
                case 1:
                    thread_tv.setText("你好");
                    break;
                default:
                    break;

            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threadtest);


        thread_tv= (TextView) findViewById(R.id.thread_tv);
        Button thread_btn= (Button) findViewById(R.id.thread_btn);

        thread_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //子线程不能对UI进行操作,使用异步消息机制进行处理
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        Message message=new Message();
                        message.what=1;
                        handler.sendMessage(message);//发送信息:handle的sendMessage()方法


                    }
                }).start();

            }
        });



    }
}