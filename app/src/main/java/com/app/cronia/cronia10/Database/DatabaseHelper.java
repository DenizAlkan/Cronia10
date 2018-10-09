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


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME+".db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_USER_ACTION + " ("+UA_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                UA_ACTION_ID +" TINYINT NOT NULL,"+
                UA_USER_ID +" INT NOT NULL,"+
                UA_START_DATE+" DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,"+
                UA_FINISH_DATE+" DATETIME)";

        Log.d(TAG, "USER_ACTION tableCreate: query: " + createTable);
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_ACTION);
        onCreate(db);
    }

    public boolean addData(int item,int item2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UA_ACTION_ID, item);
        contentValues.put(UA_USER_ID, item2);

        Log.d(TAG, "addData: Adding " + item + " and " +item2+ " to " + TABLE_USER_ACTION);

        Long result = db.insert(TABLE_USER_ACTION, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns all the data from database
     * @return
     */
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


    public void updateName(){
        //String getDate = DateFormat.getDateTimeInstance().format(new Date());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String getDate=timestamp.toString();

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_USER_ACTION + " SET " + UA_FINISH_DATE +
                " = \"" + getDate + "\" WHERE " +UA_ID+ " = ( SELECT  MAX(" +UA_ID+ ") FROM " +TABLE_USER_ACTION+" )" ;
        Log.d(TAG, "updateDate: query: " + query);
        db.execSQL(query);
    }

    /*
    public void deleteName(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }
    */
}

