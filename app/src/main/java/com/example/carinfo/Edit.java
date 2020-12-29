package com.example.carinfo;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Edit extends AppCompatActivity {

    VehicleDataBase dv=new VehicleDataBase(this);

    EditText e2,e4,e5;
    TextView t1,t3;
    Button b1,b2;
    String vNumber="0";
    String Auid;
    String id="0";
    CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        t1=(TextView) findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText2);
        t3=(TextView) findViewById(R.id.editText3);
        e4=(EditText)findViewById(R.id.editText4);
        e5=(EditText)findViewById(R.id.editText5);
        b1=(Button)findViewById(R.id.button2);
        b2=(Button)findViewById(R.id.button3);
        checkBox=(CheckBox) findViewById(R.id.checkBox);

        vNumber=getIntent().getStringExtra("var2");
        Auid=getIntent().getStringExtra("varA");

        Cursor res=dv.DisplayByNumber(vNumber);
        if(res.moveToNext())
        {
            id=res.getString(0);
            t1.setText(res.getString(1));
            e2.setText(res.getString(2));
            t3.setText(res.getString(3));
            e4.setText(res.getString(4));
            e5.setText(res.getString(5));
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
                if (checkBox.isChecked() == true) {
                    String a1, a2, a3, a4, a5, a6;
                    a1 = id;
                    a2 = t1.getText().toString();
                    a3 = e2.getText().toString();
                    a4 = t3.getText().toString();
                    a5 = e4.getText().toString();
                    a6 = e5.getText().toString();

                    if (a3.trim().isEmpty() || a5.trim().isEmpty() || a6.trim().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Please Fill Properly", Toast.LENGTH_SHORT).show();
                    } else {
                        Cursor res = dv.DisplayById(a1);

                        if (res != null) {
                            while (res.moveToNext()) {
                                boolean r1 = dv.Update(a1, a2, a3, a4, a5, a6);
                                if (r1) {
                                    Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(getApplicationContext(),VehicleList.class);
                                    intent.putExtra("varA",Auid);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Updation failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Something error...", Toast.LENGTH_SHORT).show();

                        }
                    }

                }else {
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
