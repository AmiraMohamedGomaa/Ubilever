package com.example.unilevertask.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.unilevertask.R;
import com.example.unilevertask.databinding.ActivityMainBinding;
import com.example.unilevertask.databinding.ActivityUserDetailsBinding;
import com.example.unilevertask.model.CustomerItem;

public class UserDetailsActivity extends AppCompatActivity {
    private ActivityUserDetailsBinding detailBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailBinding = ActivityUserDetailsBinding.inflate(getLayoutInflater());
        setContentView(detailBinding.getRoot());


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        detailBinding.userDetailNameTv.setText("Name: "+getCustomerData().getName());
        detailBinding.userDetailCodeTv.setText("code: "+getCustomerData().getCode());
        detailBinding.userDetailAddressTv.setText("Address: "+getCustomerData().getAddress());
        detailBinding.userDetailMobileTv.setText("Mobile Number: "+getCustomerData().getMobile());
        detailBinding.locationLinkBtn.setOnClickListener(view -> {
              Intent intent=new Intent(getApplicationContext(),MapActivity.class);
              startActivity(intent);
        });



    }

    public CustomerItem getCustomerData(){
        Intent intent=getIntent();
        CustomerItem customerItem=intent.getParcelableExtra("customer_item");
        return customerItem;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}