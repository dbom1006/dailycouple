package com.cntt.dbom.loveapp.DAL;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.cntt.dbom.loveapp.Entity.Profile;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Bac Nice on 12/14/2016.
 */

public class DAO extends SQLiteOpenHelper {

    public static final String DB_name = "LoveApp.DB";
    public static final int DB_version = 1;
    public DAO(Context context) {
        super(context, DB_name, null, DB_version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Profile (\n" +
                "    NameX        VARCHAR (30) NOT NULL,\n" +
                "    BirthdayX    DATE,\n" +
                "    NameY        VARCHAR (30),\n" +
                "    BirthdayY    DATE,\n" +
                "    DateBegin                  NOT NULL,\n" +
                "    Relationship              NOT NULL\n" +
                ");");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
