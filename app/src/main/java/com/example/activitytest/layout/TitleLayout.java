package com.example.activitytest.layout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.activitytest.R;

public class TitleLayout extends LinearLayout {

    Button back;
    Button edit;

    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);


        //在构造方法中对标题栏进行动态加载

        //通过LayoutInflater的from()方法可以构建一个LayoutInfalater对象
        //然后调用inflate()方法动态加载一个布局文件,inflate()方法的两个参数:要加载的布局的id
        //第二个是给加载好的布局加一个父布局,这里我们制定为Titlelayout,所以是this
        LayoutInflater.from(context).inflate(R.layout.activity_title,this);

        back=findViewById(R.id.back);//这样竟然也可以
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //将TitleLayout的上下文,也就是Layout转化成Activity
                ((Activity)getContext()).finish();
            }
        });

        edit=findViewById(R.id.edit);
        edit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                (getContext()).startActivity(intent);



            }
        });


    }
}