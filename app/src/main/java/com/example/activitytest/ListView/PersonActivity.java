package com.example.activitytest.ListView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.activitytest.Adapter.ContractAdapter;
import com.example.activitytest.ForAdapter.Contract;
import com.example.activitytest.R;
import com.example.activitytest.view.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonActivity extends BaseActivity {
//    SimpleAdapter adapter;
//    List<Map<String,Object>> list =new ArrayList<Map<String,Object>>();;
//
//    Map<String,Object> map;
//    ListView person_lv;

    List<Contract> contractList=new ArrayList<>();
    ContractAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phoneperson);
       // person_lv= (ListView) findViewById(R.id.person_lv);

        //设置标题栏
        Toolbar toolbar= (Toolbar) findViewById(R.id.contact_toolbal);
        setSupportActionBar(toolbar);
        //设置导航按钮
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        if (ContextCompat.checkSelfPermission(PersonActivity.this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(PersonActivity.this,new String[]{
                    Manifest.permission.READ_CONTACTS
            },1);

        }else {

            phonePerson();
        }



//        String[] from= {"name","number"};
//        int[] to= {R.id.person_name,R.id.person_number};
//        adapter=new SimpleAdapter(PersonActivity.this,list,R.layout.activity_phoneperson_item,from,to);
//        person_lv.setAdapter(adapter);

        //设置RecyclerView
        RecyclerView recyclerView= (RecyclerView) findViewById(R.id.person_recyclerview);
        LinearLayoutManager layoutManager=new LinearLayoutManager(PersonActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        adapter=new ContractAdapter(contractList);
        recyclerView.setAdapter(adapter);



    }

    public void phonePerson(){
        Cursor cursor=null;
        try{
            cursor=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null
            ,null,null,null);
            if(cursor!=null){
                while (cursor.moveToNext()){
                    String name=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.
                            DISPLAY_NAME));
                    String number=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds
                    .Phone.NUMBER));

//                    map=new HashMap<>();
//                    map.put("name",name);
//                    map.put("number",number);
//                    list.add(map);
                    Contract contract=new Contract(name,number);
                    contractList.add(contract);



                }

                adapter.notifyDataSetChanged();

            }


        }catch (Exception e){
            e.printStackTrace();

        }finally {
            if (cursor!=null){
                cursor.close();
            }
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    phonePerson();

                }else {

                    Toast.makeText(PersonActivity.this,"获取权限失败!",Toast.LENGTH_LONG).show();
                }

                break;
            default:
                break;
        }

    }

    //重写菜单项

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