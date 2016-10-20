package com.example.aashish.mvvm_databinding_demo.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by aashish on 10/20/16.
 */

public class DemoModel implements Parcelable {

    @SerializedName("name")
    public String name;

    @SerializedName("address")
    public String address;

    @SerializedName("description")
    public String description;

    @SerializedName("age")
    public String age;

    @SerializedName("gender")
    public String gender;

    public boolean isEmpty() {
        if (name == null || name.isEmpty()
                && address == null || address.isEmpty()
                && description == null || description.isEmpty()
                && age == null || age.isEmpty()
                && gender == null || gender.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public DemoModel createFromParcel(Parcel in) {
            return new DemoModel(in);
        }

        @Override
        public Object[] newArray(int size) {
            return new DemoModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(address);
        parcel.writeString(age);
        parcel.writeString(gender);
    }

    public DemoModel(Parcel in) {
        name = in.readString();
        description = in.readString();
        address = in.readString();
        age = in.readString();
        gender = in.readString();
    }

    public DemoModel() {

    }
}
