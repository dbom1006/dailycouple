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

public class ProfileDAO {

    public static Profile getInformation(Context context){
        SQLiteDatabase db=new DBContext(context).getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Profile", null);
       // DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        cursor.moveToFirst();
        Profile pf=null;
        while(!cursor.isAfterLast()){
            String _nameX = cursor.getString(cursor.getColumnIndex("NameX"));
            String _nameY = cursor.getString(cursor.getColumnIndex("NameY"));
            String _birthdayX = cursor.getString(cursor.getColumnIndex("BirthdayX"));
            String _birthdayY = cursor.getString(cursor.getColumnIndex("BirthdayY"));
            String _dateBegin = cursor.getString(cursor.getColumnIndex("DateBegin"));
            String _relationship = cursor.getString(cursor.getColumnIndex("Relationship"));
            String _imgX = cursor.getString(cursor.getColumnIndex("ImgX"));
            String _imgY = cursor.getString(cursor.getColumnIndex("ImgY"));

            try {
                pf = new Profile(_nameX,_nameY,_birthdayX,_birthdayY,_dateBegin,_relationship, _imgX,_imgY);

            } catch (Exception e) {
                Log.d("Error:","Lỗi nè");
            }
            cursor.moveToNext();
        }
        return pf;
    }
    public static String insert (Context context , Profile pf){
        SQLiteDatabase db=new DBContext(context).getReadableDatabase();
        String error=null;
        try{
            db.execSQL("INSERT INTO Profile (\n" +
                    "                        ImgX,\n" +
                    "                        ImgY,\n" +
                    "                        Relationship,\n" +
                    "                        DateBegin,\n" +
                    "                        BirthdayY,\n" +
                    "                        NameY,\n" +
                    "                        BirthdayX,\n" +
                    "                        NameX\n" +
                    "                    )\n" +
                    "                    VALUES (\n" +
                    "                        '"+pf.getImgX()+"',\n" +
                    "                        '"+pf.getImgY()+"',\n" +
                    "                        '"+pf.getRelationship()+"',\n" +
                    "                        '"+ pf.getDateBegin()+ "',\n" +
                    "                        '"+ pf.getBirthdayY()+ "',\n" +
                    "                        '"+ pf.getNameY()+ "',\n" +
                    "                        '"+ pf.getBirthdayX()+ "',\n" +
                    "                        '"+ pf.getNameX()+ "'\n" +
                    "                    );\n");

        }
        catch (Exception e){
            error = e +"";
        }
        return error;
    }
    public static String update (Context context , Profile pf){
        SQLiteDatabase db=new DBContext(context).getReadableDatabase();
        String error=null;
        try{
            db.execSQL("DELETE FROM Profile;");
            return insert(context,pf);

        }
        catch (Exception e){
            error = e +"";
        }
        return error;
    }

}
