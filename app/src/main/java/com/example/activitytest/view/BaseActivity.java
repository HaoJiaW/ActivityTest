package com.example.activitytest.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.ActivityCollector;

public class BaseActivity extends AppCompatActivity {

    private OffLineReceiver offLineReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(1);
        super.onCreate(savedInstanceState);
        Log.i("BaseActivity", getClass().getSimpleName());
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter=new IntentFilter();
        //接受"android.offline"的广播
        intentFilter.addAction("android.offline");
         offLineReceiver=new OffLineReceiver();
        registerReceiver(offLineReceiver,intentFilter);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (offLineReceiver!=null){
            unregisterReceiver(offLineReceiver);
            offLineReceiver=null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    class OffLineReceiver extends BroadcastReceiver{


        @Override
        public void onReceive(final Context context, Intent intent) {

            AlertDialog.Builder ad=new AlertDialog.Builder(context);
            ad.setCancelable(false);
            ad.setTitle("提示");
            ad.setMessage("当前账号被登录,强制下线");
            ad.setNegativeButton("退出", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCollector.finishAll();

                }
            });

            ad.setPositiveButton("重新登录", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCollector.finishAll();
                    Intent intent=new Intent(context,LoginActivity.class);
                    context.startActivity(intent);



                }
            });
            ad.show();


        }
    }
}