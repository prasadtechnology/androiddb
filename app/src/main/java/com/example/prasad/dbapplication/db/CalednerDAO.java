package com.example.prasad.dbapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.prasad.dbapplication.ContractClass.ContractClass.CalenderEntry;
import com.example.prasad.dbapplication.models.Calender;

import java.util.ArrayList;
import java.util.List;

public class CalednerDAO {

    private static final String TAG = CalednerDAO.class.getName();
    CalenderDBHelper helper = null;
    SQLiteDatabase readableDatabase = null;
    SQLiteDatabase writableDatabase = null;


    public CalednerDAO(Context context){
        this.helper = new CalenderDBHelper(context);
        readableDatabase = helper.getReadableDatabase();
        writableDatabase = helper.getWritableDatabase();
    }

    /**
     * returns list all the calender events
     * @return
     */
    public List<Calender> getCalednderEventList(){
        List<Calender> calenderList = new ArrayList<>();

        Cursor c = readableDatabase.rawQuery("select * from "+ CalenderEntry.TABLE_NAME,null);

        while(c.moveToNext()){

            Calender calender = new Calender();

            calender.setEventId(c.getLong(c.getColumnIndex(CalenderEntry._ID)));
            calender.setEventName(c.getString(c.getColumnIndex(CalenderEntry._EVENT_NAME)));
            calender.setEventDesc(c.getString(c.getColumnIndex(CalenderEntry._EVENT_DESC)));
            calender.setEventTime(c.getLong(c.getColumnIndex(CalenderEntry._EVENT_TIME)));

            Log.i(TAG,"event name is : "+c.getString(c.getColumnIndex(CalenderEntry._EVENT_NAME)));

            calenderList.add(calender);
        }

        return calenderList;
    }

    /**
     * returns particular calender event
     * @param id
     * @return
     */
    public Calender getCalednderEvent(long id){
        Calender calender = new Calender();

        Cursor c = readableDatabase.rawQuery("select * from "+ CalenderEntry.TABLE_NAME+" where "+CalenderEntry._ID+" = "+id,null);
        if(c.moveToNext()){

            calender.setEventId(c.getLong(c.getColumnIndex(CalenderEntry._ID)));
            calender.setEventName(c.getString(c.getColumnIndex(CalenderEntry._EVENT_NAME)));
            calender.setEventDesc(c.getString(c.getColumnIndex(CalenderEntry._EVENT_DESC)));
            calender.setEventTime(c.getLong(c.getColumnIndex(CalenderEntry._EVENT_TIME)));

            Log.i(TAG,"event name is : "+c.getString(c.getColumnIndex(CalenderEntry._EVENT_NAME)));
        }
        return calender;
    }

    /**
     * creates the new calender event
     * @param data
     * @return
     */
    public long insertCalenderEvent(ContentValues data){

        long result = writableDatabase.insert(CalenderEntry.TABLE_NAME,null,data);
        Log.i(TAG," after insert the result is : "+result);
        return result;
    }

    /**
     * updates the particular calender event
     * @param data
     * @param id
     * @return
     */
    public boolean updateCalenderEvent(ContentValues data,long id){
       return writableDatabase.update(CalenderEntry.TABLE_NAME,data,CalenderEntry._ID+"="+id,null) > 0;
    }

    /**
     * deletes the particular calender event
     * @param id
     * @return
     */
    public boolean deleteCalenderEvent(long id){
        return writableDatabase.delete(CalenderEntry.TABLE_NAME,CalenderEntry._ID+"="+id,null) > 0;
    }
}
