package com.example.unilevertask.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.example.unilevertask.adapters.CustomerRecycleAdapter;
import com.example.unilevertask.databinding.ActivityMainBinding;
import com.example.unilevertask.model.CustomerDatabase;
import com.example.unilevertask.model.CustomerItem;
import com.example.unilevertask.presenter.ItemClickListner;
import com.example.unilevertask.presenter.MainActivityContract;
import com.example.unilevertask.presenter.MainActivityPresenter;
import com.example.unilevertask.repos.CustomerReposetory;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClickListner , MainActivityContract.View {
    CustomerDatabase customerDatabase;
    SQLiteDatabase sqLiteDatabase;
    private ActivityMainBinding binding;
    MainActivityContract.Presenter presenter;
    CustomerReposetory customerReposetory;

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
       CustomerItem customerItem= displayCustomers().getValue().get(position);
        Intent intent=new Intent(getApplicationContext(),UserDetailsActivity.class);
        intent.putExtra("customer_item",customerItem);
        startActivity(intent);
    }

    @Override
    public LiveData<List<CustomerItem>> displayCustomers() {
         customerReposetory=new CustomerReposetory();
       // Log.w("hi",customerReposetory.displayCustomersFromDB(getApplicationContext()).getValue().get(0).getAddress());
        //Toast.makeText(getApplicationContext(), "hi"+customerReposetory.displayCustomersFromDB(getApplicationContext()).size(), Toast.LENGTH_SHORT).show();
        return customerReposetory.displayCustomersFromDB(getApplicationContext());

    }

}