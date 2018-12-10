package com.example.activitytest.UI;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.activitytest.R;

public class DrawView extends View {

    //定义小球当前位置
    public float currentX= 40;
    public float currentY= 40;

    //定义并创建画笔
    Paint p=new Paint();


    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawView(Context context) {
        super(context);
    }


    @Override
    //绘制图形
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置画笔的颜色
        p.setColor(Color.RED);
        //绘制一个小球
        canvas.drawCircle(currentX,currentY,14,p);
    }

    //重写触摸时间的方法


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //随着触摸点位置的移动,小球的位置也随之移动
        currentX=event.getX();
        currentY=event.getY();
        //通知当前组件重绘自己
        invalidate();
        //表示该方法已经处理该事件
        return true;
    }
}