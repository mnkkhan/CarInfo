package com.example.carinfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDataBase extends SQLiteOpenHelper {

    public static final String DataBase_Name="User.db";
    public static final String Table_Name="user";

    public static final String col1="Id";
    public static final String col2="user_name";
    public static final String col3="userid";
    public static final String col4="user_phone";
    public static final String col5="user_pass";
    public static final String col6="user_gender";

    public UserDataBase(Context c3) {
        super(c3, DataBase_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String q = "create table "+Table_Name+"("+col1+" integer primary key autoincrement,"+col2+" Text,"+col3+" Text,"+col4+" Text,"+col5+" Text,"+col6+" Text)";
        sqLiteDatabase.execSQL(q);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String q1="drop table if exists "+Table_Name;
        sqLiteDatabase.execSQL(q1);

    }

    public boolean insert(String nm, String uid, String ph,String pass, String gn) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(col2,nm);
        cv.put(col3,uid);
        cv.put(col4,ph);
        cv.put(col5,pass);
        cv.put(col6,gn);

        long res=db.insert(Table_Name,null,cv);
        if (res>0)
        {
            return true;
        }else {
            return false;
        }
    }

    public Cursor DisplayAll() {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from "+Table_Name,null);
        return res;
    }
    public Cursor DisplayById(String id) {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("Select * from " + Table_Name+" where "+col1+"='"+id.trim()+"'",null);
        return res;
    }
    public Cursor DisplayByUserId(String uid) {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from "+Table_Name+" where "+col3+"='"+uid.trim()+"'",null);
        return res;
    }
    public boolean ChangePassword(String id,String pass) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(col5,pass);
        long res= db.update(Table_Name,cv,col1+"=?", new String[]{id});
        if(res>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean checkuser(String user_id, String pass)
    {
        String col[]={col1};
        SQLiteDatabase db=this.getReadableDatabase();
        String selection=col3+"=?"+" And "+col5+"=?";
        String[] selctionArgs={user_id,pass};
        Cursor res=db.query(Table_Name,col,selection,selctionArgs,null,null,null);
        int resCount=res.getCount();
        if (resCount>0)
        {
            return true;
        }
        else
        {
            return false;
        }

    }
}
