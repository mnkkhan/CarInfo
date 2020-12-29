package com.example.carinfo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    AdminDataBase da;

    {
        da = new AdminDataBase(this);
    }

    TextView t2,t3;
    Button b,t4,t5,t6,t7,t8;
    String id;
    String a1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t2=(TextView)findViewById(R.id.textView2);
        t3=(TextView)findViewById(R.id.textView3);
        t4=(Button) findViewById(R.id.textView4);
        t5=(Button) findViewById(R.id.textView5);
        t6=(Button) findViewById(R.id.textView6);
        t7=(Button) findViewById(R.id.textView7);
        t8=(Button) findViewById(R.id.textView8);
        b=(Button)findViewById(R.id.button);


        SharedPreferences sp=getSharedPreferences("myPref", Context.MODE_PRIVATE);
        a1=sp.getString("myuid","default");

        Cursor cursor=da.DisplayByUserid(a1);
        while (cursor.moveToNext()) {
            t2.setText(cursor.getString(1));
        }
    }
    public void logout(View v)
    {
        final SharedPreferences sharedPreferences = getSharedPreferences("myPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.clear();
        editor.commit();

        final String a="Admin";
        Toast.makeText(getApplicationContext(),"Successfully Logout",Toast.LENGTH_SHORT).show();
        Intent i=new Intent(getApplicationContext(),Login.class);
        i.putExtra("var",a);
        startActivity(i);
        finish();
    }
    public void newAdmin(View v)
    {
        Toast.makeText(getApplicationContext(),"You are going to create New Admin.",Toast.LENGTH_SHORT).show();
        Intent i=new Intent(getApplicationContext(),CreateAdmin.class);
        i.putExtra("varA",a1);
        startActivity(i);
        finish();
    }
    public void changePassword(View v)
    {
        Toast.makeText(getApplicationContext(),"You are going to Change your Password.",Toast.LENGTH_SHORT).show();
        Intent i=new Intent(getApplicationContext(),ChangePassword.class);
        i.putExtra("var4",a1);
        startActivity(i);
        finish();
    }
    public void adminList(View v)
    {
        Intent i=new Intent(getApplicationContext(),AdminList.class);
        i.putExtra("varA",a1);
        startActivity(i);
        finish();
    }
    public void newVehicle(View v)
    {
        Toast.makeText(getApplicationContext(),"You are going to create New Vehicle Record.",Toast.LENGTH_SHORT).show();
        Intent i=new Intent(this,CreateRecord.class);
        startActivity(i);
        finish();

    }
    public void vehicleList(View v)
    {
        Intent i=new Intent(getApplicationContext(),VehicleList.class);
        i.putExtra("varA",a1);
        startActivity(i);
        finish();
    }
    public void userList(View v)
    {
        Intent i=new Intent(getApplicationContext(),UserList.class);
        i.putExtra("varA",a1);
        startActivity(i);
        finish();

    }
    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
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
