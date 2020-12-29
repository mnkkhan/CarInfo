package com.example.carinfo;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class AdminDelete extends AppCompatActivity {

    AdminDataBase da=new AdminDataBase(this);
    TextView tv1,tv2,tv3;
    Button b1, b2;
    CheckBox checkBox;
    String Auid="0";
    String Auser_id="0";
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_delete);

        tv1=(TextView)findViewById(R.id.editText6);
        tv2=(TextView)findViewById(R.id.editText7);
        tv3=(TextView)findViewById(R.id.editText8);
        b1=(Button)findViewById(R.id.button2);
        b2=(Button)findViewById(R.id.button3);
        checkBox=(CheckBox)findViewById(R.id.checkBox);

        Auid=getIntent().getStringExtra("varA");
        Auser_id=getIntent().getStringExtra("varD");

        Cursor r1=da.DisplayByUserid(Auser_id);
        while (r1.moveToNext()){
            id=r1.getString(0);
            tv1.setText(r1.getString(1));
            tv2.setText(r1.getString(2));
            tv3.setText(r1.getString(4));
        }
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),AdminList.class);
                intent.putExtra("varA",Auid);
                startActivity(intent);
                finish();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked() == true) {
                    if (!Auid.contentEquals(Auser_id)) {
                        boolean r = da.Delete(id);
                        if (r) {
                            Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), AdminList.class);
                            intent.putExtra("varA",Auid);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Deletion failed", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "You can not Delete yourself", Toast.LENGTH_SHORT).show();
                    }
                } else {
                        Toast.makeText(getApplicationContext(), "Plese make sure...", Toast.LENGTH_LONG).show();
                    }
                }
        });
    }
    @Override
    public void onBackPressed() {

        Intent intent=new Intent(getApplicationContext(),AdminList.class);
        intent.putExtra("varA",Auid);
        startActivity(intent);
        finish();
    }
}
