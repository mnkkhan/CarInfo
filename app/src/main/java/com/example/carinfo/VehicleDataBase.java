package com.example.carinfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class VehicleDataBase extends SQLiteOpenHelper {

    public static final String DataBase_Name="Vehicle.db";
    public static final String Table_Name="vehicle";


    public static final String col1="Id";
    public static final String col2="number";
    public static final String col3="owner_name";
    public static final String col4="reg_date";
    public static final String col5="polution";
    public static final String col6="insurance";

    public VehicleDataBase(Context c2) {
        super(c2,DataBase_Name, null, 1);
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
    public boolean insert(String nb,String nm,String rd,String pl,String in)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(col2,nb);
        cv.put(col3,nm);
        cv.put(col4,rd);
        cv.put(col5,pl);
        cv.put(col6,in);

        long res=db.insert(Table_Name,null,cv);
        if (res>0)
        {
            return true;
        }else {
            return false;
        }
    }
    public boolean Update(String id,String nb,String nm,String rd,String pl,String in)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(col3,nm);
        cv.put(col5,pl);
        cv.put(col6,in);
        long res= db.update(Table_Name,cv,col1+"=?",new String[]{id});
        if(res>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public Cursor DisplayAll() {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from "+Table_Name,null);
        return res;
    }
    public Cursor DisplayByNumber(String nb) {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from "+Table_Name+" where "+col2+"='"+nb.trim()+"'",null);
        return res;
    }
    public Cursor DisplayById(String id) {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("select * from "+Table_Name+" where "+col1+"='"+id+"'",null);
        return res;
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
}
