package com.example.carinfo;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserPassChange extends AppCompatActivity {

    UserDataBase ud=new UserDataBase(this);

    EditText e1,e2,e3;
    Button b;
    String user_id;
    String uid="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_pass_change);

        e1=(EditText)findViewById(R.id.editText9);
        e2=(EditText)findViewById(R.id.editText10);
        e3=(EditText)findViewById(R.id.editText11);
        b=(Button)findViewById(R.id.button4);

        user_id=getIntent().getStringExtra("var3");

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a1, a2, a3;
                a1 = e1.getText().toString();
                a2 = e2.getText().toString();
                a3 = e3.getText().toString();

                if (user_id.trim().isEmpty() == false) {
                    Cursor r3 = ud.DisplayByUserId(user_id);
                    if (r3 != null) {
                        while (r3.moveToNext()) {
                            uid = r3.getString(0);

                            boolean res = ud.checkuser(user_id, a1);
                            if (res) {
                                if (a2.contentEquals(a3)) {
                                    boolean r4 = ud.ChangePassword(uid, a2);
                                    if (r4) {
                                        Toast.makeText(getApplicationContext(), "Password Changes Successfully", Toast.LENGTH_SHORT).show();
                                        e1.setText("");
                                        e2.setText("");
                                        e3.setText("");
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Password Changes failed", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(), "Confirmation password does not match", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Old Password is wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }

            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent=new Intent(getApplicationContext(),UserHomepage.class);
        startActivity(intent);
        finish();
    }
}
