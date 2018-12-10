package com.example.activitytest.ListView;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.activitytest.Internet.HttpUrlConnectionActivity;
import com.example.activitytest.Internet.WebViewActivity;
import com.example.activitytest.Location.LocationTestActivity;
import com.example.activitytest.MaterialUI.MaterialTestActivity;
import com.example.activitytest.SQLite.MyDataBaseHelper;
import com.example.activitytest.view.BaseActivity;
import com.example.activitytest.R;
import com.example.activitytest.view.PhotoActivity;
import com.example.activitytest.view.ServiceTestActivity;
import com.example.activitytest.view.ThreadTestActivity;

public class LVActivity extends BaseActivity {
    private DatePicker datePicker;
    private TimePicker timePicker;

    private String list[]={"语文","数学","英语","地理","历史","政治","物理","化学","生物","更新数据",
            "添加数据","创建数据库","删除数据","查询数据","打电话","联系人列表","发送一个通知",
            "我要拍照","音乐播放器","视频播放器","打开百度","发送http请求","测试线程","测试服务",
            "我的位置","测试新的UI界面"};

    private NetWorkChangeReceive netWorkChangeReceive;
    private LocalBroadCastReceiver localBroadCastReceiver;
    private IntentFilter intentFilter;
    private IntentFilter intentFilter1;

    //本地广播核心
    private LocalBroadcastManager localBroadcastManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lv);
        //本地广播的核心!!!
        localBroadcastManager=LocalBroadcastManager.getInstance(this);

        intentFilter=new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        netWorkChangeReceive =new NetWorkChangeReceive();
        registerReceiver(netWorkChangeReceive,intentFilter);

        intentFilter1=new IntentFilter();
        intentFilter1.addAction("android.nihao");
        localBroadCastReceiver=new LocalBroadCastReceiver();
        //注册广播接收器调用localBroadcastManager
        localBroadcastManager.registerReceiver(localBroadCastReceiver, intentFilter1);

        //创建数据库,声明一个MyDataBaseHelper对象

        //构建SQLiteOpenHelper实例;
        //需要升级,将版本号从1改为2
        //final MyDataBaseHelper dbHelper =new MyDataBaseHelper(this,"BookStore.db",null,1);

        final MyDataBaseHelper dbHelper =new MyDataBaseHelper(this,"BookStore.db",null,2);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(LVActivity.this,android.R.layout.simple_list_item_1,list);
        ListView lv= (ListView) findViewById(R.id.lv);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch(position){
                    case 0:
                        Intent intent=new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("http://www.yuwen.com"));
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1=new Intent(Intent.ACTION_VIEW);
                        intent1.setData(Uri.parse("http://www.shuxue.com"));
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2=new Intent(Intent.ACTION_VIEW);
                        intent2.setData(Uri.parse("http://www.yingyu.com"));
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3=new Intent(Intent.ACTION_VIEW);
                        intent3.setData(Uri.parse("http://www.dili.com"));
                        startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4=new Intent(Intent.ACTION_VIEW);
                        intent4.setData(Uri.parse("http://www.lishi.com"));
                        startActivity(intent4);
                        break;
                    case 5:
                        Intent intent5=new Intent(Intent.ACTION_VIEW);
                        intent5.setData(Uri.parse("http://www.zhengzhi.com"));
                        startActivity(intent5);
                        break;
                    case 6:
                        //使用intent 发送广播,并把要发送的广播的值传入
                        Intent intent6=new Intent("com.example.broadcasttest.My");
                        sendBroadcast(intent6);
                    break;
                    case 7:

                        Intent intent7=new Intent("android.nihao");
                        //发送广播的时候调用localBroadcastManager
                        localBroadcastManager.sendBroadcast(intent7);
                        break;
                    case 8:

                        Intent intent8=new Intent("com.example.activitytest.view.nihao");
                        startActivity(intent8);
                        break;
                    case 9:
//                        SQLiteDatabase db=dbHelper.getWritableDatabase();
//                        ContentValues values=new ContentValues();
//                        values.put("price", "9.9");
//                        //表示更新name为sanmaoliulangji表的价格
//                        db.update("book",values,"name = ?",new String[]{"sanmaliulangji"});
                        break;


                    case 10:
//                        SQLiteDatabase db=dbHelper.getWritableDatabase();
//                        ContentValues values=new ContentValues();
//                        //values用于组装数据
//                        values.put("author","sanmao");
//                        values.put("price","30.0");
//                        values.put("pages","290");
//                        values.put("name","sanmapliulangji");
//                        db.insert("book",null,values);//向book表中添加一条数据
                        break;

                    case 11:

                        //调用SQLiteOpenHelper的getWritableDatabase()方法创建数据库
                        dbHelper.getWritableDatabase();
                        break;
                    case 12:
//                         SQLiteDatabase db=dbHelper.getWritableDatabase();
//                        //表示删除页数大于10的书
//                        db.delete("book","pages>?",new String[]{"10"});
                        break;
                    case 13:
                        SQLiteDatabase db=dbHelper.getWritableDatabase();
                        Cursor cursor=db.query("book",null,null,null,null,null,null);
                        if (cursor.moveToFirst()){
                            do {
                                String name=cursor.getString(cursor.getColumnIndex("name"));
                                String author=cursor.getString(cursor.getColumnIndex("author"));
                                int pages=cursor.getInt(cursor.getColumnIndex("pages"));
                                double price=cursor.getDouble(cursor.getColumnIndex("price"));
                                Log.i("LVActivity","book de name is:"+name);
                                Log.i("LVActivity","book de author is:"+author);
                                Log.i("LVActivity","book de pages is:"+pages);
                                Log.i("LVActivity","book de price is:"+price);
                            }while(cursor.moveToNext());


                        }cursor.close();
                        break;
                    case 14:
                        //适用于android6.0以下的版本,用来打电话
                        /*try {
                            Intent intent10 = new Intent(Intent.ACTION_CALL);
                            intent10.setData(Uri.parse("tel:13966495401"));
                            startActivity(intent10);
                        }catch (Exception e){
                            e.printStackTrace();
                        }*/

                        //android 6.0以上版本的打电话
                        if (ContextCompat.checkSelfPermission(LVActivity.this, android.Manifest.permission.CALL_PHONE)
                                != PackageManager.PERMISSION_GRANTED){

                            ActivityCompat.requestPermissions(LVActivity.this,new String[]{
                                    android.Manifest.permission.CALL_PHONE
                            },1);


                        }else{
                            call();

                        }
                        break;
                    case 15:
                        Intent intent9=new Intent(LVActivity.this,PersonActivity.class);
                        startActivity(intent9);
                        break;

                    case 16:
                        Intent intent11=new Intent(LVActivity.this,PersonActivity.class);
                        PendingIntent pi=PendingIntent.getActivity(LVActivity.this,0,intent11,0);

                        NotificationManager manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        Notification notification=new NotificationCompat.Builder(LVActivity.this).
                                setContentTitle("今天你过得好吗?").setContentText("众所周知,今天是国庆长假后的" +
                                "第一天,那么,怎么能过得好呢").setWhen(System.currentTimeMillis())
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                                .setContentIntent(pi).setAutoCancel(true).build();
                        manager.notify(1,notification);
                        Toast.makeText(LVActivity.this,"通知发送成功",Toast.LENGTH_SHORT).show();


                        break;

                    case 17:
                        Intent intent12=new Intent(LVActivity.this, PhotoActivity.class);
                        startActivity(intent12);
                        break;

                    case 18:
                        Intent intent13=new Intent(LVActivity.this,MusicActivity.class);
                        startActivity(intent13);
                        break;
                    case 19:
                        Intent intent14=new Intent(LVActivity.this,VideoActivity.class);
                        startActivity(intent14);
                        break;
                    case 20:
                        Intent intent15=new Intent(LVActivity.this, WebViewActivity.class);
                        startActivity(intent15);
                        break;
                    case 21:
                        Intent intent16=new Intent(LVActivity.this, HttpUrlConnectionActivity.class);
                        startActivity(intent16);
                        break;
                    case 22:
                        Intent intent17=new Intent(LVActivity.this, ThreadTestActivity.class);
                        startActivity(intent17);
                        break;
                    case 23:
                        Intent intent18=new Intent(LVActivity.this, ServiceTestActivity.class);
                        startActivity(intent18);
                        break;
                    case 24:
                        Intent intent19=new Intent(LVActivity.this, LocationTestActivity.class);
                        startActivity(intent19);
                        break;
                    case 25:
                        Intent intent20=new Intent(LVActivity.this, MaterialTestActivity.class);
                        startActivity(intent20);
                        break;



                    default:
                        break;
                }


            }
        });






    }
    public void call(){
         try {
                            //Intent.ACTION_CALL代表的是直接拨打电话,ACTION_DIAL代表跳转到拨打电话的键盘页
                            Intent intent10 = new Intent(Intent.ACTION_CALL);
                            intent10.setData(Uri.parse("tel:13966495401"));
                            startActivity(intent10);
                        }catch (Exception e){
                            e.printStackTrace();
                        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode){
            case 1:
                if (grantResults.length>0&&grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    call();
                }
                else{

                    Toast.makeText(LVActivity.this,"运行失败",Toast.LENGTH_SHORT).show();
                }
                break;

            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(netWorkChangeReceive);
        //取消注册广播接收器调用了localBroadcastManager
        localBroadcastManager.unregisterReceiver(localBroadCastReceiver);
    }



    class NetWorkChangeReceive extends BroadcastReceiver{


        @Override
        public void onReceive(Context context, Intent intent) {

            // Toast.makeText(context,"网络变化",Toast.LENGTH_LONG).show();
            ConnectivityManager connectivityManager= (ConnectivityManager) getSystemService(context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
            if (networkInfo!=null&&networkInfo.isAvailable()){

                Toast.makeText(context,"当前网络可用",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context,"当前网络不可用",Toast.LENGTH_SHORT).show();

            }


        }
    }
    class LocalBroadCastReceiver extends BroadcastReceiver{


        @Override
        public void onReceive(Context context, Intent intent) {

            Toast.makeText(context,"您点击了化学",Toast.LENGTH_SHORT).show();

        }
    }

    //重写菜单项方法:onCreateOptionsMenu()
    //设置强制下线的菜单选项
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_lvactivity,menu);
        return true;
    }

    //重写菜单项的功能方法:onOptionsItemSelected

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.offline:
                //此处发送强制下线的广播
                Intent intent=new Intent("android.offline");
                sendBroadcast(intent);

                break;
            case R.id.sava_data:
                SharedPreferences.Editor editor=getSharedPreferences("data",MODE_PRIVATE).edit();
                editor.putString("nihao","你好");
                editor.putInt("shuzi",7);
                editor.putBoolean("islove",false);
                //apply()用于提交
                editor.apply();
                break;
            case R.id.restore_data:
                SharedPreferences sp=getSharedPreferences("data",MODE_PRIVATE);
                String nihao=sp.getString("nihao", "");
                int shuzi=sp.getInt("shuzi", 0);
                Boolean islove=sp.getBoolean("islove",false);
                Log.i("LVActivity","打招呼:"+nihao);
                Log.i("LVActivity","你最喜欢的数字是"+shuzi);
                Log.i("LVActivity","你恋爱了吗?"+islove);
               // ActivityCollector.finishAll();

                break;





        }
        return true;
    }
}