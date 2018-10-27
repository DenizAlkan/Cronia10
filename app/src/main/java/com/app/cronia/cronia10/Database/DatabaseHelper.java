package com.app.cronia.cronia10.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.format.DateFormat;
import android.util.Log;

import java.sql.Timestamp;

/**
 * Created by User on 2/28/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TAG = "DatabaseHelper";

    public static final String DATABASE_NAME = "CroniaDB";

    public static final String TABLE_USER_ACTION = "USER_ACTION";
    public static final String UA_ID = "ID";
    public static final String UA_ACTION_ID = "ACTION_ID";
    public static final String UA_USER_ID = "USER_ID";
    public static final String UA_START_DATE = "START_DATE";
    public static final String UA_FINISH_DATE = "FINISH_DATE";

    public static final String TABLE_ACTIONS = "ACTIONS";
    public static final String A_ID = "ID";
    public static final String A_NAME = "NAME";
    public static final String A_IMAGE_URL = "IMAGE_URL";

    public static final String TABLE_USERS = "USERS";
    public static final String U_ID = "ID";
    public static final String U_USER_NAME = "USER_NAME";
    public static final String U_MAIL = "MAIL";
    public static final String U_PASSWORD = "PASSWORD";
    public static final String U_REGISTER_DATE = "REGISTER_DATE";

    public static final String TABLE_USER_DETAILS = "USER_DETAILS";
    public static final String UD_USER_ID = "USER_ID";
    public static final String UD_FIRST_NAME = "FIRST_NAME";
    public static final String UD_LAST_NAME = "LAST_NAME";
    public static final String UD_GENDER = "GENDER";
    public static final String UD_BIRTH_DATE = "BIRTH_DATE";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME+".db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable_UserAction = "CREATE TABLE " + TABLE_USER_ACTION + " ("+UA_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                UA_ACTION_ID +" TINYINT NOT NULL,"+
                UA_USER_ID +" INT NOT NULL,"+
                UA_START_DATE+" DATETIME NOT NULL DEFAULT (DATETIME('now','localtime')),"+
                UA_FINISH_DATE+" DATETIME)";

        String createTable_Actions = "CREATE TABLE " + TABLE_ACTIONS + " ("+A_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                A_NAME +" TEXT NOT NULL,"+
                A_IMAGE_URL +" TEXT NULL)";

        String createTable_Users = "CREATE TABLE "+TABLE_USERS + " ("+U_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                U_USER_NAME+" TEXT NOT NULL,"+
                U_MAIL +" TEXT NOT NULL,"+
                U_PASSWORD +" TEXT NOT NULL,"+
                U_REGISTER_DATE+ " DATETIME NOT NULL DEFAULT (DATETIME('now','localtime')) )";

        String createTable_UserDetails = "CREATE TABLE "+TABLE_USER_DETAILS + " ("+UD_USER_ID+" INTEGER NOT NULL, " +
                UD_FIRST_NAME+" TEXT,"+
                UD_LAST_NAME +" TEXT,"+
                UD_GENDER+ " TEXT,"+
                UD_BIRTH_DATE+ " DATE)";

        Log.d(TAG, "USER_ACTION tableCreate: query: " + createTable_UserAction);
        db.execSQL(createTable_UserAction);
        Log.d(TAG, "ACTIONS tableCreate: query: " + createTable_Actions);
        db.execSQL(createTable_Actions);
        Log.d(TAG, "USERS tableCreate: query: " + createTable_Users);
        db.execSQL(createTable_Users);
        Log.d(TAG, "USER_DETAILS tableCreate: query: " + createTable_UserDetails);
        db.execSQL(createTable_UserDetails);

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
        Log.d(TAG,"insert result :"+result);
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

    /*
    public Cursor getItemID(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT  MAX(" +UA_ID+ ") FROM " +TABLE_USER_ACTION;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    */

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



    public Cursor Listele(){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT "+A_NAME+","+UA_START_DATE+","+UA_FINISH_DATE+",Cast ((\n" +
                "    JulianDay("+UA_FINISH_DATE+") - JulianDay("+UA_START_DATE+")\n" +
                ") * 24 * 60 * 60 As Integer) AS Toplam FROM "+ TABLE_USER_ACTION+" AS ua INNER JOIN "+TABLE_ACTIONS+" AS a" +
                " ON ua."+UA_ACTION_ID+" = a."+A_ID+" WHERE "+UA_FINISH_DATE+" IS NOT NULL";
        Log.d(TAG, "Listele: query: " + query);
        Cursor cursor = db.rawQuery(query,null);

        return  cursor;
    }

    public String maxAction;
    public int maxTime;

    public String getMaxAction() {
        return maxAction;
    }

    public void setMaxAction(String maxAction) {
        this.maxAction = maxAction;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(int maxTime) {
        this.maxTime = maxTime;
    }

    public void maxActionDetail (){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "WITH CTE AS (SELECT " + A_NAME + " ,SUM(Cast ((JulianDay(" + UA_FINISH_DATE + ") - JulianDay(" + UA_START_DATE + "))" +
                " * 24 * 60 * 60 As Integer)) AS Toplam FROM " + TABLE_USER_ACTION + " AS ua INNER JOIN " + TABLE_ACTIONS + " AS a " +
                "ON ua." + UA_ACTION_ID + " = a." + A_ID + "  WHERE "+UA_FINISH_DATE+" IS NOT NULL GROUP BY " + A_NAME + ") SELECT "+A_NAME+",Toplam FROM CTE WHERE Toplam = " +
                "(SELECT MAX(Toplam) FROM CTE ) LIMIT 1";

        Log.d(TAG, "maxActionDetail: query: " + query);

        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()) {
            setMaxAction(cursor.getString(0));
            setMaxTime(cursor.getInt(1));
        }
        cursor.close();
        Log.d(TAG,"En çok yapılan etkinlik: "+getMaxAction()+",süresi: "+getMaxTime());
    }

    public Cursor grafikListe(){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "WITH CTE AS (SELECT " + A_NAME + " ,SUM(Cast ((JulianDay(" + UA_FINISH_DATE + ") - JulianDay(" + UA_START_DATE + "))" +
                " * 24 * 60 * 60 As Integer)) AS Toplam FROM " + TABLE_USER_ACTION + " AS ua INNER JOIN " + TABLE_ACTIONS + " AS a " +
                "ON ua." + UA_ACTION_ID + " = a." + A_ID + "  WHERE "+UA_FINISH_DATE+" IS NOT NULL GROUP BY " + A_NAME + ") SELECT "+A_NAME+",Toplam FROM CTE ORDER BY " +
                "Toplam DESC LIMIT 4";

        Log.d(TAG, "grafikListe: query: " + query);
        Cursor cursor = db.rawQuery(query,null);

        return  cursor;
    }


}


