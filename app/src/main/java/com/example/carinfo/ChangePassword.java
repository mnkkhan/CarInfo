package com.example.carinfo;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePassword extends AppCompatActivity {

    AdminDataBase da=new AdminDataBase(this);


    EditText e1,e2,e3;
    Button b;
    String Auser_id;
    String aid="0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        e1=(EditText)findViewById(R.id.editText9);
        e2=(EditText)findViewById(R.id.editText10);
        e3=(EditText)findViewById(R.id.editText11);
        b=(Button)findViewById(R.id.button4);

        Auser_id=getIntent().getStringExtra("var4");


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a1, a2, a3;
                a1 = e1.getText().toString();
                a2 = e2.getText().toString();
                a3 = e3.getText().toString();

                if (Auser_id.trim().isEmpty() == false) {
                    Cursor r2 = da.DisplayByUserid(Auser_id);
                    if (r2 != null) {
                        while (r2.moveToNext()) {
                            aid = r2.getString(0);

                            boolean res = da.checkuser(Auser_id, a1);
                            if (res) {
                                if (a2.contentEquals(a3)) {
                                    boolean r1 = da.ChangePassword(aid, a2);
                                    if (r1) {
                                        Toast.makeText(getApplicationContext(), "Password Changes Successfully", Toast.LENGTH_SHORT).show();
                                        e1.setText("");
                                        e2.setText("");
                                        e3.setText("");
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Password Changes failed", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(), "Confirmation password does not match", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Old Password is wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }

            }
        });
        }
    @Override
    public void onBackPressed() {
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }
    }
