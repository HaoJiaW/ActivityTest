package com.example.activitytest.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.activitytest.ForAdapter.Fruit;
import com.example.activitytest.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Fruit> mlist;



    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView fruit_name;
        ImageView fruit_image;
        public ViewHolder(View view) {
            super(view);
            fruit_image=view.findViewById(R.id.fruit_image);
            fruit_name=view.findViewById(R.id.fruit_name);

        }

    }

    public MyAdapter(List<Fruit> list){
        mlist=list;
    }
    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item
        ,parent,false);
        ViewHolder holder=new ViewHolder(view);

        holder.fruit_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(parent.getContext(),"点击了图片",Toast.LENGTH_SHORT).show();
            }
        });
        holder.fruit_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(parent.getContext(),"点击了文字",Toast.LENGTH_SHORT).show();

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fruit fruit=mlist.get(position);
        holder.fruit_image.setImageResource(fruit.getImageid());
        holder.fruit_name.setText(fruit.getName());

    }

    @Override
    public int getItemCount() {

        return mlist.size();
    }


}