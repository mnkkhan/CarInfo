package com.example.carinfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminDataBase extends SQLiteOpenHelper {

    public static final String DataBase_Name="Admin.db";
    public static final String Table_Name="admin";

    public static final String col1="Id";
    public static final String col2="admin_name";
    public static final String col3="admin_userid";
    public static final String col4="admin_pass";
    public static final String col5="admin_creator";

    public AdminDataBase(Context c1) {
        super(c1, DataBase_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String q = "create table "+Table_Name+"("+col1+" integer primary key autoincrement,"+col2+" Text,"+col3+" Text,"+col4+" Text,"+col5+" Text)";
        sqLiteDatabase.execSQL(q);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String q1="drop table if exists "+Table_Name;
        sqLiteDatabase.execSQL(q1);

    }
    public boolean insert(String nm,String ud,String pwd,String Ac)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(col2,nm);
        cv.put(col3,ud);
        cv.put(col4,pwd);
        cv.put(col5,Ac);

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
        Cursor res=db.rawQuery("Select * from " +Table_Name,null);
        return res;
    }
    public Cursor DisplayByUserid(String ud) {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("Select * from " + Table_Name+" where "+col3+"='"+ud.trim()+"'",null);
        return res;
    }

    public Cursor DisplayById(String id) {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("Select * from " + Table_Name+" where "+col1+"='"+id.trim()+"'",null);
        return res;
    }

    public boolean ChangePassword(String id, String pass) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(col4,pass);
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
    public boolean Delete(String id) {
        SQLiteDatabase db=this.getWritableDatabase();
        long res=db.delete(Table_Name,col1+"=?",new String[]{id});
        if (res>0)
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
        String selection=col3+"=?"+" And "+col4+"=?";
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
