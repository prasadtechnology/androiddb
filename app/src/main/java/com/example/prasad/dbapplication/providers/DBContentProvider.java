package com.example.prasad.dbapplication.providers;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.prasad.dbapplication.ContractClass.ContractClass;
import com.example.prasad.dbapplication.ContractClass.ContractClass.CalenderEntry;
import com.example.prasad.dbapplication.db.CalenderDBHelper;

public class DBContentProvider extends ContentProvider {

    private static final String TAG = DBContentProvider.class.getSimpleName();
    private CalenderDBHelper calenderDBHelper;
    public static final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        matcher.addURI(ContractClass.CONTENT_AUTHORITY,"events",ContractClass.EVENTS);
        matcher.addURI(ContractClass.CONTENT_AUTHORITY,"events/#",ContractClass.EVENTS_ID);
    }

    @Override
    public boolean onCreate() {
        calenderDBHelper = new CalenderDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = calenderDBHelper.getReadableDatabase();
        Cursor cursor = null;

        int matchResult = matcher.match(uri);

        switch (matchResult){

            case ContractClass.EVENTS:
                cursor = db.query(CalenderEntry.TABLE_NAME,null,null,null,null,null,sortOrder);
                break;
            case ContractClass.EVENTS_ID:
                selection = CalenderEntry._ID+"=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = db.query(CalenderEntry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder,null);
                break;
            default:
                throw new IllegalArgumentException("Cant query the unknown URI = "+uri);
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        int matchResult = matcher.match(uri);
        Uri finalUri = null;

        switch (matchResult){
            case ContractClass.EVENTS:
                finalUri = insertEvent(uri,values);
                break;
            default:
                new IllegalArgumentException("un known uri : "+uri);
        }

        return finalUri;
    }

    private Uri insertEvent(Uri uri,ContentValues values){

        SQLiteDatabase db = calenderDBHelper.getWritableDatabase();

        long id = db.insert(CalenderEntry.TABLE_NAME,null,values);

        if(id == -1){
            Log.e(TAG,"Failed to insert row for : "+uri);
            return null;
        }

        return ContentUris.withAppendedId(uri,id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
