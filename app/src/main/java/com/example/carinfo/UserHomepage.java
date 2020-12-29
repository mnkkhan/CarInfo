package com.example.carinfo;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UserHomepage extends AppCompatActivity {

    VehicleDataBase dv=new VehicleDataBase(this);
    UserDataBase ud=new UserDataBase(this);


    SearchView searchView;
    String a1;
    RecyclerView itemList;
    List<Item> listitem= new ArrayList<>();


    ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_homepage);


        TextView tv=(TextView)findViewById(R.id.textView29);
        Button b = (Button) findViewById(R.id.button7);
        itemList = (RecyclerView) findViewById(R.id.itemList);
        itemList.setHasFixedSize(true);


        SharedPreferences sp=getSharedPreferences("myPref", Context.MODE_PRIVATE);
        a1=sp.getString("myuid","default");

        Cursor cursor=ud.DisplayByUserId(a1);
        while (cursor.moveToNext()){
            tv.setText(cursor.getString(1));
        }

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final SharedPreferences sharedPreferences = getSharedPreferences("myPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.clear();
                editor.commit();

                final String a="User";
                Intent i = new Intent(getApplicationContext(), Login.class);
                i.putExtra("var",a);
                startActivity(i);
                finish();
            }
        });

        Cursor res = dv.DisplayAll();
        if (res != null && res.getCount()>0) {
            int i = 1;
            while (res.moveToNext()) {
                listitem.add(new Item(res.getString(1),res.getString(2),a1));
                i++;
            }
        } else {
            Toast.makeText(getApplicationContext(), "No Vehicle Record Found", Toast.LENGTH_SHORT).show();
        }
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        itemList.setLayoutManager(linearLayoutManager);
        adapter=new ItemAdapter(listitem,UserHomepage.this);
        itemList.setAdapter(adapter);
    }

    public void changePassword(View v)
    {
        Toast.makeText(getApplicationContext(),"You want to Change your Password",Toast.LENGTH_SHORT).show();
        Intent i=new Intent(getApplicationContext(),UserPassChange.class);
        i.putExtra("var3",a1);
        startActivity(i);
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
    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder= new AlertDialog.Builder(UserHomepage.this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("Do you want to exit from this application?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
}
