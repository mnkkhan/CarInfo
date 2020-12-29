package com.example.carinfo;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{

    private List<Item> UserList;
    private Context Ucontext;

    public UserAdapter(List<Item> userList, Context ucontext) {
        UserList = userList;
        Ucontext = ucontext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.item_info,viewGroup,false);
        return new UserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final Item item=UserList.get(i);
        viewHolder.Uid.setText(item.getNumber());
        viewHolder.Uname.setText(item.getName());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Ucontext, item.getUid()+" View Details of "+item.getName(), Toast.LENGTH_SHORT).show();
                Intent j=new Intent(Ucontext,UserView.class);
                j.putExtra("varU",item.getNumber());
                j.putExtra("varA",item.getUid());
                view.getContext().startActivity(j);
                ((com.example.carinfo.UserList)Ucontext).finish();
            }
        });

    }

    @Override
    public int getItemCount() {
        return UserList.size();
    }
    public void setfilter(List<Item> listitem)
    {
        UserList=new ArrayList<>();
        UserList.addAll(listitem);
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView Uid;
        public TextView Uname;
        public ViewHolder(View itemView) {
            super(itemView);
            Uid=(TextView) itemView.findViewById(R.id.id);
            Uname=(TextView) itemView.findViewById(R.id.name);
        }
    }
}

