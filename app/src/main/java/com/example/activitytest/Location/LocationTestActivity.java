package com.example.activitytest.Location;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.example.activitytest.R;
import com.example.activitytest.view.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class LocationTestActivity extends BaseActivity {
    public LocationClient mlocationClient;
    private TextView location_tv;
    List<String> permissionList;

    private MapView mapView;
    private BaiduMap baiduMap;
    private boolean first =true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // mlocationClient=new LocationClient(LocationTestActivity.this);
        mlocationClient=new LocationClient(getApplicationContext());
        //注册一个位置监听器
        mlocationClient.registerLocationListener(new MyLocationLister());
        SDKInitializer.initialize(getApplicationContext());



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locationtest);

        mapView= (MapView) findViewById(R.id.mapview);

        baiduMap=mapView.getMap();

        location_tv= (TextView) findViewById(R.id.locationtest_tv);

        permissionList=new ArrayList<>();

        permission();
        baiduMap.setMyLocationEnabled(true);

        //设置标题栏
        Toolbar toolbar= (Toolbar) findViewById(R.id.laocation_toolbar);
        setSupportActionBar(toolbar);
        //设置导航按钮
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);

        }
    }

    private void permission(){
        if (ContextCompat.checkSelfPermission(LocationTestActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){

            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);

//            ActivityCompat.requestPermissions(LocationTestActivity.this,new String[]{
//                    Manifest.permission.ACCESS_FINE_LOCATION
//            },1);

        }if (ContextCompat.checkSelfPermission(LocationTestActivity.this,Manifest.permission.READ_PHONE_STATE)!=
                PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_PHONE_STATE);

        }if (ContextCompat.checkSelfPermission(LocationTestActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                !=PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()){
            String[] permissions=permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(LocationTestActivity.this,permissions,1);


        }else {
            requestLocation();

        }






    }
    private void requestLocation(){
        //更新位置的方法
        initLocation();
        mlocationClient.start();

    }
    private void initLocation(){
        LocationClientOption option=new LocationClientOption();
        option.setScanSpan(5000);//设置间隔时间为5秒
        //设置定位模式为传感器模式,只能使用GPS定位的模式
        option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);

        //设置定位需要详细地址
        option.setIsNeedAddress(true);

        mlocationClient.setLocOption(option);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode){
            case 1:
//                if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
//
//
//                    requestLocation();
//                }
//
                 if(grantResults.length>0){

                     for(int a:grantResults){
                         if (a!=PackageManager.PERMISSION_GRANTED){
                            // requestLocation();
                             Toast.makeText(LocationTestActivity.this,"必须同意所有权限才可以!!",Toast.LENGTH_SHORT).show();
                             finish();
                             return;
                         }
                     }
                     requestLocation();

                 }else {
                     Toast.makeText(LocationTestActivity.this,"获取权限失败!",Toast.LENGTH_SHORT).show();
                     finish();
                 }
                break;
            default:
                break;

        }
    }

    public class MyLocationLister implements BDLocationListener{


        @Override
        public void onReceiveLocation(BDLocation Location) {

            StringBuilder mylocation=new StringBuilder();
            mylocation.append("纬度:").append(Location.getLatitude()).append("\n");
            mylocation.append("经度:").append(Location.getLongitude()).append("\n");

            //详细地址
            mylocation.append("国家:").append(Location.getCountry()).append("\n");
            mylocation.append("省:").append(Location.getProvince()).append("\n");
            mylocation.append("市:").append(Location.getCity()).append("\n");
            mylocation.append("区:").append(Location.getDistrict()).append("\n");
            mylocation.append("街道:").append(Location.getStreet()).append("\n");

            mylocation.append("定位方式:");
            if (Location.getLocType()==BDLocation.TypeGpsLocation){
                mylocation.append("GPS");
            }
            if (Location.getLocType()==BDLocation.TypeNetWorkLocation){
                mylocation.append("网络");

            }
            if (Location.getLocType()==BDLocation.TypeGpsLocation
                    ||Location.getLocType()==BDLocation.TypeNetWorkLocation){
                navigateTo(Location);

            }
            location_tv.setText(mylocation);
        }
    }

    private void navigateTo(BDLocation location){
        if (first) {
            LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
            baiduMap.animateMapStatus(update);
            update = MapStatusUpdateFactory.zoomTo(16f);
            baiduMap.animateMapStatus(update);
            first=false;
        }
        MyLocationData.Builder locationBudilder=new MyLocationData.Builder();
        locationBudilder.latitude(location.getLatitude());
        locationBudilder.longitude(location.getLongitude());
        MyLocationData locationData=locationBudilder.build();
        baiduMap.setMyLocationData(locationData);



    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mlocationClient.stop();
        mapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);
    }

    //重写菜单功能

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            default:
                break;


        }
        return true;
    }
}