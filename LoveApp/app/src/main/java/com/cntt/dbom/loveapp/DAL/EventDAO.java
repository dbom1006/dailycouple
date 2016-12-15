package com.cntt.dbom.loveapp.DAL;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.util.SortedList;

import com.cntt.dbom.loveapp.Entity.Event;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by Dbom on 12/15/2016.
 */

public class EventDAO {
    public static String Delete(Event e, Context context){
        SQLiteDatabase db=new DBContext(context).getReadableDatabase();
        String error = null;
        String sql="DELETE FROM Event\n" +
                "        WHERE Date = '"+e.getTxtOldDate()+"' AND\n" +
                "                Name = '"+e.getName()+"'";
        try{
            db.execSQL(sql);
        }
        catch (Exception e1){
            error = e1+"";
        }
        return error;
    }
    public static String DeleteByName(String name, Context context){
        SQLiteDatabase db=new DBContext(context).getReadableDatabase();
        String error=null;
        String sql="DELETE FROM Event\n" +
                "        WHERE  Name = '"+name+"'";
        try{
            db.execSQL(sql);
        }
        catch (Exception e1){
            error = e1+"";
        }
        return error;
    }
    public static String Insert(Event e,Context context){
        SQLiteDatabase db=new DBContext(context).getReadableDatabase();
        String error=null;
        try {
            db.execSQL("INSERT INTO Event (\n" +
                    "                      Name,\n" +
                    "                      Date,\n" +
                    "                      Type,\n" +
                    "                      Icon\n" +
                    "                  )\n" +
                    "                  VALUES (\n" +
                    "                      '"+e.getName()+"',\n" +
                    "                      '"+e.getFullDate()+"',\n" +
                    "                      "+e.getType()+",\n" +
                    "                      "+e.getIcon()+"\n" +
                    "                  );\n");
        }
        catch (Exception e1){
            error = e1 +"";
        }
        return error;
    }
    public static List<Event> getList(Context context){
        List<Event> lst=getDataToList(context);
        Date date=new Date();
        SimpleDateFormat sdfDate=new SimpleDateFormat("dd");
        SimpleDateFormat sdfMonth=new SimpleDateFormat("MM");
        SimpleDateFormat sdfYear=new SimpleDateFormat("yyyy");
        int day= Integer.parseInt(sdfDate.format(date));
        int month=Integer.parseInt(sdfMonth.format(date));
        int year=Integer.parseInt(sdfYear.format(date));
        List<Event> lstDel=new ArrayList<>();
        for (Event e:lst) {
            if(e.getDaysLeft()<0 && e.getType()==2) lstDel.add(e);
            if(e.getType()!=2) {
                int eDay = Integer.parseInt(sdfDate.format(e.getDateFomat()));
                int eMonth = Integer.parseInt(sdfMonth.format(e.getDateFomat()));
                int eYear = Integer.parseInt(sdfYear.format(e.getDateFomat()));
                if (eYear <= year && (eMonth < month || (eMonth == month && eDay < day))) {
                    e.setTxtDate(sdfDate.format(e.getDateFomat()) + "/" + sdfMonth.format(e.getDateFomat()) + "/" + (year + 1));
                } else if (eYear <= year && (eMonth > month || (eMonth == month && eDay >= day))) {
                    e.setTxtDate(sdfDate.format(e.getDateFomat()) + "/" + sdfMonth.format(e.getDateFomat()) + "/" + (year));
                }

            }
        }
        for (Event e: lstDel) {
            lst.remove(e);
        }
        Collections.sort(lst);
        return lst;
    }
    public static List<Event> getDataToList(Context context){
        List<Event> lst=new ArrayList<>();
        SQLiteDatabase db=new DBContext(context).getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Event", null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            String name = cursor.getString(cursor.getColumnIndex("Name"));
            String date = cursor.getString(cursor.getColumnIndex("Date"));
            int type = cursor.getInt(cursor.getColumnIndex("Type"));
            int icon = cursor.getInt(cursor.getColumnIndex("Icon"));
            lst.add(new Event(name,date,type,icon));
            cursor.moveToNext();
        }
        return lst;
    }
}
