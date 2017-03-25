package com.example.anureetkaur.cmccarpool;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Random;

/**
 * Created by anureet kaur on 3/11/2017.
 */
public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String Database_Name="Users.db";
    public static final String Table_Name="Users";
    public static final String Col1="_Phone";
    public static final String Col2="Name";
    public static final String Col3="College";
    public static final String Col4="Password";
    final String createTable=" create table "+ Table_Name +"(_Phone text primary key, Name text, College text, Password text)";
    static SQLiteDatabase db;
    public DatabaseHelper(Context context)
    {
        super(context,Database_Name,null,1);
    }
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(createTable);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXIST "+Table_Name);
        onCreate(db);
    }
    public boolean insertUser(String phn,String name,String college)
    {
        db=this.getWritableDatabase();
        Random r = new Random();
        int i1 = r.nextInt(100)+1000;
        String password=Integer.toString(i1);
        ContentValues cv=new ContentValues();
        cv.put(Col1,phn);
        cv.put(Col2,name);
        cv.put(Col3,college);
        cv.put(Col4,password);
        long result=db.insert(Table_Name,null,cv);
        if(result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public Cursor getData(String phn)
    {
        db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+Table_Name+" where _Phone=?",new String[]{phn});
        return res;
    }
}
