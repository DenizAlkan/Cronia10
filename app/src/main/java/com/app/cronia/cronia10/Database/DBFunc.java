package com.app.cronia.cronia10.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.app.cronia.cronia10.Register;
import com.app.cronia.cronia10.Database.DatabaseHelper;

import java.util.Date;

public abstract class DBFunc extends Context {

    public static final String TAG = "DBFunc";

    final DatabaseHelper mdb = new DatabaseHelper(this);



    public int registerControl (String userName,String mail){
        Log.d(TAG,"registerControl,degiskenler : "+userName+" , "+mail);

        SQLiteDatabase db = mdb.getWritableDatabase();
        int result = 0;
        Log.d(TAG,"buraya geldi");

        String userNameQuery = "SELECT * FROM "+mdb.TABLE_USERS+ " WHERE "+mdb.U_USER_NAME+" = \'"+userName+"\'";
        String mailQuery = "SELECT * FROM "+mdb.TABLE_USERS+ " WHERE "+mdb.U_MAIL+" = \'"+mail+"\'";



        Cursor c1 = db.rawQuery(userNameQuery,null);
        Log.d(TAG,userName+" Count : "+c1.getCount());

        Cursor c2 = db.rawQuery(mailQuery,null);
        Log.d(TAG,mail+" Count : "+c2.getCount());

        if(c1.getCount() > 0)
        {
            result=result+1;
        }
        c1.close();
        if(c2.getCount() > 0)
        {
            result=result+2;
        }
        c2.close();
        mdb.close();
        return result;
    }

    public void userInsert (String userName, String mail, String pass, String firstName, String lastName, Date dateOfBirth, String gender){
        SQLiteDatabase db = mdb.getWritableDatabase();

        ContentValues c1 = new ContentValues();
        c1.put(mdb.U_USER_NAME  ,userName );
        c1.put(mdb.U_MAIL  ,mail );
        c1.put(mdb.U_PASSWORD  ,pass );

        Long result1 = db.insert(mdb.TABLE_USERS,null,c1);

        ContentValues c2 = new ContentValues();
        c2.put(mdb.UD_USER_ID, result1);
        c2.put(mdb.UD_FIRST_NAME, firstName);
        c2.put(mdb.UD_LAST_NAME, lastName);
        c2.put(mdb.UD_BIRTH_DATE, String.valueOf(dateOfBirth));
        c2.put(mdb.UD_GENDER, gender);

        db.insert(mdb.TABLE_USER_DETAILS,null,c2);

        db.close();

    }

}
