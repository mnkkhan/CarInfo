package com.example.carinfo;

import android.content.Context;
import android.content.Intent;
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

public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.ViewHolder> {

    private List<Item> AdminList;
    private Context Acontext;

    public AdminAdapter(List<Item> adminList, Context acontext) {
        AdminList = adminList;
        Acontext = acontext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.recycler_item,viewGroup,false);
        return new AdminAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {

        final Item item=AdminList.get(i);
        viewHolder.Auid.setText(item.getNumber());
        viewHolder.Aname.setText(item.getName());
        viewHolder.Option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                PopupMenu popupMenu=new PopupMenu(Acontext,viewHolder.Option);
                popupMenu.inflate(R.menu.option);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.View:
                                Toast.makeText(Acontext,item.getUid()+" View Details of "+item.getName(),Toast.LENGTH_SHORT).show();
                                Intent j=new Intent(Acontext,AdminView.class);
                                j.putExtra("var5",item.getNumber());
                                j.putExtra("varA",item.getUid());
                                view.getContext().startActivity(j);
                                ((com.example.carinfo.AdminList)Acontext).finish();
                                break;
                            case R.id.Delete:
                                Toast.makeText(Acontext,"You want to Remove "+item.getName()+" from Admin",Toast.LENGTH_SHORT).show();
                                Intent l=new Intent(Acontext,AdminDelete.class);
                                l.putExtra("varD",item.getNumber());
                                l.putExtra("varA",item.getUid());
                                view.getContext().startActivity(l);
                                ((com.example.carinfo.AdminList)Acontext).finish();
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
        return AdminList.size();
    }
    public void setfilter(List<Item> listitem)
    {
        AdminList=new ArrayList<>();
        AdminList.addAll(listitem);
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView Auid;
        public TextView Aname;
        public TextView Option;
        public ViewHolder(View itemView) {
            super(itemView);
            Auid=(TextView) itemView.findViewById(R.id.id2);
            Aname=(TextView) itemView.findViewById(R.id.name2);
            Option=(TextView)itemView.findViewById(R.id.optionMenu);
        }
    }
}
