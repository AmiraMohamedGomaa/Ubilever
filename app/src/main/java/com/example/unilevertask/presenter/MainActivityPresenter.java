package com.example.unilevertask.presenter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.unilevertask.model.CustomerDatabase;
import com.example.unilevertask.model.CustomerItem;
import com.example.unilevertask.repos.CustomerReposetory;
import java.util.List;

public class MainActivityPresenter implements MainActivityContract.Presenter{
 LiveData<List<CustomerItem>>customerItems=getCustomers();

    @Override
    public void addCustomers(CustomerDatabase customerDatabase, SQLiteDatabase sqLiteDatabase, Context context) {
        CustomerReposetory customerReposetory=new CustomerReposetory();
        customerReposetory.addCustomersToDB(context,customerItems);

    }

    public LiveData<List<CustomerItem>>getCustomers(){
        LiveData<List<CustomerItem>>getCutomerItems = new MutableLiveData<>();
        getCutomerItems.getValue().add(new CustomerItem("Amira","cairo",1234,01002055425));
        getCutomerItems.getValue().add(new CustomerItem("Ali","Alex",1785,01102675425));
        getCutomerItems.getValue().add(new CustomerItem("Salma","Giza",1234,01067055425));
        getCutomerItems.getValue().add(new CustomerItem("Mohamed","Sohag",1234,01272055425));
        getCutomerItems.getValue().add(new CustomerItem("Maysa","Luxor",1234,01552055425));

        return getCutomerItems;
    }
}
