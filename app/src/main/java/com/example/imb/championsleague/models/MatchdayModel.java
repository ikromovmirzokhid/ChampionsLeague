package com.example.imb.championsleague.models;

import android.database.Cursor;

import com.example.imb.championsleague.databases.Database;

public class MatchdayModel {
    private int id, matchday;
    private String club1, club2, date, time, status, goalsOfClub1, goalsOfClub2;
    private byte[] club1Logo, club2Logo;

    private MatchdayModel(int id, String goalsOfClub1, String goalsOfClub2, String club1, String club2, String date, String time, String status, byte[] logo1, byte[] logo2, int matchday) {
        this.id = id;
        this.goalsOfClub1 = goalsOfClub1;
        this.goalsOfClub2 = goalsOfClub2;
        this.club1 = club1;
        this.club2 = club2;
        this.date = date;
        this.time = time;
        this.status = status;
        club1Logo = logo1;
        club2Logo = logo2;
        this.matchday = matchday;
    }

    public static MatchdayModel getInstance(Cursor cursor) {
        return new MatchdayModel(cursor.getInt(cursor.getColumnIndex("id")),
                cursor.getString(cursor.getColumnIndex("goalsOfClub1")),
                cursor.getString(cursor.getColumnIndex("goalsOfClub2")),
                cursor.getString(cursor.getColumnIndex("club_1")),
                cursor.getString(cursor.getColumnIndex("club_2")),
                cursor.getString(cursor.getColumnIndex("date")),
                cursor.getString(cursor.getColumnIndex("time")),
                cursor.getString(cursor.getColumnIndex("status")),
                Database.getInstance().getClubLogo(cursor.getString(cursor.getColumnIndex("club_1"))),
                Database.getInstance().getClubLogo(cursor.getString(cursor.getColumnIndex("club_2"))),
                cursor.getInt(cursor.getColumnIndex("matchday")));
    }

    public int getId() {
        return id;
    }

    public String getGoalsOfClub1() {
        return goalsOfClub1;
    }

    public String getGoalsOfClub2() {
        return goalsOfClub2;
    }

    public String getClub1() {
        return club1;
    }

    public String getClub2() {
        return club2;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getStatus() {
        return status;
    }

    public byte[] getClub1Logo() {
        return club1Logo;
    }

    public byte[] getClub2Logo() {
        return club2Logo;
    }

    public int getMatchday() {
        return matchday;
    }
}
