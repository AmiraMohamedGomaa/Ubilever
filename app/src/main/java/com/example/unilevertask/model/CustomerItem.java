package com.example.unilevertask.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CustomerItem implements Parcelable {
    String name,address;
    int code,mobile;

    public CustomerItem(String name, String address, int code, int mobile) {
        this.name = name;
        this.address = address;
        this.code = code;
        this.mobile = mobile;
    }

    protected CustomerItem(Parcel in) {
        name = in.readString();
        address = in.readString();
        code = in.readInt();
        mobile = in.readInt();
    }

    public static final Creator<CustomerItem> CREATOR = new Creator<CustomerItem>() {
        @Override
        public CustomerItem createFromParcel(Parcel in) {
            return new CustomerItem(in);
        }

        @Override
        public CustomerItem[] newArray(int size) {
            return new CustomerItem[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }


    public int getCode() {
        return code;
    }

    public int getMobile() {
        return mobile;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(address);
        parcel.writeInt(code);
        parcel.writeInt(mobile);
    }
}
