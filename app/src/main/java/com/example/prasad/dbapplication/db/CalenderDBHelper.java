package com.example.prasad.dbapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.prasad.dbapplication.ContractClass.ContractClass.CalenderEntry;



public class CalenderDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "calender.db";
    private static final int DB_VERSION = 1;
    private static final String TAG = CalenderDBHelper.class.getCanonicalName();

    public CalenderDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i(TAG,"came to on create in calender helper class ...");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + CalenderEntry.TABLE_NAME+ " ("
                + CalenderEntry._ID + " INTEGER PRIMARY KEY,"
                + CalenderEntry._EVENT_NAME + " TEXT,"
                + CalenderEntry._EVENT_DESC + " TEXT,"
                + CalenderEntry._EVENT_TIME + " INTEGER);");

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
