package com.example.imb.championsleague.models;

import android.database.Cursor;

public class ClubSquadModel extends SquadViewHeadModel {
    private int id, number, weigth, height, age;
    private String name, country, position;
    private byte[] image;

    private ClubSquadModel(int id, int number, int weigth, int height, int age, String name, String country, String position, byte[] image) {
        this.id = id;
        this.number = number;
        this.weigth = weigth;
        this.height = height;
        this.age = age;
        this.name = name;
        this.country = country;
        this.position = position;
        this.image = image;
    }

    public static ClubSquadModel getInstance(Cursor cursor) {
        return new ClubSquadModel(cursor.getInt(cursor.getColumnIndex("id")),
                cursor.getInt(cursor.getColumnIndex("number")),
                cursor.getInt(cursor.getColumnIndex("weight")),
                cursor.getInt(cursor.getColumnIndex("height")),
                cursor.getInt(cursor.getColumnIndex("age")),
                cursor.getString(cursor.getColumnIndex("name")),
                cursor.getString(cursor.getColumnIndex("country")),
                cursor.getString(cursor.getColumnIndex("position")),
                cursor.getBlob(cursor.getColumnIndex("photo")));
    }

    public int getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public int getWeigth() {
        return weigth;
    }

    public int getHeight() {
        return height;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getPosition() {
        return position;
    }

    public byte[] getImage() {
        return image;
    }

    @Override
    public int getType() {
        return 2;
    }
}
