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
                "    DateBegin    DATE,\n" +
                "    Relationship VARCHAR\n" +
                ");\n");
        db.execSQL("CREATE TABLE Event (\n" +
                "    Name VARCHAR (50) NOT NULL,\n" +
                "    Date VARCHAR,\n" +
                "    Type INTEGER,\n" +
                "    Icon INTEGER\n" +
                ");\n");
        db.execSQL("INSERT INTO Event (\n" +
                "                      Icon,\n" +
                "                      Type,\n" +
                "                      Date,\n" +
                "                      Name\n" +
                "                  )\n" +
                "                  VALUES (\n" +
                "                      2130837588,\n" +
                "                      1,\n" +
                "                      '24/12/2016',\n" +
                "                      'Noel'\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      2130837588,\n" +
                "                      1,\n" +
                "                      '01/01/2017',\n" +
                "                      'Tết dương lịch'\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      2130837587,\n" +
                "                      1,\n" +
                "                      '14/02/2017',\n" +
                "                      'Valentine'\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      2130837586,\n" +
                "                      1,\n" +
                "                      '22/02/1995',\n" +
                "                      'Sinh nhật Anh'\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      2130837586,\n" +
                "                      1,\n" +
                "                      '28/06/1995',\n" +
                "                      'Sinh nhật Em'\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      2130837588,\n" +
                "                      1,\n" +
                "                      '08/03/2017',\n" +
                "                      'Phụ nữ Việt Nam'\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      2130837588,\n" +
                "                      1,\n" +
                "                      '01/06/2017',\n" +
                "                      'Quốc tế thiếu nhi'\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      2130837587,\n" +
                "                      2,\n" +
                "                      '25/06/2017',\n" +
                "                      'Dẫn gấu đi họp lớp'\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      2130837587,\n" +
                "                      2,\n" +
                "                      '15/01/2017',\n" +
                "                      'Dẫn gấu di mua đồ tết'\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      2130837587,\n" +
                "                      2,\n" +
                "                      '12/10/2016',\n" +
                "                      'Dẫn gấu đi xem phim'\n" +
                "                  );\n");
        db.execSQL("INSERT INTO Profile (\n" +
                "                        Relationship,\n" +
                "                        DateBegin,\n" +
                "                        BirthdayY,\n" +
                "                        NameY,\n" +
                "                        BirthdayX,\n" +
                "                        NameX\n" +
                "                    )\n" +
                "                    VALUES (\n" +
                "                        'Couple Love',\n" +
                "                        '2015-12-12',\n" +
                "                        '1995-07-25',\n" +
                "                        'Si',\n" +
                "                        '1995-06-10',\n" +
                "                        'Bom'\n" +
                "                    );\n");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
