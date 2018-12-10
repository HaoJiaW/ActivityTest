package com.example.activitytest.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.activitytest.ForAdapter.Contract;
import com.example.activitytest.R;

import java.util.List;

public class ContractAdapter extends RecyclerView.Adapter<ContractAdapter.ViewHolder> {
    List<Contract> mlist;
    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView person_name;
        TextView person_number;

        public ViewHolder(View view) {
            super(view);
            person_name=view.findViewById(R.id.person_name);
            person_number=view.findViewById(R.id.person_number);


        }
    }
    public ContractAdapter(List<Contract> list){
        mlist=list;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_phoneperson_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
       // holder.person_name.setText();
        return holder;

    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Contract contract=mlist.get(position);
        holder.person_name.setText(contract.getPerson_name());
        holder.person_number.setText(contract.getPerson_number());

    }

    @Override
    public int getItemCount() {

        return mlist.size();
    }
}