package com.example.carinfo;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AdminView extends AppCompatActivity {

    AdminDataBase da=new AdminDataBase(this);

    TextView tv1,tv2,tv3;
    Button b1;
    String Auid;
    String Auser_id="0";
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view);

        tv1=(TextView)findViewById(R.id.editText6);
        tv2=(TextView)findViewById(R.id.editText7);
        tv3=(TextView)findViewById(R.id.editText8);
        b1=(Button)findViewById(R.id.button3);

        Auser_id=getIntent().getStringExtra("var5");
        Auid=getIntent().getStringExtra("varA");

        Cursor r1=da.DisplayByUserid(Auser_id);
        if (r1!=null && r1.getCount()>0) {
            while (r1.moveToNext()) {
                tv1.setText(r1.getString(1));
                tv2.setText(r1.getString(2));
                tv3.setText(r1.getString(4));
            }
        }
        else
            {
                Toast.makeText(getApplicationContext(),"This Admin does Not exist.",Toast.LENGTH_SHORT).show();
            }
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminView.this,AdminList.class);
                intent.putExtra("varA",Auid);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent=new Intent(AdminView.this,AdminList.class);
        intent.putExtra("varA",Auid);
        startActivity(intent);
        finish();
    }
}
