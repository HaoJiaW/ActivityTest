package com.example.activitytest.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ActivityCollector;
import com.example.activitytest.R;

public class FirstActivity extends BaseActivity {
    Button click;
    Button btnFinish;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frist_layout);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        Log.i("TAG", "onCreate");
        btnFinish= (Button) findViewById(R.id.btn_finish);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        click= (Button) findViewById(R.id.button1);

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置一个进度对话框
                ProgressDialog progressDialog=new ProgressDialog(FirstActivity.this);
                progressDialog.setTitle("提示");
                progressDialog.setMessage("    请稍候...    ");
                progressDialog.setCancelable(true);
                progressDialog.show();



                //设置圆形进度框是否可见
                /*if(progressBar.getVisibility()==View.GONE){

                    progressBa
                    r.setVisibility(View.VISIBLE);
                }else{

                    progressBar.setVisibility(View.GONE);
                }
*/

                //设置水平进度框的进度
                /*int progress=progressBar.getProgress();
                progress=progress+100;
                progressBar.setProgress(progress);
                if (progress==1000){

                    Toast.makeText(FirstActivity.this,"点击完成",Toast.LENGTH_LONG).show();

                }*/




               // Toast.makeText(FirstActivity.this,"点击了按钮!",Toast.LENGTH_LONG).show();
                //显式intent
                //Intent的数据传递(向下一个活动传值)
               /* Intent intent=new Intent(FirstActivity.this,MainActivity.class);
                String data="这是Intent传递的数据";
                intent.putExtra("data_name",data);
                startActivity(intent);*/

                //Intent的数据传递(向上一个活动返回值)
//                Intent  intent=new Intent(FirstActivity.this,MainActivity.class);
//                startActivityForResult(intent,1);




                //隐式intent
               /* Intent intent=new Intent("com.example.activitytest.ACTION_START" );
                intent.addCategory("com.example.activitytest.MY_CATEGORY");
                startActivity(intent);*/

                //更多隐式用法
                //跳转到淮南师范的官网
                /*Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("Http://www.hnnu.edu.cn"));
                startActivity(intent);
                */

//                //拨打手机号10086
//                Intent intent=new Intent(Intent.ACTION_DIAL);
//                //第三方的模拟器不能调用拨号键盘,系统自带的模拟器可以
//                intent.setData(Uri.parse("tel:10086"));
//                startActivity(intent);


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if(resultCode==RESULT_OK){

                    click.setText(data.getStringExtra("return"));
                }

                break;
            default:
                Toast.makeText(FirstActivity.this,"有错误",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("TAG","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("TAG","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("TAG","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("TAG","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("TAG", "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("TAG","onRestart");
    }

    //重写菜单的菜单项显式
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    //重写菜单项的功能
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_item:
                Toast.makeText(FirstActivity.this,"点击了add",Toast.LENGTH_LONG).show();
                break;
            case R.id.remove_item:
                Toast.makeText(FirstActivity.this, "点击了remove", Toast.LENGTH_LONG).show();
                break;
            case R.id.finish:
                ActivityCollector.finishAll();
                break;
            default:
                break;
        }
        return true;
    }

    //重写返回键的方法
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        AlertDialog.Builder ad = new AlertDialog.Builder(FirstActivity.this);
        ad.setTitle("提示");
        ad.setMessage("是否退出软件");
        //设置不可取消
        ad.setCancelable(false);
        ad.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        ad.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        ad.show();
    }
}