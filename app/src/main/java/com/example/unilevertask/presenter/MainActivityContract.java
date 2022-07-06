package com.example.unilevertask.presenter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.lifecycle.LiveData;

import com.example.unilevertask.model.CustomerDatabase;
import com.example.unilevertask.model.CustomerItem;

import java.util.List;

public interface MainActivityContract {
    interface View{
        LiveData<List<CustomerItem>> displayCustomers();
    }

    interface Presenter{
        void addCustomers(CustomerDatabase customerDatabase, SQLiteDatabase sqLiteDatabase , Context context);
    }
}
