package com.example.unilevertask.presenter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;
import com.example.unilevertask.model.CustomerDatabase;
import com.example.unilevertask.model.CustomerItem;
import java.util.ArrayList;

public class MainActivityPresenter implements MainActivityContract.Presenter{

    @Override
    public void addCustomers(CustomerDatabase customerDatabase, SQLiteDatabase sqLiteDatabase, Context context) {
      customerDatabase =CustomerDatabase.getDatabaseInstance(context);
      sqLiteDatabase=customerDatabase.getWritableDatabase();
      for(int i=0;i<getCustomers().size();i++) {
        sqLiteDatabase.execSQL("insert into customer values(?,?,?,?);", new Object[]{getCustomers().get(i).getCode(),getCustomers().get(i).getName(),getCustomers().get(i).getMobile(),getCustomers().get(i).getAddress()});
      }
      Toast.makeText( context,"Saved data",Toast.LENGTH_LONG ).show();
    }

    public ArrayList<CustomerItem>getCustomers(){
        ArrayList<CustomerItem>getCutomerItems=new ArrayList<>();
        getCutomerItems.add(new CustomerItem("Amira","cairo",1234,01002055425));
        getCutomerItems.add(new CustomerItem("Ali","Alex",1785,01102675425));
        getCutomerItems.add(new CustomerItem("Salma","Giza",1234,01067055425));
        getCutomerItems.add(new CustomerItem("Mohamed","Sohag",1234,01272055425));
        getCutomerItems.add(new CustomerItem("Maysa","Luxor",1234,01552055425));

        return getCutomerItems;
    }
}
