package com.example.carinfo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class Delete_item extends AppCompatActivity {

    VehicleDataBase dv=new VehicleDataBase(this);
    TextView e1,e2,e3,e4,e5;
    Button b1,b2;
    CheckBox checkBox;
    String vNumber="0";
    String Auid="0";
    String id="0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_item);
        e1=(TextView) findViewById(R.id.editText);
        e2=(TextView)findViewById(R.id.editText2);
        e3=(TextView)findViewById(R.id.editText3);
        e4=(TextView)findViewById(R.id.editText4);
        e5=(TextView)findViewById(R.id.editText5);
        b1=(Button)findViewById(R.id.button2);
        b2=(Button)findViewById(R.id.button3);
        checkBox=(CheckBox) findViewById(R.id.checkBox);

        vNumber=getIntent().getStringExtra("varD");
        Auid=getIntent().getStringExtra("varA");

        Cursor r1=dv.DisplayByNumber(vNumber);
        while (r1.moveToNext()){
            id=r1.getString(0);
            e1.setText(r1.getString(1));
            e2.setText(r1.getString(2));
            e3.setText(r1.getString(3));
            e4.setText(r1.getString(4));
            e5.setText(r1.getString(5));
        }
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),VehicleList.class);
                intent.putExtra("varA",Auid);
                startActivity(intent);
                finish();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked()==true){
                    boolean r=dv.Delete(id);
                    if (r)
                    {
                        Toast.makeText(getApplicationContext(),"Deleted Successfully",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),VehicleList.class);
                        intent.putExtra("varA",Auid);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Deletion failed",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),"Plese make sure...",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent=new Intent(getApplicationContext(),VehicleList.class);
        intent.putExtra("varA",Auid);
        startActivity(intent);
        finish();
    }
}
