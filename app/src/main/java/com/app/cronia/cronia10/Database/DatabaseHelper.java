package com.app.cronia.cronia10.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Timestamp;

/**
 * Created by User on 2/28/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String DATABASE_NAME = "CroniaDB";

    private static final String TABLE_USER_ACTION = "USER_ACTION";
    private static final String UA_ID = "ID";
    private static final String UA_ACTION_ID = "ACTION_ID";
    private static final String UA_USER_ID = "USER_ID";
    private static final String UA_START_DATE = "START_DATE";
    private static final String UA_FINISH_DATE = "FINISH_DATE";

    private static final String TABLE_ACTIONS = "ACTIONS";
    private static final String A_ID = "ID";
    private static final String A_NAME = "NAME";
    private static final String A_IMAGE_URL = "IMAGE_URL";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME+".db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable_UserAction = "CREATE TABLE " + TABLE_USER_ACTION + " ("+UA_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                UA_ACTION_ID +" TINYINT NOT NULL,"+
                UA_USER_ID +" INT NOT NULL,"+
                UA_START_DATE+" DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,"+
                UA_FINISH_DATE+" DATETIME)";

        String createTable_Actions = "CREATE TABLE " + TABLE_ACTIONS + " ("+A_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                A_NAME +" TEXT NOT NULL,"+
                A_IMAGE_URL +" TEXT NULL)";

        Log.d(TAG, "USER_ACTION tableCreate: query: " + createTable_UserAction);
        db.execSQL(createTable_UserAction);
        Log.d(TAG, "ACTIONS tableCreate: query: " + createTable_Actions);
        db.execSQL(createTable_Actions);

        String Actions_Data = "INSERT INTO " + TABLE_ACTIONS + " ("+A_NAME+") " +
                "VALUES ('Yemek'),('Kitap Okuma'),('Uyku'),('Sosyallik'),('Spor'),('Seyahat')";
        Log.d(TAG, "ACTIONS tableInsert: query: " + Actions_Data);
        db.execSQL(Actions_Data);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_ACTION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACTIONS);

        onCreate(db);
    }

    public boolean addData(int ActionID,int UserID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UA_ACTION_ID, ActionID);
        contentValues.put(UA_USER_ID, UserID);

        Log.d(TAG, "addData: Adding " + ActionID + " and " +UserID+ " to " + TABLE_USER_ACTION);

        Long result = db.insert(TABLE_USER_ACTION, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_USER_ACTION;
        Cursor data = db.rawQuery(query, null);
        return data;
    }


    public Cursor getItemID(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT  MAX(" +UA_ID+ ") FROM " +TABLE_USER_ACTION;
        Cursor data = db.rawQuery(query, null);
        return data;
    }


    public void updateFinishDate(String action){
        //String getDate = DateFormat.getDateTimeInstance().format(new Date());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String getDate=timestamp.toString();

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_USER_ACTION + " SET " + UA_FINISH_DATE +
                " = \"" + getDate + "\" WHERE " +UA_ID+ " = ( SELECT  MAX(" +UA_ID+ ") FROM " +TABLE_USER_ACTION+" WHERE "+UA_ACTION_ID+" = " +
                "(SELECT "+A_ID+" FROM "+TABLE_ACTIONS+" WHERE "+A_NAME+"='"+action+"'))" ;
        Log.d(TAG, "updateDate: query: " + query);
        db.execSQL(query);
    }

}

