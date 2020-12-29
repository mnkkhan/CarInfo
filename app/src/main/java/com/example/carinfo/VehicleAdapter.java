package com.example.carinfo;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.ViewHolder>{

    private List<Item> VehicleList;
    private Context Vcontext;

    public VehicleAdapter(List<Item> vehicleList, Context vcontext) {
        VehicleList = vehicleList;
        Vcontext = vcontext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.recycler_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        final Item item=VehicleList.get(i);
        viewHolder.Number.setText(item.getNumber());
        viewHolder.Name.setText(item.getName());
        viewHolder.Option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                PopupMenu popupMenu=new PopupMenu(Vcontext,viewHolder.Option);
                popupMenu.inflate(R.menu.option_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.view:
                                Toast.makeText(Vcontext,"View Detais of vehicle No. "+item.getNumber(),Toast.LENGTH_SHORT).show();
                                Intent j=new Intent(Vcontext,ViewVehicle.class);
                                j.putExtra("var6",item.getNumber());
                                j.putExtra("varA",item.getUid());
                                view.getContext().startActivity(j);
                                ((com.example.carinfo.VehicleList)Vcontext).finish();
                                break;
                            case R.id.edit:
                                Toast.makeText(Vcontext,item.getUid()+" is going to Edit Details of vehicle No. "+item.getNumber(),Toast.LENGTH_SHORT).show();
                                Intent k=new Intent(Vcontext,Edit.class);
                                k.putExtra("var2",item.getNumber());
                                k.putExtra("varA",item.getUid());
                                view.getContext().startActivity(k);
                                ((com.example.carinfo.VehicleList)Vcontext).finish();
                                break;
                            case R.id.delete:
                                Toast.makeText(Vcontext,"Process of Deletion of vehicle No. "+item.getNumber(),Toast.LENGTH_SHORT).show();
                                Intent l=new Intent(Vcontext,Delete_item.class);
                                l.putExtra("varD",item.getNumber());
                                l.putExtra("varA",item.getUid());
                                view.getContext().startActivity(l);
                                ((com.example.carinfo.VehicleList)Vcontext).finish();
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return VehicleList.size();
    }
    public void setfilter(List<Item> listitem)
    {
        VehicleList=new ArrayList<>();
        VehicleList.addAll(listitem);
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView Number;
        public TextView Name;
        public TextView Option;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Number=(TextView) itemView.findViewById(R.id.id2);
            Name=(TextView) itemView.findViewById(R.id.name2);
            Option=(TextView)itemView.findViewById(R.id.optionMenu);
        }
    }
}
