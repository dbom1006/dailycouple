package com.cntt.dbom.loveapp.DAL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.cntt.dbom.loveapp.R;

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
                "    Relationship VARCHAR (30),\n" +
                "    ImgX         VARCHAR (255),\n" +
                "    ImgY         VARCHAR (255)\n" +
                ");\n");

        db.execSQL("CREATE TABLE Event (\n" +
                "    Name VARCHAR (50) NOT NULL,\n" +
                "    Date VARCHAR,\n" +
                "    Type INTEGER,\n" +
                "    Icon INTEGER\n" +
                ");\n");
        db.execSQL("CREATE TABLE Activity (\n" +
                "    Status VARCHAR (100) NOT NULL,\n" +
                "    Date   VARCHAR (10),\n" +
                "    Time   VARCHAR (10),\n" +
                "    Icon   INTEGER (10) \n" +
                ");\n");
        db.execSQL("INSERT INTO Event (\n" +
                "                      Icon,\n" +
                "                      Type,\n" +
                "                      Date,\n" +
                "                      Name\n" +
                "                  )\n" +
                "                  VALUES (\n" +
                "                      "+ R.drawable.calendar_schedule+",\n" +
                "                      1,\n" +
                "                      '24/12/2016',\n" +
                "                      'Noel'\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      "+ R.drawable.calendar_schedule+",\n" +
                "                      1,\n" +
                "                      '01/01/2017',\n" +
                "                      'Tết dương lịch'\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      "+R.drawable.calendar_love+",\n" +
                "                      1,\n" +
                "                      '14/02/2017',\n" +
                "                      'Valentine'\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      "+ R.drawable.calendar_schedule+",\n" +
                "                      1,\n" +
                "                      '08/03/2017',\n" +
                "                      'Phụ nữ Việt Nam'\n" +
                "                  ),\n" +
                "                  (\n" +
                "                      "+ R.drawable.calendar_schedule+",\n" +
                "                      1,\n" +
                "                      '01/06/2017',\n" +
                "                      'Quốc tế thiếu nhi'\n" +
                "                  );\n");
        db.execSQL("INSERT INTO Activity (\n" +
                "                         Icon,\n" +
                "                         Time,\n" +
                "                         Date,\n" +
                "                         Status\n" +
                "                     )\n" +
                "                     VALUES (\n" +
                "                         "+R.drawable.em_haha+",\n" +
                "                         '12:04',\n" +
                "                         '12/12/2016',\n" +
                "                         'Cuộc đời buồn chán 1'\n" +
                "                     ),\n" +
                "                     (\n" +
                "                         "+R.drawable.em_cry+",\n" +
                "                         '12:05',\n" +
                "                         '12/12/2016',\n" +
                "                         'Cuộc đời buồn chán 2'\n" +
                "                     ),\n" +
                "                     (\n" +
                "                         "+R.drawable.em_angry+",\n" +
                "                         '12:06',\n" +
                "                         '12/12/2016',\n" +
                "                         'Cuộc đời buồn chán 3'\n" +
                "                     ),\n" +
                "                     (\n" +
                "                         "+R.drawable.em_like+",\n" +
                "                         '12:07',\n" +
                "                         '12/10/2016',\n" +
                "                         'Cuộc đời buồn chán 4'\n" +
                "                     ),\n" +
                "                     (\n" +
                "                         "+R.drawable.em_wow+",\n" +
                "                         '12:08',\n" +
                "                         '12/10/2016',\n" +
                "                         'Cuộc đời buồn chán 5'\n" +
                "                     ),\n" +
                "                     (\n" +
                "                         "+R.drawable.em_love+",\n" +
                "                         '12:09',\n" +
                "                         '12/10/2016',\n" +
                "                         'Cuộc đời buồn chán 6'\n" +
                "                     );\n");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
