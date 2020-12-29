package com.example.carinfo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WhoRu extends AppCompatActivity {


    Button b1,b2;
    String a12;

    public static final String myPreference2 = "mypref2";
    public static final String myuidType2 = "utkey2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_who_ru);
        b1 = (Button) findViewById(R.id.admin);
        b2 = (Button) findViewById(R.id.user);


        SharedPreferences sharedPreferences = getSharedPreferences("mypref2", Context.MODE_PRIVATE);
        String a12=sharedPreferences.getString("myuidType2","");

        if (!a12.trim().isEmpty()) {
            if (a12.contentEquals("Admin")) {
                Intent i = new Intent(getApplicationContext(), Login.class);
                //i.putExtra("var", a12);
                startActivity(i);
                finish();
            } else if (a12.contentEquals("User")) {
                Intent j = new Intent(getApplicationContext(), Login.class);
                //j.putExtra("var", a12);
                startActivity(j);
                finish();
            }

        }else {
            final SharedPreferences shpref = getSharedPreferences("mypref2", Context.MODE_PRIVATE);
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String a1 = "Admin";
                    SharedPreferences.Editor editor2 = shpref.edit();
                    editor2.putString("myuidType2", a1);
                    editor2.commit();

                    Intent i = new Intent(getApplicationContext(), Login.class);
                    //i.putExtra("var", a1);
                    startActivity(i);
                    finish();
                }
            });

            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String a2 = "User";
                    SharedPreferences.Editor editor2 = shpref.edit();
                    editor2.putString("myuidType2", a2);
                    editor2.commit();
                    Intent j = new Intent(getApplicationContext(), Login.class);
                    //j.putExtra("var", a2);
                    startActivity(j);
                    finish();
                }
            });
        }
    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder= new AlertDialog.Builder(WhoRu.this);
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