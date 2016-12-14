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

public class Activity {
    private int id;
    private String status;
    private Date time;
    private int icon;
    public static SimpleDateFormat SDF=new SimpleDateFormat("HH:mm dd/MM/yyyy");
    public Activity(int id,String status, Date time, int icon){
        this.id=id;
        this.status=status;
        this.time=time;
        this.icon=icon;
    }
    public int getIcon() {
        return icon;
    }
    public int getId() {
        return id;
    }
    public String getStatus() {
        return status;
    }
    public String getTime(){
        return (new SimpleDateFormat("HH:mm")).format(time);
    }
    public String getDate(){
        return (new SimpleDateFormat("dd/MM/yyyy")).format(time);
    }
    public static List<Activity> getList2(){
        List<Activity> lst=new ArrayList<>();

        Date d0,d1,d2,d3,d4,d5,d6,d7;
        try{
            d0=SDF.parse("21:32 14/12/2016");
            d1=SDF.parse("12:05 12/12/2016");
            d2=SDF.parse("12:02 12/12/2016");
            d3=SDF.parse("11:12 11/12/2016");
            d4=SDF.parse("10:00 11/12/2016");
            d5=SDF.parse("07:40 11/12/2016");
            d6=SDF.parse("06:05 10/12/2016");
            d7=SDF.parse("16:05 10/12/2016");
        }catch (Exception e) {
            d0=d1=d2=d3=d4=d5=d6=d7= new Date();
        }
        lst.add(new Activity(1,"Buổi tối buồn",d0, R.drawable.emoticon_sad));
        lst.add(new Activity(1,"Buổi trưa tuyệt vời <3",d1, R.drawable.emoticon_happy));
        lst.add(new Activity(2,"Em đem cơm qua rồi",d2, R.drawable.emoticon_love));
        lst.add(new Activity(3,"Trưa nay em không nấu cơm cho mình ăn",d3, R.drawable.emoticon_angry));
        lst.add(new Activity(4,"Về thấu nhà rồi",d4, R.drawable.emoticon_happy));
        lst.add(new Activity(5,"Cảm giác chờ phụ nữ thật là ÂY GÙ",d5, R.drawable.emoticon_shocked));
        lst.add(new Activity(6,"Phải dậy qua chở em đi học, buồn ngủ quá",d6, R.drawable.emoticon_sad));
        lst.add(new Activity(7,"Chiều ngồi học mà nhớ em quá",d7, R.drawable.emoticon_love));
        return lst;
    }
    public static List<Activity> getList(){
        List<Activity> lst=new ArrayList<>();

        Date d1,d2,d3,d4,d5,d6;
        try{
            d1=SDF.parse("14:05 12/12/2016");
            d2=SDF.parse("12:02 12/12/2016");
            d3=SDF.parse("11:12 12/12/2016");
            d4=SDF.parse("10:00 12/12/2016");
            d5=SDF.parse("07:40 12/12/2016");
            d6=SDF.parse("06:05 12/12/2016");
        }catch (Exception e) {
            d1=d2=d3=d4=d5=d6= new Date();
        }
        lst.add(new Activity(1,"Buổi trưa tuyệt vời <3",d1, R.drawable.emoticon_happy));
        lst.add(new Activity(2,"Em đem cơm qua rồi",d2, R.drawable.emoticon_love));
        lst.add(new Activity(3,"Trưa nay em không nấu cơm cho mình ăn",d3, R.drawable.emoticon_angry));
        lst.add(new Activity(4,"Về thấu nhà rồi",d4, R.drawable.emoticon_happy));
        lst.add(new Activity(5,"Cảm giác chờ phụ nữ thật là ÂY GÙ",d5, R.drawable.emoticon_shocked));
        lst.add(new Activity(6,"Phải dậy qua chở em đi học, buồn ngủ quá",d6, R.drawable.emoticon_sad));
        lst.add(new Activity(1,"Buổi trưa tuyệt vời <3",d1, R.drawable.emoticon_happy));
        lst.add(new Activity(2,"Em đem cơm qua rồi",d2, R.drawable.emoticon_love));
        lst.add(new Activity(3,"Trưa nay em không nấu cơm cho mình ăn",d3, R.drawable.emoticon_angry));
        lst.add(new Activity(4,"Về thấu nhà rồi",d4, R.drawable.emoticon_happy));
        lst.add(new Activity(5,"Cảm giác chờ phụ nữ thật là ÂY GÙ",d5, R.drawable.emoticon_shocked));
        lst.add(new Activity(6,"Phải dậy qua chở em đi học, buồn ngủ quá",d6, R.drawable.emoticon_sad));
        lst.add(new Activity(1,"Buổi trưa tuyệt vời <3",d1, R.drawable.emoticon_happy));
        lst.add(new Activity(2,"Em đem cơm qua rồi",d2, R.drawable.emoticon_love));
        lst.add(new Activity(3,"Trưa nay em không nấu cơm cho mình ăn",d3, R.drawable.emoticon_angry));
        lst.add(new Activity(4,"Về thấu nhà rồi",d4, R.drawable.emoticon_happy));
        lst.add(new Activity(5,"Cảm giác chờ phụ nữ thật là ÂY GÙ",d5, R.drawable.emoticon_shocked));
        lst.add(new Activity(6,"Phải dậy qua chở em đi học, buồn ngủ quá",d6, R.drawable.emoticon_sad));
        lst.add(new Activity(1,"Buổi trưa tuyệt vời <3",d1, R.drawable.emoticon_happy));
        lst.add(new Activity(2,"Em đem cơm qua rồi",d2, R.drawable.emoticon_love));
        lst.add(new Activity(3,"Trưa nay em không nấu cơm cho mình ăn",d3, R.drawable.emoticon_angry));
        lst.add(new Activity(4,"Về thấu nhà rồi",d4, R.drawable.emoticon_happy));
        lst.add(new Activity(5,"Cảm giác chờ phụ nữ thật là ÂY GÙ",d5, R.drawable.emoticon_shocked));
        lst.add(new Activity(6,"Phải dậy qua chở em đi học, buồn ngủ quá",d6, R.drawable.emoticon_sad));
        lst.add(new Activity(1,"Buổi trưa tuyệt vời <3",d1, R.drawable.emoticon_happy));
        lst.add(new Activity(2,"Em đem cơm qua rồi",d2, R.drawable.emoticon_love));
        lst.add(new Activity(3,"Trưa nay em không nấu cơm cho mình ăn",d3, R.drawable.emoticon_angry));
        lst.add(new Activity(4,"Về thấu nhà rồi",d4, R.drawable.emoticon_happy));
        lst.add(new Activity(5,"Cảm giác chờ phụ nữ thật là ÂY GÙ",d5, R.drawable.emoticon_shocked));
        lst.add(new Activity(6,"Phải dậy qua chở em đi học, buồn ngủ quá",d6, R.drawable.emoticon_sad));
        lst.add(new Activity(1,"Buổi trưa tuyệt vời <3",d1, R.drawable.emoticon_happy));
        lst.add(new Activity(2,"Em đem cơm qua rồi",d2, R.drawable.emoticon_love));
        lst.add(new Activity(3,"Trưa nay em không nấu cơm cho mình ăn",d3, R.drawable.emoticon_angry));
        lst.add(new Activity(4,"Về thấu nhà rồi",d4, R.drawable.emoticon_happy));
        lst.add(new Activity(5,"Cảm giác chờ phụ nữ thật là ÂY GÙ",d5, R.drawable.emoticon_shocked));
        lst.add(new Activity(6,"Phải dậy qua chở em đi học, buồn ngủ quá",d6, R.drawable.emoticon_sad));
        return lst;
    }
}
