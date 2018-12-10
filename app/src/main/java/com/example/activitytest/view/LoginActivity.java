package com.example.activitytest.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.activitytest.ListView.LVActivity;
import com.example.activitytest.MaterialUI.MaterialTestActivity;
import com.example.activitytest.R;
import com.example.activitytest.Test.TestViewActivity;

public class LoginActivity extends BaseActivity {

    private EditText username;
    private EditText password;
    private Button  login;
    private CheckBox isWatch;
    private int count=0;


    private CheckBox rememberPassword;
    private SharedPreferences.Editor editor;
    private SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //使用自定义Toolbar
        Toolbar toolbar= (Toolbar) findViewById(R.id.login_toolbar);
        setSupportActionBar(toolbar);

        //设置导航栏按钮(标题栏最左边的按钮)
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.back2_mini);
        }




        username= (EditText) findViewById(R.id.username);
        password= (EditText) findViewById(R.id.password);
        login= (Button) findViewById(R.id.login);
        isWatch= (CheckBox) findViewById(R.id.passworiswatch);

        pref= getSharedPreferences("data",MODE_PRIVATE);
        rememberPassword= (CheckBox) findViewById(R.id.rememberPassword);
        boolean isremember=pref.getBoolean("isremember",false);
        if(isremember){
            username.setText(pref.getString("username","      "));
            password.setText(pref.getString("password","000000"));
            //设置光标在数据最后位置
            username.setSelection(username.getText().length());
            rememberPassword.setChecked(true);

        }

        isWatch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){

                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                }else {

                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }


                password.setSelection(password.getText().length());
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().equals("534226096") &&
                        password.getText().toString().equals("1")) {
                  //  SharedPreferences.Editor editor=PreferenceManager.getDefaultSharedPreferences(LoginActivity.this).edit();
                  //得到SharedPreferences的三种方法其中之一,上面是常见的一种
                    editor=getSharedPreferences("data",MODE_PRIVATE).edit();
                    if (rememberPassword.isChecked()){
                        editor.putString("username",username.getText().toString());
                        editor.putString("password",password.getText().toString());
                        editor.putBoolean("isremember",true);


                    //    Toast.makeText(LoginActivity.this,"你好",Toast.LENGTH_SHORT).show();


                    }else{
                        editor.clear();

                    }
                    editor.commit();
                    Intent intent = new Intent(LoginActivity.this, MaterialTestActivity.class);
                    startActivity(intent);
                    finish();

                } else {

                    Toast.makeText(LoginActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();

                }

            }
        });





    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:

                count++;
                Toast.makeText(LoginActivity.this,"再按返回键,退出程序!",Toast.LENGTH_SHORT).show();
                if(count==2){
                    finish();
                    count=0;
                }
                break;




            default: break;

        }

        return true;

    }
}