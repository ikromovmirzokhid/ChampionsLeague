package com.example.imb.championsleague.databases;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

import com.example.imb.championsleague.models.ClubModel;
import com.example.imb.championsleague.models.MatchdayModel1;

import java.util.ArrayList;
import java.util.List;

public class Database extends DBHelper {
    @SuppressLint("StaticFieldLeak")
    private static Database database;


    private Database(Context context) {
        super(context, "Champions_League.db");
    }

    public static void init(Context context) {
        if (database == null)
            database = new Database(context);
    }

    public static Database getInstance() {
        return database;
    }


    public Cursor getAllData() {
        mDatabase = this.getReadableDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * from clubsData", null);
        cursor.moveToFirst();
        return cursor;
    }

    public Cursor getClubData(String clubName) {
        mDatabase = this.getReadableDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * from clubsData where name = " + "\"" + clubName + "\"", null);
        return cursor;
    }

    public List<ClubModel> getGroupDataList(String group) {
        List<ClubModel> list = new ArrayList<>();
        mDatabase = this.getReadableDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * from clubsData where cGroup =" + "\"" + group + "\"", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                list.add(ClubModel.getInstance(cursor));
            } while (cursor.moveToNext());
        }
        return list;
    }

    public Cursor getGroupData(String group) {
        mDatabase = this.getReadableDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * from clubsData where cGroup = " + group, null);
        return cursor;
    }

    public Cursor getSquadData(String club) {
        mDatabase = this.getReadableDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * from " + club, null);
        return cursor;
    }

    public Cursor getSquadDataByPosition(String club, String position) {
        mDatabase = this.getReadableDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * from " + club + " where position=" + "\"" + position + "\"", null);
        return cursor;
    }

    public Cursor getPlayerData(String club, String playerName) {
        mDatabase = this.getReadableDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * from " + club + " where name=" + "\"" + playerName + "\"", null);
        return cursor;
    }

    public void updateStatistics(int played, int scored, int conceded, int points, int win, int draw, int loss, String name) {
        mDatabase = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        Cursor cursor = mDatabase.rawQuery("Select * from clubsData where name=" + "\"" + name + "\"", null);
        cursor.moveToFirst();
        played += cursor.getInt(cursor.getColumnIndex("played"));
        scored += cursor.getInt(cursor.getColumnIndex("goalScored"));
        conceded += cursor.getInt(cursor.getColumnIndex("goalConceded"));
        points += cursor.getInt(cursor.getColumnIndex("points"));
        win += cursor.getInt(cursor.getColumnIndex("win"));
        draw += cursor.getInt(cursor.getColumnIndex("draw"));
        loss += cursor.getInt(cursor.getColumnIndex("loss"));
        values.put("played", played);
        values.put("goalScored", scored);
        values.put("goalConceded", conceded);
        values.put("points", points);
        values.put("win", win);
        values.put("draw", draw);
        values.put("loss", loss);

        mDatabase = this.getWritableDatabase();
        mDatabase.update("clubsData", values, "name=" + "\"" + name + "\"", null);
    }

    public void undoStatistics(int played, int scored, int conceded, int points, int win, int draw, int loss, String name) {
        mDatabase = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        Cursor cursor = mDatabase.rawQuery("Select * from clubsData where name=" + "\"" + name + "\"", null);
        cursor.moveToFirst();

        played = cursor.getInt(cursor.getColumnIndex("played")) - played;
        scored = cursor.getInt(cursor.getColumnIndex("goalScored")) - scored;
        conceded = cursor.getInt(cursor.getColumnIndex("goalConceded")) - conceded;
        points = cursor.getInt(cursor.getColumnIndex("points")) - points;
        win = cursor.getInt(cursor.getColumnIndex("win")) - win;
        draw = cursor.getInt(cursor.getColumnIndex("draw")) - draw;
        loss = cursor.getInt(cursor.getColumnIndex("loss")) - loss;

        values.put("played", played);
        values.put("goalScored", scored);
        values.put("goalConceded", conceded);
        values.put("points", points);
        values.put("win", win);
        values.put("draw", draw);
        values.put("loss", loss);

        mDatabase = this.getWritableDatabase();
        mDatabase.update("clubsData", values, "name=" + "\"" + name + "\"", null);
    }

    public Cursor getMatchesData(int matchday) {
        mDatabase = this.getReadableDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * from matchday_" + matchday, null);
        return cursor;
    }

    public List<MatchdayModel1> getMatchesDataList(int matchday) {
        List<MatchdayModel1> data = new ArrayList<>();
        mDatabase = this.getReadableDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * from matchday_" + matchday, null);
        cursor.moveToFirst();
        do {
            data.add(MatchdayModel1.getInstance(cursor));
        } while (cursor.moveToNext());
        return data;
    }

    public void updateStatus(int id, int matchday, String goalsOfClub1, String goalsOfClub2, String status) {
        mDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("goalsOfClub1", goalsOfClub1);
        values.put("goalsOfClub2", goalsOfClub2);
        values.put("status", status);
        mDatabase.update("matchday_" + matchday, values, "id=" + id, null);
    }

    public Cursor getClubAndLogoData() {
        mDatabase = this.getReadableDatabase();
        Cursor cursor = mDatabase.rawQuery("select name, logo from clubsData", null);
        return cursor;
    }

    public Cursor getCountryName(String clubCountry) {
        mDatabase = this.getReadableDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * from countries where name=" + "\"" + clubCountry + "\"", null);
        return cursor;
    }

    public Cursor getClubMatches(String clubName, int matchday) {
        mDatabase = this.getReadableDatabase();
        Cursor cursor = mDatabase.rawQuery("Select * from matchday_" + matchday + " where club_1" + "=" + "\"" + clubName + "\"", null);
        if (cursor.getCount() == 0) {
            cursor = mDatabase.rawQuery("Select * from matchday_" + matchday + " where club_2" + "=" + "\"" + clubName + "\"", null);
        }
        return cursor;
    }

    public byte[] getClubLogo(String clubName) {
        mDatabase = this.getReadableDatabase();
        Cursor cursor = mDatabase.rawQuery("Select logo from clubsData where name=" + "\"" + clubName + "\"", null);
        cursor.moveToFirst();
        return cursor.getBlob(cursor.getColumnIndex("logo"));
    }

    public Cursor getClubLogoCursor(String clubName) {
        mDatabase = this.getReadableDatabase();
        Cursor cursor = mDatabase.rawQuery("Select logo from clubsData where name=" + "\"" + clubName + "\"", null);
        return cursor;
    }

    public byte[] getClubNationality(String nation) {
        mDatabase = this.getReadableDatabase();
        Cursor cursor = mDatabase.rawQuery("Select flag from countries where name=" + "\"" + nation + "\"", null);
        cursor.moveToNext();
        return cursor.getBlob(cursor.getColumnIndex("flag"));
    }

}
