package com.example.carinfo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class UserReg extends AppCompatActivity {

    UserDataBase ud=new UserDataBase(this);

    EditText e1,e2,e3,e4,e5;
    TextView tv;
    Button b;
    Spinner sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_reg);

        e1=(EditText)findViewById(R.id.editText13);
        e2=(EditText)findViewById(R.id.editText15);
        e3=(EditText)findViewById(R.id.editText16);
        e4=(EditText)findViewById(R.id.editText17);
        e5=(EditText)findViewById(R.id.editText18);
        tv=(TextView)findViewById(R.id.textView27);
        b=(Button)findViewById(R.id.button6);
        sp=(Spinner)findViewById(R.id.spinner2);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a1,a2,a3,a4,a5,a6;
                a1=e1.getText().toString();
                a2=e2.getText().toString();
                a3=e3.getText().toString();
                a4=e4.getText().toString();
                a5=e5.getText().toString();
                a6=sp.getSelectedItem().toString();

                if (a1.trim().isEmpty() || a2.trim().isEmpty() || a3.trim().isEmpty() || a4.trim().isEmpty() || a5.trim().isEmpty() )
                {
                    Toast.makeText(getApplicationContext(),"Please Fill Properly",Toast.LENGTH_SHORT).show();
                }
                else {
                    Cursor res=ud.DisplayByUserId(a2);
                    if (res.moveToNext())
                    {
                        Toast.makeText(getApplicationContext(),"User id is already exist",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        if (a4.contentEquals(a5))
                        {
                            boolean r1 = ud.insert(a1, a2, a3, a5, a6);
                            if (r1) {
                                Toast.makeText(getApplicationContext(), "Registred Successfully", Toast.LENGTH_SHORT).show();
                                e1.setText("");
                                e2.setText("");
                                e3.setText("");
                                e4.setText("");
                                e5.setText("");
                            } else {
                                Toast.makeText(getApplicationContext(), "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Confirmation Password does not matched. ",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                final String a="User";
                Intent i=new Intent(getApplicationContext(),Login.class);
                i.putExtra("var",a);
                startActivity(i);
                finish();
            }
        });
    }
    public void login(View v)
    {
        final String a="User";
        Intent i=new Intent(getApplicationContext(),Login.class);
        i.putExtra("var",a);
        startActivity(i);
        finish();
    }
    @Override
    public void onBackPressed() {

        final String a="User";
        Intent i=new Intent(getApplicationContext(),Login.class);
        i.putExtra("var",a);
        startActivity(i);
        finish();
    }
}
