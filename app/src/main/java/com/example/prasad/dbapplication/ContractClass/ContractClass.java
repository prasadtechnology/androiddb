package com.example.prasad.dbapplication.ContractClass;

import android.net.Uri;
import android.provider.BaseColumns;

public final class ContractClass {

    public static final String CONTENT_AUTHORITY = "com.example.prasad.dbapplication.providers";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHORITY);
    public static final String PATH_EVENTS = "events";
    public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI,PATH_EVENTS);

    public static final int EVENTS = 2000;
    public static final int EVENTS_ID = 2001;


    public final class CalenderEntry implements BaseColumns{

        public static final String TABLE_NAME = "calender_entry";
        public static final String _ID = "id";
        public static final String _EVENT_NAME= "event_name";
        public static final String _EVENT_TIME = "event_time";
        public static final String _EVENT_DESC = "event_desc";


    }
}
