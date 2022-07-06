package com.example.unilevertask.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unilevertask.databinding.CustomerRecycleItemBinding;
import com.example.unilevertask.model.CustomerItem;
import com.example.unilevertask.presenter.ItemClickListner;

import java.util.ArrayList;
import java.util.List;

public class CustomerRecycleAdapter extends RecyclerView.Adapter<CustomerRecycleAdapter.CustomerViewHolder> {
 ItemClickListner itemClickListner;
 LiveData<List<CustomerItem>> customerItems;

    public CustomerRecycleAdapter(ItemClickListner itemClickListner, LiveData<List<CustomerItem>> customerItems) {
        this.itemClickListner = itemClickListner;
        this.customerItems = customerItems;
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomerViewHolder(CustomerRecycleItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
           CustomerItem customerItem=customerItems.getValue().get(position);
           holder.customerRecycleItemBinding.customerCodeTv.setText(String.valueOf(customerItem.getCode()));
           holder.customerRecycleItemBinding.customerNameTv.setText(customerItem.getName());
           holder.itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   itemClickListner.onClick(position);
               }
           });
    }

    @Override
    public int getItemCount() {
        return customerItems.getValue().size();
    }

    public class CustomerViewHolder extends RecyclerView.ViewHolder {
        private CustomerRecycleItemBinding customerRecycleItemBinding;

        public CustomerViewHolder(CustomerRecycleItemBinding customerRecycleItemBinding) {
            super(customerRecycleItemBinding.getRoot());
            this.customerRecycleItemBinding=customerRecycleItemBinding;
        }


    }
}
