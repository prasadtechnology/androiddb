package com.example.prasad.dbapplication;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.prasad.dbapplication.ContractClass.ContractClass.CalenderEntry;

import com.example.prasad.dbapplication.db.CalednerDAO;
import com.example.prasad.dbapplication.models.Calender;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CalednerDAO calednerDAO = new CalednerDAO(getApplicationContext());

        Log.i(TAG,"--------------------");

        System.out.println(calednerDAO.getCalednderEvent(2));

    }
}
