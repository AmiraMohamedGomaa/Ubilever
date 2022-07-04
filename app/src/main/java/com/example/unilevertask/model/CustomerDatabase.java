package com.example.unilevertask.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CustomerDatabase extends SQLiteOpenHelper {
    public CustomerDatabase(@Nullable Context context) {
        super(context, "customer_dp", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
         sqLiteDatabase.execSQL("create table customer(code number(10),name varchar(30),mobile number(11),address varchar(50));" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
