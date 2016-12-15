package com.cntt.dbom.loveapp.DAL;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cntt.dbom.loveapp.Entity.Activity;
import com.cntt.dbom.loveapp.Entity.Activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Dbom on 12/15/2016.
 */

public class AcitivityDAO {
    public static void Delete(Activity ac, Context context){
        SQLiteDatabase db=new DBContext(context).getReadableDatabase();
        String sql="DELETE FROM Activity\n" +
                "      WHERE Date = '"+ac.getDate()+"' AND \n" +
                "            Status = '"+ac.getStatus()+"' AND \n" +
                "            Time = '"+ac.getTime()+"';";
        db.execSQL(sql);
    }
    public static void Insert(Activity ac,Context context){
        SQLiteDatabase db=new DBContext(context).getReadableDatabase();
        db.execSQL("INSERT INTO Activity (\n" +
                "                         Status,\n" +
                "                         Date,\n" +
                "                         Time,\n" +
                "                         Icon\n" +
                "                     )\n" +
                "                     VALUES (\n" +
                "                         '"+ac.getStatus()+"',\n" +
                "                         '"+ac.getDate()+"',\n" +
                "                         '"+ac.getTime()+"',\n" +
                "                         '"+ac.getIcon()+"'\n" +
                "                     );\n");
    }
    public static List<Activity> getList(Context context){
        List<Activity> lst=getDataToList(context);
        Collections.sort(lst);
        return lst;
    }
    public static List<Activity> getListToday(Context context){
        List<Activity> lst=new ArrayList<>();
        String dateNow=new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        SQLiteDatabase db=new DBContext(context).getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Activity WHERE Date='"+dateNow+"'", null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            String name = cursor.getString(cursor.getColumnIndex("Status"));
            String date = cursor.getString(cursor.getColumnIndex("Date"));
            String time = cursor.getString(cursor.getColumnIndex("Time"));
            int icon = cursor.getInt(cursor.getColumnIndex("Icon"));
            lst.add(new Activity(name,date,time,icon));
            cursor.moveToNext();
        }
        Collections.sort(lst);
        return lst;
    }
    public static List<Activity> getDataToList(Context context){
        List<Activity> lst=new ArrayList<>();
        SQLiteDatabase db=new DBContext(context).getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Activity", null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            String name = cursor.getString(cursor.getColumnIndex("Status"));
            String date = cursor.getString(cursor.getColumnIndex("Date"));
            String time = cursor.getString(cursor.getColumnIndex("Time"));
            int icon = cursor.getInt(cursor.getColumnIndex("Icon"));
            lst.add(new Activity(name,date,time,icon));
            cursor.moveToNext();
        }
        return lst;
    }
}
