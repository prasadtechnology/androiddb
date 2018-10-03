package com.example.prasad.dbapplication.ContractClass;

import android.provider.BaseColumns;

public final class ContractClass {

    public final class CalenderEntry implements BaseColumns{

        public static final String TABLE_NAME = "calender_entry";
        public static final String _ID = "id";
        public static final String _EVENT_NAME= "event_name";
        public static final String _EVENT_TIME = "event_time";
        public static final String _EVENT_DESC = "event_desc";


    }
}
