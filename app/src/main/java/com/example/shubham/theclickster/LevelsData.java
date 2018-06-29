package com.example.shubham.theclickster;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shubham on 26-12-2017.
 */

public class LevelsData extends SQLiteOpenHelper {


    public LevelsData(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public LevelsData(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("Create Table GameData (LevelNo integer  PRIMARY KEY AUTOINCREMENT ,Highscore integer)");
    }

    public boolean insertHS(int hs){
        ContentValues contentValues=new ContentValues();
        contentValues.put("Highscore",hs);
        SQLiteDatabase sqLiteDatabase= getWritableDatabase();
        long rowId=sqLiteDatabase.insert("GameData",null,contentValues);
        sqLiteDatabase.close();
        if(rowId>0)
            return true;
        else
            return false;
    }

    public int checkHS(int level)
    {
        int flag;
        String qury="SELECT Highscore FROM GameData WHERE LevelNo = "+level;
        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor=db.rawQuery(qury,null);

         if (cursor != null)
        cursor.moveToFirst();
        String s=cursor.getString(0);
         flag=Integer.parseInt(s);
        cursor.close();
        db.close();
        if (s.equals(null)) {return (-1);} else {return  flag;}

    }

    public boolean updateHS(int level,int hs){
        ContentValues contentValues=new ContentValues();
        contentValues.put("Highscore",hs);
        SQLiteDatabase sqLiteDatabase= getWritableDatabase();
        long rowId=sqLiteDatabase.update("GameData",contentValues,"LevelNo = "+level,null);
        sqLiteDatabase.close();
        if(rowId>0)
            return true;
        else
            return false;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
