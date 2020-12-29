package com.example.carinfo;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ViewVehicle extends AppCompatActivity {

    VehicleDataBase dv=new VehicleDataBase(this);
    TextView e1,e2,e3,e4,e5;
    Button b;
    String vNumber="0";
    String Auid="0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_vehicle);

        e1=(TextView) findViewById(R.id.editText);
        e2=(TextView)findViewById(R.id.editText2);
        e3=(TextView)findViewById(R.id.editText3);
        e4=(TextView)findViewById(R.id.editText4);
        e5=(TextView)findViewById(R.id.editText5);
        b=(Button)findViewById(R.id.button2);


        vNumber=getIntent().getStringExtra("var6");
        Auid=getIntent().getStringExtra("varA");

            Cursor res=dv.DisplayByNumber(vNumber);
            if (res!=null && res.getCount()>0)
            {
                while (res.moveToNext())
                {
                    e1.setText(res.getString(1));
                    e2.setText(res.getString(2));
                    e3.setText(res.getString(3));
                    e4.setText(res.getString(4));
                    e5.setText(res.getString(5));
                }
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Record Not Found",Toast.LENGTH_SHORT).show();
            }
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(getApplicationContext(),VehicleList.class);
                    i.putExtra("varA",Auid);
                    startActivity(i);
                    finish();
                }
            });
    }
    public void onBackPressed() {
        Intent intent=new Intent(getApplicationContext(),VehicleList.class);
        intent.putExtra("varA",Auid);
        startActivity(intent);
        finish();
    }
}
