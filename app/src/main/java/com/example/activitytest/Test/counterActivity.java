package com.example.activitytest.Test;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.activitytest.R;

public class counterActivity extends AppCompatActivity implements View.OnClickListener{

    private Button AC,QIUYU,CHU,CHENG,JIA,JIAN,EQUAL;
    private Button one,two,three,four,five,six,seven,eight,nine,zero,dian;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);

        init();
    }
    private void init(){
        AC= (Button) findViewById(R.id.btn_ac);
        QIUYU= (Button) findViewById(R.id.btn_qiuyu);
        CHU= (Button) findViewById(R.id.btn_chu);
        CHENG= (Button) findViewById(R.id.btn_cheng);
        JIA= (Button) findViewById(R.id.btn_jia);
        JIAN= (Button) findViewById(R.id.btn_jian);
        EQUAL= (Button) findViewById(R.id.btn_equal);
        one= (Button) findViewById(R.id.btn_one);
        two= (Button) findViewById(R.id.btn_two);
        three= (Button) findViewById(R.id.btn_three);
        four= (Button) findViewById(R.id.btn_four);
        five= (Button) findViewById(R.id.btn_five);
        six= (Button) findViewById(R.id.btn_six);
        seven= (Button) findViewById(R.id.btn_seven);
        eight= (Button) findViewById(R.id.btn_eight);
        nine= (Button) findViewById(R.id.btn_nine);
        zero= (Button) findViewById(R.id.btn_zero);
        dian= (Button) findViewById(R.id.btn_dian);
        result= (TextView) findViewById(R.id.tv_result);

        AC.setOnClickListener(this);
        QIUYU.setOnClickListener(this);
        CHU.setOnClickListener(this);
        CHENG.setOnClickListener(this);
        JIA.setOnClickListener(this);
        JIAN.setOnClickListener(this);
        EQUAL.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        zero.setOnClickListener(this);
        dian.setOnClickListener(this);
        result.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        
        switch (v.getId()){
            case R.id.btn_ac:
                break;
            case R.id.btn_qiuyu:
                break;
            case R.id.btn_chu:
                break;
            case R.id.btn_cheng:
                break;
            case R.id.btn_jia:
                break;
            case R.id.btn_jian:
                break;
            case R.id.btn_equal:
                break;
            case R.id.btn_one:
                break;
            case R.id.btn_two:
                break;
            case R.id.btn_three:
                break;
            case R.id.btn_four:
                break;
            case R.id.btn_five:
                break;
            case R.id.btn_six:
                break;
            case R.id.btn_seven:
                break;
            case R.id.btn_eight:
                break;
            case R.id.btn_nine:
                break;
            case R.id.btn_dian:
                break;
            case R.id.tv_result:
                break;





        }
    }
}