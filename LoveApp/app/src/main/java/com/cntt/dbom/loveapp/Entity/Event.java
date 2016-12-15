package com.cntt.dbom.loveapp.Entity;

import android.util.Log;

import com.cntt.dbom.loveapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Dbom on 12/14/2016.
 */

public class Event implements Comparable<Event>{
    private String name;
    private Date date;
    private String txtOldDate;
    private String txtDate;
    private int type;
    //1: Event Hàng năm
    //2: Event 1 lần
    private int icon;

    public Event(String name,Date date,int type,int icon){
        this.name=name;
        this.date=date;
        this.type=type;
        this.icon=icon;
    }
    public Event(String name,String date,int type,int icon){
        this.name=name;
        this.txtDate=date;
        this.txtOldDate=date;
        try{
            this.date=(new SimpleDateFormat("dd/MM/yyyy").parse(date));
        }catch (Exception e){
            this.date=new Date();
        }
        this.type=type;
        this.icon=icon;
    }

    public void setTxtDate(String date) {
        this.txtDate=date;
        try{
            this.date=(new SimpleDateFormat("dd/MM/yyyy").parse(date));
        }catch (Exception e){
            this.date=new Date();
        }
    }

    public int getType() {
        return type;
    }

    public String getDate() {
        return (new SimpleDateFormat("dd/MM/yyyy")).format(date);
    }
    public String getMonthYear(){
        return (new SimpleDateFormat("MM/yyyy")).format(date);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullDate() {
        return txtDate;
    }
    public Date getDateFomat(){
        return date;
    }

    public int getIcon() {
        return icon;
    }

    public long getDaysLeft(){
        return 1+(date.getTime()-(new Date()).getTime())/(1000*60*60*24);
    }

    public String getTxtOldDate() {
        return txtOldDate;
    }

    @Override
    public int compareTo(Event o) {
//        SimpleDateFormat sdfDate=new SimpleDateFormat("dd");
//        SimpleDateFormat sdfMonth=new SimpleDateFormat("MM");
//        SimpleDateFormat sdfYear=new SimpleDateFormat("yyyy");
//        int oDay=Integer.parseInt(sdfDate.format(o.getDateFomat()));
//        int oMonth=Integer.parseInt(sdfMonth.format(o.getDateFomat()));
//        int oYear=Integer.parseInt(sdfYear.format(o.getDateFomat()));
//        int Day=Integer.parseInt(sdfDate.format(getDateFomat()));
//        int Month=Integer.parseInt(sdfMonth.format(getDateFomat()));
//        int Year=Integer.parseInt(sdfYear.format(getDateFomat()));
//        if(Year!=oYear) return Year-oYear;
//        else    if(Month!=oMonth) return Month-oMonth;
//        else    if(Day!=oDay) return Day-oDay;
        return (int)(getDaysLeft()-o.getDaysLeft());
    }
}
