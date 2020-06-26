package com.example.imb.championsleague.models;

import android.database.Cursor;

public class ChooseClubModel {
    private String clubName;
    private byte[] clubImage;

    private ChooseClubModel(String clubName, byte[] clubImage) {
        this.clubName = clubName;
        this.clubImage = clubImage;
    }

    public static ChooseClubModel getInstance(Cursor cursor) {
        return new ChooseClubModel(cursor.getString(cursor.getColumnIndex("name")),
                cursor.getBlob(cursor.getColumnIndex("logo")));
    }

    public String getClubName() {
        return clubName;
    }

    public byte[] getClubImage() {
        return clubImage;
    }
}
