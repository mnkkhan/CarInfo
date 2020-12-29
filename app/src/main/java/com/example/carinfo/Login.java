package com.example.carinfo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    AdminDataBase da=new AdminDataBase(this);
    UserDataBase ud=new UserDataBase(this);

    TextView tv;
    EditText e1,e2;
    Button b;
    String a;

    public static final String myPreference = "mypref";
    public static final String myuid = "nmkey";
    public static final String mypass = "passkey";
    public static final String myuidType = "utkey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        e1=(EditText)findViewById(R.id.editText12);
        e2=(EditText)findViewById(R.id.editText14);
        b=(Button)findViewById(R.id.button5);
        tv=(TextView)findViewById(R.id.textView27);

        SharedPreferences sp3=getSharedPreferences("mypref2", Context.MODE_PRIVATE);
        a=sp3.getString("myuidType2","default");
        //a=getIntent().getStringExtra("var");

        SharedPreferences sharedPreferences = getSharedPreferences("myPref", Context.MODE_PRIVATE);
        String a11=sharedPreferences.getString("myuid","");
        String a12=sharedPreferences.getString("myuidType","");

        if (a12.contentEquals(a)) {
            if (!a11.trim().isEmpty()) {

                String a7, a8;
                a7 = "User";
                a8 = "Admin";
                if (a12.contentEquals(a7)) {
                    Intent i = new Intent(Login.this, UserHomepage.class);
                    startActivity(i);
                    finish();
                } else if (a12.contentEquals(a8)) {
                    Intent i = new Intent(Login.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        }else {

            final SharedPreferences sharedPreference = getSharedPreferences("myPref", Context.MODE_PRIVATE);

            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String a1, a2, a5, a6;
                    a1 = e1.getText().toString();
                    a2 = e2.getText().toString();
                    a5 = "User";
                    a6 = "Admin";
                    if (a.contentEquals(a5)) {
                        boolean r = ud.checkuser(a1, a2);
                        if (r) {
                            SharedPreferences.Editor editor = sharedPreference.edit();
                            editor.putString("myuid", a1);
                            editor.putString("mypass", a2);
                            editor.putString("myuidType", a);
                            editor.commit();

                            Intent i = new Intent(getApplicationContext(), UserHomepage.class);
                            startActivity(i);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Invaild Credentials", Toast.LENGTH_SHORT).show();
                        }

                    } else if (a.contentEquals(a6)) {
                        Cursor re = da.DisplayAll();
                            if (re != null && re.getCount()>0){
                                boolean r = da.checkuser(a1, a2);
                                if (r) {
                                    SharedPreferences.Editor editor = sharedPreference.edit();
                                    editor.putString("myuid", a1);
                                    editor.putString("mypass", a2);
                                    editor.putString("myuidType", a);
                                    editor.commit();

                                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(i);
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Invaild Credentials", Toast.LENGTH_SHORT).show();
                                }
                        } else {
                                if (a1.contentEquals("Admin") && a2.contentEquals("Admin")) {
                                    da.insert("Admin","Admin","Admin","Admin");
                                    SharedPreferences.Editor editor = sharedPreference.edit();
                                    editor.putString("myuid", a1);
                                    editor.putString("mypass", a2);
                                    editor.putString("myuidType", a);
                                    editor.commit();

                                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(i);
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Invaild Credentials", Toast.LENGTH_SHORT).show();
                                }
                        }
                    }
                }
            });
        }
    }
    public void registration(View v){
        if (a.contentEquals("User")) {
            Intent j = new Intent(getApplicationContext(), UserReg.class);
            startActivity(j);
            finish();
        }else {
            Toast.makeText(getApplicationContext(), "It's Only for User.", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onBackPressed() {
                final SharedPreferences sharedPref2 = getSharedPreferences("mypref2", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor2 = sharedPref2.edit();

                editor2.clear();
                editor2.commit();
                Intent intent=new Intent(getApplicationContext(),WhoRu.class);
                startActivity(intent);
                finish();
    }
}
