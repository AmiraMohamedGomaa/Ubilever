package com.example.unilevertask.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.unilevertask.adapters.CustomerRecycleAdapter;
import com.example.unilevertask.databinding.ActivityMainBinding;
import com.example.unilevertask.model.CustomerDatabase;
import com.example.unilevertask.model.CustomerItem;
import com.example.unilevertask.presenter.ItemClickListner;
import com.example.unilevertask.presenter.MainActivityContract;
import com.example.unilevertask.presenter.MainActivityPresenter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ItemClickListner , MainActivityContract.View {
    CustomerDatabase customerDatabase;
    SQLiteDatabase sqLiteDatabase;
    private ActivityMainBinding binding;
    MainActivityContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //add data to database only once
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);

        if (firstStart) {
            presenter=new MainActivityPresenter();
            presenter.addCustomers(customerDatabase,sqLiteDatabase,this);
        }

         prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();



        CustomerRecycleAdapter customerRecycleAdapter=new CustomerRecycleAdapter(this,displayCustomers());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.customerRv.setLayoutManager(layoutManager);
        binding.customerRv.setHasFixedSize(true);
        binding.customerRv.setAdapter(customerRecycleAdapter);

    }




    @Override
    public void onClick(int position) {
       CustomerItem customerItem= displayCustomers().get(position);
        Intent intent=new Intent(getApplicationContext(),UserDetailsActivity.class);
        intent.putExtra("customer_item",customerItem);
        startActivity(intent);
    }

    @Override
    public ArrayList<CustomerItem> displayCustomers() {
        customerDatabase=new CustomerDatabase( this );
        sqLiteDatabase=customerDatabase.getReadableDatabase();
        Cursor customerCursor = sqLiteDatabase.rawQuery("SELECT * FROM customer", null);

        ArrayList<CustomerItem> customerItemArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (customerCursor.moveToFirst()) {
            do {
                //adding the data from cursor to our array list.
                customerItemArrayList.add(new CustomerItem(customerCursor.getString(1),
                        customerCursor.getString(3),
                        customerCursor.getInt(0),
                        customerCursor.getInt(2)));
            } while (customerCursor.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        customerCursor.close();
        return customerItemArrayList;

    }

}