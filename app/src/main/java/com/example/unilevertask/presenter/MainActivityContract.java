package com.example.unilevertask.presenter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.unilevertask.model.CustomerDatabase;
import com.example.unilevertask.model.CustomerItem;

import java.util.ArrayList;

public interface MainActivityContract {
    interface View{
        ArrayList<CustomerItem>displayCustomers();
    }

    interface Presenter{
        void addCustomers(CustomerDatabase customerDatabase, SQLiteDatabase sqLiteDatabase , Context context);
    }
}
