package com.example.imb.championsleague.models;

import android.database.Cursor;

public class CountriesModel {
    private String name;
    private byte[] flag;

    private CountriesModel(String name, byte[] flag) {
        this.name = name;
        this.flag = flag;
    }

    public static CountriesModel getInstance(Cursor cursor) {
        return new CountriesModel(cursor.getString(cursor.getColumnIndex("name")),
                cursor.getBlob(cursor.getColumnIndex("flag")));
    }

    public String getName() {
        return name;
    }

    public byte[] getFlag() {
        return flag;
    }
}
