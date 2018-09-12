package com.app.cronia.cronia10.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context c) {
        super(c , "CroniaDB",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "create table Users (USER_ID int primary key autoincrement"+
                ",USER_NAME nvarchar(30) not null"+
                ",MAIL nvarchar(50) not null" +
                ",PASSWORD nvarchar(20) not null"+
                ",REGISTER_DATE datetime default current_timestamp"+
                ",USER_TYPE_ID tinyint not null"+
                ",STATUS_ID tinyint)";
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int eskiV, int yeniV) {

    }
}
