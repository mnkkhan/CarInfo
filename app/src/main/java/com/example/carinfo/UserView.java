package com.example.carinfo;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UserView extends AppCompatActivity {

    UserDataBase ud=new UserDataBase(this);
    TextView t1,t2,t3,t4;
    Button b;
    String Uid="0";
    String Auid;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view);

        t1=(TextView)findViewById(R.id.editText13);
        t2=(TextView)findViewById(R.id.editText15);
        t3=(TextView)findViewById(R.id.editText16);
        t4=(TextView)findViewById(R.id.spinner2);
        b=(Button)findViewById(R.id.button6);

        Uid=getIntent().getStringExtra("varU");
        Auid=getIntent().getStringExtra("varA");

        Cursor r1=ud.DisplayByUserId(Uid);
        if (r1!=null && r1.getCount()>0) {
        while (r1.moveToNext()){
            t1.setText(r1.getString(1));
            t2.setText(r1.getString(2));
            t3.setText(r1.getString(3));
            t4.setText(r1.getString(5));
        }
        }
        else
        {
            Toast.makeText(getApplicationContext(),"This User does Not exist in the Record.",Toast.LENGTH_SHORT).show();
        }
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),UserList.class);
                intent.putExtra("varA",Auid);
                startActivity(intent);
                finish();
            }
        });
    }
    public void onBackPressed() {
        Intent intent=new Intent(getApplicationContext(),UserList.class);
        intent.putExtra("varA",Auid);
        startActivity(intent);
        finish();
    }
}