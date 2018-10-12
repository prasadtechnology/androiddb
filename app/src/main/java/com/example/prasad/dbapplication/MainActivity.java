package com.example.prasad.dbapplication;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;

import com.example.prasad.dbapplication.ContractClass.ContractClass;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    @Override
    @TargetApi(Build.VERSION_CODES.M)
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Cursor cursor = getContentResolver().query(ContractClass.CONTENT_URI,null,null,null,null,null);
//        CalendarView calendarView = (CalendarView)findViewById(R.id.calendarView);
//        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
//                onAddEventClicked(view);
//            }
//        });

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            Log.d(TAG,"----- permissions not granted -----------");

            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.READ_CALENDAR,Manifest.permission.WRITE_CALENDAR},
                    101);
        }

        Utility.readCalendarEvent(getApplicationContext());

    }

    public void onAddEventClicked(View view) {
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType("vnd.android.cursor.item/event");

        Calendar cal = Calendar.getInstance();
        long startTime = cal.getTimeInMillis();
        long endTime = cal.getTimeInMillis() + 60 * 60 * 1000;

        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime);
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime);
        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);

        intent.putExtra(CalendarContract.Events.TITLE, "Vikranth Birthday");
        intent.putExtra(CalendarContract.Events.DESCRIPTION, "This is a description");
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "My House");
        intent.putExtra(CalendarContract.Events.RRULE, "FREQ=YEARLY");

        Log.i(TAG,"adding event in calender --------");
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Log.i(TAG,"called call back function ....");
        switch (requestCode) {
            case 101:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG,"permission granted ============> ");
                } else {
                    Log.d(TAG,"permission still denied =====> ");
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
