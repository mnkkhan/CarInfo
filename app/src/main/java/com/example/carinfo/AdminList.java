package com.example.carinfo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AdminList extends AppCompatActivity {

    AdminDataBase da=new AdminDataBase(this);
    SearchView searchView;
    RecyclerView itemList;
    List<Item> listitem= new ArrayList<>();

    AdminAdapter adapter;
    String id;
    String Auid="0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_list);

        itemList = (RecyclerView) findViewById(R.id.itemList);
        itemList.setHasFixedSize(true);

        Auid=getIntent().getStringExtra("varA");

        Cursor res=da.DisplayAll();
        if (res!=null && res.getCount()>0)
        {
            int i=1;
            while (res.moveToNext())
            {
                listitem.add(new Item(res.getString(2),res.getString(1),Auid));
                i++;
            }
        }else
        {
            Toast.makeText(getApplicationContext(),"No Admin Record Found",Toast.LENGTH_SHORT).show();
        }
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        itemList.setLayoutManager(linearLayoutManager);
        adapter=new AdminAdapter(listitem,AdminList.this);
        itemList.setAdapter(adapter);
    }
    @Override
    public void onBackPressed() {
        Intent intent=new Intent(AdminList.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        final MenuItem myActionMenuItem = menu.findItem(R.id.search);
        searchView=(SearchView)myActionMenuItem.getActionView();

        ((EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text)).setHintTextColor(getResources().getColor(R.color.white));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (!searchView.isIconified()){
                    searchView.setIconified(true);
                }
                myActionMenuItem.collapseActionView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                final List<Item> filtermodelist=filter(listitem,s);
                adapter.setfilter(filtermodelist);
                return false;
            }
        });


        return true;
    }
    private List<Item> filter(List<Item> p1, String query)
    {
        query=query.toLowerCase();
        final List<Item> filteredModeList=new ArrayList<>();
        for(Item model:p1)
        {
            final String text=model.getNumber().toLowerCase();
            if (text.startsWith(query))
            {
                filteredModeList.add(model);
            }
        }
        return filteredModeList;
    }
    private void changeSearchViewTextColor(View view){
        if (view!=null)
        {
            if (view instanceof TextView){
                ((TextView) view).setTextColor(Color.WHITE);
                return;
            }else if (view instanceof ViewGroup){
                ViewGroup viewGroup=(ViewGroup) view;
                for (int i=0;i<viewGroup.getChildCount();i++){
                    changeSearchViewTextColor(viewGroup.getChildAt(i));
                }
            }
        }
    }
}