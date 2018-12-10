package com.example.Service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.activitytest.R;
import com.example.activitytest.view.ServiceTestActivity;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("MyService", "execute oncreate");
        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.e-tecsun.com"));
        PendingIntent pi=PendingIntent.getActivity(MyService.this,0,intent,0);
        Notification notification= new NotificationCompat.Builder(MyService.this).setContentTitle("德生科技")
                .setContentText("德生科技前台服务,时刻为你提供贴心服务!")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .build();

        startForeground(1,notification);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i("MyService","execute onstartcommand");
        return super.onStartCommand(intent,flags,startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("MyService", "execute ondestroy");

    }
}
