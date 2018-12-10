package com.example.activitytest.MaterialUI;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.activitytest.FriutContentActivity;
import com.example.activitytest.ForAdapter.Fruit;
import com.example.activitytest.Internet.HttpUrlConnectionActivity;
import com.example.activitytest.ListView.MusicActivity;
import com.example.activitytest.ListView.PersonActivity;
import com.example.activitytest.ListView.VideoActivity;
import com.example.activitytest.ListView.ZidingyiListViewActivity;
import com.example.activitytest.Location.LocationTestActivity;
import com.example.activitytest.Adapter.MyAdapter;
import com.example.activitytest.R;
import com.example.activitytest.Test.NiHongDengActivity;
import com.example.activitytest.Test.counterActivity;
import com.example.activitytest.view.BaseActivity;
import com.example.activitytest.view.PhotoActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MaterialTestActivity extends BaseActivity {

    private DrawerLayout drawerlayout;

    private NavigationView navigationview;

    private List<Fruit> fruitList=new ArrayList<>();
    private MyAdapter adapter;
    //设置列表显示
    //SimpleAdapter adapter;
    //ListView material_lv;
    //List<Map<String,Object>> list=new ArrayList<>();
    //Map<String,Object> map;

    //设置下拉刷新
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materialtest);

        //设置自定义ToolBar
        //Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        android.support.v7.widget.Toolbar toolbar= (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //设置标题栏的导航按钮
        drawerlayout= (DrawerLayout) findViewById(R.id.drawerlayout);
        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            //设置导航按钮存在
            actionBar.setDisplayHomeAsUpEnabled(true);
            //设置导航按钮的图标
            actionBar.setHomeAsUpIndicator(R.drawable.menu_mini);

        }

        //设置滑动菜单的子项功能
        navigationview= (NavigationView) findViewById(R.id.nav_view);
        //设置默认点击
       // navigationview.setCheckedItem(R.id.localmedia);
        navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                //drawerlayout.closeDrawers();

                switch (menuItem.getItemId()){
                    case R.id.contact:
                        Intent intent1=new Intent(MaterialTestActivity.this, PersonActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.location:
                        Intent intent2=new Intent(MaterialTestActivity.this, LocationTestActivity.class);
                        startActivity(intent2);
                        break;
                    case R.id.localmedia:
                        Intent intent3=new Intent(MaterialTestActivity.this, MusicActivity.class);
                        startActivity(intent3);
                        break;
                    case R.id.localvideo:
                        Intent intent4=new Intent(MaterialTestActivity.this, VideoActivity.class);
                        startActivity(intent4);
                        break;
                    case R.id.photo:
                        Intent intent5=new Intent(MaterialTestActivity.this, PhotoActivity.class);
                        startActivity(intent5);
                        break;
                    case R.id.zidingyi_listview:
                        Intent intent6=new Intent(MaterialTestActivity.this, ZidingyiListViewActivity.class);
                        startActivity(intent6);
                        break;
                    case R.id.Z_Activity:
                        Intent intent7=new Intent(MaterialTestActivity.this, FriutContentActivity.class);
                        startActivity(intent7);
                        break;
                    case R.id.Pull_test:
                        Intent intent8=new Intent(MaterialTestActivity.this, HttpUrlConnectionActivity.class);
                        startActivity(intent8);
                        break;
                    case R.id.Counter:
                        Intent intent9=new Intent(MaterialTestActivity.this,counterActivity.class);
                        startActivity(intent9);
                    case R.id.deng:
                        Intent intent10=new Intent(MaterialTestActivity.this, NiHongDengActivity.class);
                        startActivity(intent10);
                    default:
                        break;
                }

                return true;

            }
        });

        //设置ListView
//        for (int i=0;i<50;i++){
//            map=new HashMap<>();
//            map.put("material_image",R.mipmap.ic_launcher);
//            map.put("material_name","图片"+(i+1));
//            list.add(map);

        initList();

        RecyclerView material_recyview= (RecyclerView) findViewById(R.id.material_recyclerview);
        GridLayoutManager layoutManager=new GridLayoutManager(MaterialTestActivity.this,2);
        material_recyview.setLayoutManager(layoutManager);

        adapter=new MyAdapter(fruitList);
        material_recyview.setAdapter(adapter);


//
//        }
//        adapter.notifyDataSetChanged();
        //material_lv= (ListView) findViewById(R.id.material_lv);


       // String[] from={"material_image","material_name"};
        //int[] to={R.id.material_image,R.id.material_name};
       // adapter=new SimpleAdapter(MaterialTestActivity.this,list,R.layout.fruit_item,from,to);

        //设置listview为网格形式
        //material_lv.setAdapter(adapter);



        //设置悬浮按钮的功能
        FloatingActionButton fab= (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //弹出toast
                //Toast.makeText(MaterialTestActivity.this,"点击了悬浮按钮",Toast.LENGTH_SHORT).show();

                //设置先进的提示工具:Snackbar
                Snackbar.make(v,"删除了文件",Snackbar.LENGTH_SHORT).setAction("恢复",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Toast.makeText(MaterialTestActivity.this,"恢复了文件",Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });

        //设置下拉刷新
        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        //设置下拉刷新进度条
        swipeRefreshLayout.setColorSchemeColors(R.color.colorPrimary);
        //设置下拉监听
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshList();
            }
        });

    }

    public  void  initList() {
        fruitList.clear();
        for (int i = 0; i<100; i++) {
            Random random=new Random();
            int index=random.nextInt(100);
            Fruit fruit = new Fruit("水果" + index, R.mipmap.ic_launcher);
            fruitList.add(fruit);

        }
    }

    public void refreshList(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                }catch (Exception e){
                    e.printStackTrace();

                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initList();
                        adapter.notifyDataSetChanged();
                        //表示刷新事件结束
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbal,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.wangyiyun_back:
                finish();
                break;
            case R.id.wangyiyun_setup:
                Toast.makeText(MaterialTestActivity.this,"你点击了设置按钮!",Toast.LENGTH_SHORT).show();

                break;

            //设置导航按钮的功能:划出左边的菜单
            case android.R.id.home:
                drawerlayout.openDrawer(GravityCompat.START);
                break;
            default:
                break;


        }

        return true;
    }
}