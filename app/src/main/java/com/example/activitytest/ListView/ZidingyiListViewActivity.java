package com.example.activitytest.ListView;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.activitytest.*;
import com.example.activitytest.ForAdapter.Fruit;
import com.example.activitytest.Adapter.MyAdapter;
import com.example.activitytest.view.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class ZidingyiListViewActivity  extends BaseActivity{


    private List<Fruit> fruitList=new ArrayList<>();
    //ListView zidingyi_lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_zidingyi);
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       // zidingyi_lv= (ListView) findViewById(R.id.zidingyi_lv);


        for (int i=0;i<300;i++){
            Fruit fruit=new Fruit("水果"+(i+1),R.mipmap.ic_launcher);
            fruitList.add(fruit);

        }

        RecyclerView recyclerview= (RecyclerView) findViewById(R.id.recyclerview);
//        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);

        recyclerview.setLayoutManager(layoutManager);


        MyAdapter adapter=new MyAdapter(fruitList);
        //zidingyi_lv.setAdapter(adapter);
        recyclerview.setAdapter(adapter);
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

               break;
           default:
               break;
       }
        return true;
    }
}

/*
class FruitAdapter extends ArrayAdapter<Fruit>{
    private int myresource;
    public FruitAdapter(Context context, int resource, List<Fruit> objects) {
        super(context, resource, objects);
        myresource=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Fruit fruit=getItem(position);
       // View view= LayoutInflater.from(getContext()).inflate(myresource, parent, false);
        View view;
        ViewHolder viewHolder;
        if (convertView==null){
            view=LayoutInflater.from(getContext()).inflate(myresource,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.fruit_image=view.findViewById(R.id.fruit_image);
            viewHolder.fruit_name=view.findViewById(R.id.fruit_name);
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.fruit_image.setImageResource(fruit.getImageid());
        viewHolder.fruit_name.setText(fruit.getName());


        //ImageView fruit_image=view.findViewById(R.id.fruit_image);
        //TextView fruit_name=view.findViewById(R.id.fruit_name);
        //fruit_image.setImageResource(fruit.getImageid());
       // fruit_name.setText(fruit.getName());
        return view;

    }
    class ViewHolder{
        ImageView fruit_image;
        TextView fruit_name;
    }
*/
/*class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    List<Fruit> mlist;


    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView fruit_image;
        TextView fruit_name;


        public ViewHolder(View view) {
            super(view);
            fruit_image=view.findViewById(R.id.fruit_image);
            fruit_name=view.findViewById(R.id.fruit_name);
        }
    }
    public  MyAdapter(List<Fruit> list){
        mlist=list;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_zidingyi_item
        ,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
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



}*/
//}
