package com.cntt.dbom.loveapp.Entity;

import com.cntt.dbom.loveapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.Collections.sort;

/**
 * Created by Dbom on 12/13/2016.
 */

public class Activity implements Comparable<Activity> {
    private String status;
    private String date;
    private String time;
    private Date dateTime;
    private int icon;
    public static SimpleDateFormat SDF=new SimpleDateFormat("HH:mm dd/MM/yyyy");
    public Activity(String status, String date, String time, int icon){
        this.status=status;
        this.date=date;
        this.time=time;
        this.icon=icon;
        try{
            dateTime=SDF.parse(time+" "+date);
        }catch (Exception e){
            dateTime=new Date();
        }
    }
    public int getIcon() {
        return icon;
    }
    public String getStatus() {
        return status;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public Date getDateTime() {
        return dateTime;
    }


    @Override
    public int compareTo(Activity o) {
        SimpleDateFormat sdfDate=new SimpleDateFormat("dd");
        SimpleDateFormat sdfMonth=new SimpleDateFormat("MM");
        SimpleDateFormat sdfYear=new SimpleDateFormat("yyyy");
        int oDay=Integer.parseInt(sdfDate.format(o.getDateTime()));
        int oMonth=Integer.parseInt(sdfMonth.format(o.getDateTime()));
        int oYear=Integer.parseInt(sdfYear.format(o.getDateTime()));
        int Day=Integer.parseInt(sdfDate.format(getDateTime()));
        int Month=Integer.parseInt(sdfMonth.format(getDateTime()));
        int Year=Integer.parseInt(sdfYear.format(getDateTime()));
        if(Year!=oYear) return oYear-Year;
        else    if(Month!=oMonth) return oMonth-Month;
        else    if(Day!=oDay) return oDay-Day;
        return (int)(o.getDateTime().getTime()-getDateTime().getTime());
    }
}
