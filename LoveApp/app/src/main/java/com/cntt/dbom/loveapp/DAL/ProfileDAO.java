package com.cntt.dbom.loveapp.DAL;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cntt.dbom.loveapp.Entity.Profile;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Bac Nice on 12/14/2016.
 */

public class ProfileDAO  extends DAO{
    public ProfileDAO(Context context) {
        super(context);
    }

    public Profile getInfomation(){
        SQLiteDatabase sd = getReadableDatabase();
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
}
