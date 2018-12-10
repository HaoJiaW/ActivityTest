package com.example.activitytest.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.activitytest.ForAdapter.Fruit;
import com.example.activitytest.R;

import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    private Context mcontext;
    List<Fruit> mfruitList;



    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView fruit_image;
        TextView fruit_name;


        public ViewHolder(View itemView) {
            super(itemView);
            cardView= (CardView) itemView;
          //  fruit_image=itemView.findViewById(R.id.fruit_image);
          //  fruit_name=itemView.findViewById(R.id.fruit_name);

        }


    }

    public FruitAdapter(List<Fruit> fruitList){

        mfruitList=fruitList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (mcontext!=null){
            mcontext=parent.getContext();


        }
        View view= LayoutInflater.from(mcontext).inflate(R.layout.fruit_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Fruit fruit=mfruitList.get(position);
        holder.fruit_name.setText(fruit.getName());
        //使用Glide来加载图片
        Glide.with(mcontext).load(fruit.getImageid()).into(holder.fruit_image);

    }

    @Override
    public int getItemCount() {

        return mfruitList.size();
    }

}