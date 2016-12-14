package com.cntt.dbom.loveapp.Entity;

import com.cntt.dbom.loveapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Dbom on 12/14/2016.
 */

public class Event {
    private int id;
    private String name;
    private Date date;
    private int type;
    //1: Event Hàng năm
    //2: Event 1 lần
    private int icon;

    public Event(int id,String name,Date date,int type,int icon){
        this.id=id;
        this.name=name;
        this.date=date;
        this.type=type;
        this.icon=icon;
    }

    public int getType() {
        return type;
    }

    public int getId() {
        return id;
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

    public int getIcon() {
        return icon;
    }

    public long getDaysLeft(){
        return (date.getTime()-(new Date()).getTime())/(1000*60*60*24);
    }
    public static List<Event> getList(){
        List<Event> lst=new ArrayList<>();
        Date d1,d2,d3,d4,d5;
        SimpleDateFormat SDF=new SimpleDateFormat("dd/MM/yyyy");
        try{
            d1=SDF.parse("24/12/2016");
            d2=SDF.parse("01/01/2017");
            d3=SDF.parse("28/01/2017");
            d4=SDF.parse("14/02/2017");
            d5=SDF.parse("01/06/2017");
        }catch (Exception e){
            d1=new Date();
            d2=new Date();
            d3=new Date();
            d4=new Date();
            d5=new Date();
        }
        lst.add(new Event(1,"Noel",d1,1, R.drawable.calendar_important));
        lst.add(new Event(2,"Tết dương lịch",d2,1, R.drawable.calendar_schedule));
        lst.add(new Event(3,"Tết Âm lịch",d3,1, R.drawable.calendar_schedule));
        lst.add(new Event(4,"Valentine",d4,1, R.drawable.calendar_love));
        lst.add(new Event(5,"Quốc tế thiếu nhi",d5,1, R.drawable.calendar_schedule));
        return lst;
    }
}
