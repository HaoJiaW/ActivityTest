package com.example.activitytest;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.activitytest.view.BaseActivity;

/**
 * 测试折叠标题栏
 */
public class FriutContentActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruitcontent);

        //设置标题栏
        Toolbar toolbar= (Toolbar) findViewById(R.id.Z_toolbar);
        setSupportActionBar(toolbar);

        CollapsingToolbarLayout collapsingToolbarLayout= (CollapsingToolbarLayout) findViewById(R.id.collasping_toolbar);
        ImageView Z_beijig= (ImageView) findViewById(R.id.Z_beijing);
        TextView Z_content= (TextView) findViewById(R.id.Z_content);
        collapsingToolbarLayout.setTitle("可爱的--标题栏");
        Z_beijig.setImageResource(R.drawable.idimage2);
        Z_content.setText("【玩运动的中级SUV！马自达CX-8下线】10日，" +
                "马自达CX-8在长安马自达正式下线。9月的成都车展上" +
                "，长安马自达国产CX-8正式发布，新车定位中大型7座SUV。" +
                "1、外观上基本延续了海外版车型的家族式设计语言，采用了" +
                "最新的“魂动”设计，盾型前格栅，前大灯组为全LED光源。" +
                "2、内饰上与海外设计保持一致，配备全新的三幅式方向盘，" +
                "以及7英寸悬浮式中控屏幕。车厢内部采用7座布局，顶配车型" +
                "的座椅材质为Nappa真皮。3、动力方面，新车将搭载2.5L直列" +
                "四缸自然吸气发动机，最大功率为141kW，传动系统预计匹配6" +
                "挡手自一体变速箱。此外还有四驱系统。7座SUV之中，目前还" +
                "没有一款民用品牌车型在主打运动、操控，而这正是马自达在" +
                "北美市场里所擅长的。已经征服了北美市场的马自达中大型SU" +
                "V，在中国市场应该会有不同思路的一款车型。");


        //设置导航按钮
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return  true;
    }
}