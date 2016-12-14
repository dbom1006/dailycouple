package com.cntt.dbom.loveapp.DAL;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.cntt.dbom.loveapp.Entity.Profile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Bac Nice on 12/14/2016.
 */

public class ProfileDAO{

    public static Profile getInformation(Context context){
        SQLiteDatabase db=new DBContext(context).getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Profile", null);
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        cursor.moveToFirst();
        Profile pf=null;
        while(!cursor.isAfterLast()){
            String _nameX = cursor.getString(cursor.getColumnIndex("NameX"));
            String _nameY = cursor.getString(cursor.getColumnIndex("NameY"));
            String _birthdayX = cursor.getString(cursor.getColumnIndex("BirthdayX"));
            String _birthdayY = cursor.getString(cursor.getColumnIndex("BirthdayY"));
            String _dateBegin = cursor.getString(cursor.getColumnIndex("DateBegin"));
            String _relationship = cursor.getString(cursor.getColumnIndex("Relationship"));
            try {
                Date tDateX= sdf.parse(_birthdayX), tDateY = sdf.parse(_birthdayY), tDateBegin = sdf.parse(_dateBegin);
                pf = new Profile(_nameX,_nameY,tDateX,tDateY,tDateBegin,_relationship);
            } catch (Exception e) {
                Log.d("Lỗi:","Lỗi nè");
            }
            cursor.moveToNext();
        }
        return pf;
    }
}
