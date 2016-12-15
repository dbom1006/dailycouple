package com.cntt.dbom.loveapp.DAL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dbom on 10/6/2016.
 */
public class DBContext extends SQLiteOpenHelper {

    public final static String  DB_Name = "loveapp.db";
    public final static int Db_Version=1;
    public DBContext(Context context) {
        super(context, DB_Name, null, Db_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Profile (\n" +
                "    NameX        VARCHAR (30) NOT NULL,\n" +
                "    BirthdayX    DATE,\n" +
                "    NameY        VARCHAR (30) NOT NULL,\n" +
                "    BirthdayY    DATE,\n" +
                "    DateBegin    DATE         NOT NULL,\n" +
                "    Relationship VARCHAR,\n" +
                "    ImgX         STRING,\n" +
                "    ImgY         STRING\n" +
                ");\n");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
