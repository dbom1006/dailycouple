package com.cntt.dbom.loveapp.DAL;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.cntt.dbom.loveapp.Entity.Profile;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Bac Nice on 12/14/2016.
 */

public class DAO extends SQLiteOpenHelper {

    public static final String DB_name = "LoveApp.DB";
    public static final int DB_version = 1;
    public DAO(Context context) {
        super(context, DB_name, null, DB_version);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Profile (\n" +
                "    NameX        VARCHAR (30) NOT NULL,\n" +
                "    BirthdayX    DATE,\n" +
                "    NameY        VARCHAR (30),\n" +
                "    BirthdayY    DATE,\n" +
                "    DateBegin    DATE,\n" +
                "    Relationship VARCHAR\n" +
                ");");
        Log.d("Database:"," created");
        db.execSQL("INSERT INTO Profile (\n" +
                "                        NameX,NameY\n" +
                "                    )\n" +
                "                    VALUES (\n" +
                "                        'Đức', 'Si'\n" +
                "                    );");
    }

    public Profile getInfomation(DAO dao){
        SQLiteDatabase sd = dao.getReadableDatabase();
        Cursor cursor = sd.rawQuery("select * from Profile", null);
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        cursor.moveToFirst();
        while(!cursor.isFirst()){
            String _nameX = cursor.getString(cursor.getColumnIndex("NameX"));
            String _nameY = cursor.getString(cursor.getColumnIndex("NameY"));
            String _birthdayX = cursor.getString(cursor.getColumnIndex("BirthdayX"));
            String _birthdayY = cursor.getString(cursor.getColumnIndex("BirthdayY"));
            String _dateBegin = cursor.getString(cursor.getColumnIndex("DateBegin"));
            String _relationship = cursor.getString(cursor.getColumnIndex("Relationship"));
            try {
                Date tDateX= sdf.parse(_birthdayX), tDateY = sdf.parse(_birthdayY), tDateBegin = sdf.parse(_dateBegin);
                Profile pf = new Profile(_nameX,_nameY,tDateX,tDateY,tDateBegin,_relationship);
                return pf;
            } catch (ParseException e) {
                e.printStackTrace();
            }
            cursor.moveToNext();
        }
        return null;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
