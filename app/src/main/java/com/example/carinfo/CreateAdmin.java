package com.example.carinfo;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreateAdmin extends AppCompatActivity {

    AdminDataBase da=new AdminDataBase(this);

    TextView t;
    EditText e1,e2,e3;
    Button b;
    String Auid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_admin);

        e1 = (EditText) findViewById(R.id.editText6);
        e2 = (EditText) findViewById(R.id.editText7);
        e3 = (EditText) findViewById(R.id.editText8);
        t = (TextView) findViewById(R.id.auid);
        b = (Button) findViewById(R.id.button3);

        Auid = getIntent().getStringExtra("varA");
        t.setText(Auid);


            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String a1, a2, a3;
                    a1 = e1.getText().toString();
                    a2 = e2.getText().toString();
                    a3 = e3.getText().toString();
                    if (a1.trim().isEmpty() || a2.trim().isEmpty() || a3.trim().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Please Fill Properly", Toast.LENGTH_SHORT).show();
                    } else {
                        Cursor res = da.DisplayByUserid(a2);
                        if (res.moveToNext()) {
                            Toast.makeText(getApplicationContext(), "Admin User id is already exist", Toast.LENGTH_SHORT).show();
                        } else {
                            boolean r2 = da.insert(a1, a2, a3, Auid);
                            if (r2) {
                                Toast.makeText(getApplicationContext(), "Inserted Successfully", Toast.LENGTH_SHORT).show();
                                e1.setText("");
                                e2.setText("");
                                e3.setText("");
                            } else {
                                Toast.makeText(getApplicationContext(), "Insertion failed", Toast.LENGTH_SHORT).show();
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
