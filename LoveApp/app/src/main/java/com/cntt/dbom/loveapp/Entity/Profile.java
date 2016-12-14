package com.cntt.dbom.loveapp.Entity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cntt.dbom.loveapp.DAL.DAO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Bac Nice on 12/14/2016.
 */

public class Profile {
    private String nameX;
    private  String nameY;
    private Date birthdayX;
    private Date birthdayY;
    private Date dateBegin;
    private String relationship;

    public Profile(String nameX, Date dateBegin, String relationship) {
        this.nameX = nameX;
        this.dateBegin = dateBegin;
        this.relationship = relationship;
    }
    public Profile(){

    }
    public Profile(String nameX, String nameY, Date birthdayX, Date birthdayY, Date dateBegin, String relationship) {
        this.nameX = nameX;
        this.nameY = nameY;
        this.birthdayX = birthdayX;
        this.birthdayY = birthdayY;
        this.dateBegin = dateBegin;
        this.relationship = relationship;
    }
    public Profile getProfile (Context context){
        SQLiteDatabase database = new DAO(context ).getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from Profile", null);
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
    public String getNameX() {
        return nameX;
    }

    public void setNameX(String nameX) {
        this.nameX = nameX;
    }

    public String getNameY() {
        return nameY;
    }

    public void setNameY(String nameY) {
        this.nameY = nameY;
    }

    public Date getBirthdayX() {
        return birthdayX;
    }

    public void setBirthdayX(Date birthdayX) {
        this.birthdayX = birthdayX;
    }

    public Date getBirthdatY() {
        return birthdayY;
    }

    public void setBirthdatY(Date birthdatY) {
        this.birthdayY = birthdatY;
    }

    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
}
