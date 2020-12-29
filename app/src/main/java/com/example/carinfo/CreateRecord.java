package com.example.carinfo;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateRecord extends AppCompatActivity {

    VehicleDataBase dv=new VehicleDataBase(this);



    EditText e1,e2,e3,e4,e5;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_record);

        e1=(EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText2);
        e3=(EditText)findViewById(R.id.editText3);
        e4=(EditText)findViewById(R.id.editText4);
        e5=(EditText)findViewById(R.id.editText5);
        b=(Button)findViewById(R.id.button2);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a1, a2, a3, a4, a5;
                a1=e1.getText().toString();
                a2=e2.getText().toString();
                a3=e3.getText().toString();
                a4=e4.getText().toString();
                a5=e5.getText().toString();

                if (a1.trim().isEmpty() ||a2.trim().isEmpty() ||a3.trim().isEmpty() ||a4.trim().isEmpty()||a5.trim().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Please Fill Properly",Toast.LENGTH_SHORT).show();
                }
                else {
                    Cursor res=dv.DisplayByNumber(a1);
                    if (res.moveToNext())
                    {
                        Toast.makeText(getApplicationContext(),"Vehicle Number is already exist",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        boolean r1=dv.insert(a1, a2, a3, a4, a5);
                        if (r1)
                        {
                            Toast.makeText(getApplicationContext(),"Inserted Successfully",Toast.LENGTH_SHORT).show();
                            e1.setText("");
                            e2.setText("");
                            e3.setText("");
                            e4.setText("");
                            e5.setText("");
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Insertion failed",Toast.LENGTH_SHORT).show();
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
