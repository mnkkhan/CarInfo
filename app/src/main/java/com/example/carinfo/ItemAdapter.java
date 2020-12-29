package com.example.carinfo;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{

    private List<Item> itemList;
    private Context context;

    public ItemAdapter(List<Item> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }



    @Override
    public ItemViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.item_info,viewGroup,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder itemViewHolder, final int i) {

        itemViewHolder.Number.setText(itemList.get(i).getNumber());
        itemViewHolder.Name.setText(itemList.get(i).getName());


        itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, itemList.get(i).getUid()+" View Details of "+itemList.get(i).getName(), Toast.LENGTH_SHORT).show();
                Intent j=new Intent(context,ViewVehicleUser.class);
                j.putExtra("var5",itemList.get(i).getNumber());
                view.getContext().startActivity(j);
                ((UserHomepage)context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
    public void setfilter(List<Item> listitem)
    {
        itemList=new ArrayList<>();
        itemList.addAll(listitem);
        notifyDataSetChanged();

    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView Number;
        TextView Name;
        public ItemViewHolder(View itemView) {
            super(itemView);
            Number=(TextView) itemView.findViewById(R.id.id);
            Name=(TextView) itemView.findViewById(R.id.name);
        }
    }
}
