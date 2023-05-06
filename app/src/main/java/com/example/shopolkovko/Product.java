package com.example.shopolkovko;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Product extends ArrayList<Parcelable> implements Parcelable {
        String name;
        int price;
        int image;

        String number;
        boolean box;
        Product(String _describe, String _ability, int _price, int _image, boolean _box){
            name = _describe;
            number = _ability;
            price = _price;
            image = _image;
            box = _box;
        }

    protected Product(Parcel in) {
        name = in.readString();
        price = in.readInt();
        image = in.readInt();
        number = in.readString();
        box = in.readByte() != 0;
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(price);
        parcel.writeInt(image);
        parcel.writeString(number);
        parcel.writeByte((byte) (box ? 1 : 0));
    }
}
