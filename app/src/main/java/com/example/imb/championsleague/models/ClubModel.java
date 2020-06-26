package com.example.imb.championsleague.models;

import android.database.Cursor;

public class ClubModel {
    private int id, title, played, goalScored, ranking, goalConceded, points, win, draw, loss;
    private String name, group, squad, top_scorer, most_appearance, country, textOfPhoto;
    private byte[] scorerPhoto, logo, photo, appearance_photo;

    private ClubModel(int id, int title, int played, int goalScored, int ranking, int goalConceded, int points, int win, int draw, int loss, String name, String group, String squad, String top_scorer, String most_appearance, String country, String textOfPhoto, byte[] scorerPhoto, byte[] appearance_photo, byte[] logo, byte[] photo) {
        this.id = id;
        this.title = title;
        this.played = played;
        this.goalScored = goalScored;
        this.ranking = ranking;
        this.goalConceded = goalConceded;
        this.points = points;
        this.win = win;
        this.draw = draw;
        this.loss = loss;
        this.name = name;
        this.group = group;
        this.squad = squad;
        this.top_scorer = top_scorer;
        this.most_appearance = most_appearance;
        this.country = country;
        this.textOfPhoto = textOfPhoto;
        this.scorerPhoto = scorerPhoto;
        this.logo = logo;
        this.photo = photo;
        this.appearance_photo = appearance_photo;
    }

    public static ClubModel getInstance(Cursor cursor) {
        return new ClubModel(cursor.getInt(cursor.getColumnIndex("id")),
                cursor.getInt(cursor.getColumnIndex("title")),
                cursor.getInt(cursor.getColumnIndex("played")),
                cursor.getInt(cursor.getColumnIndex("goalScored")),
                cursor.getInt(cursor.getColumnIndex("ranking")),
                cursor.getInt(cursor.getColumnIndex("goalConceded")),
                cursor.getInt(cursor.getColumnIndex("points")),
                cursor.getInt(cursor.getColumnIndex("win")),
                cursor.getInt(cursor.getColumnIndex("draw")),
                cursor.getInt(cursor.getColumnIndex("loss")),
                cursor.getString(cursor.getColumnIndex("name")),
                cursor.getString(cursor.getColumnIndex("cGroup")),
                cursor.getString(cursor.getColumnIndex("squad")),
                cursor.getString(cursor.getColumnIndex("top_scorer")),
                cursor.getString(cursor.getColumnIndex("most_appearance")),
                cursor.getString(cursor.getColumnIndex("country")),
                cursor.getString(cursor.getColumnIndex("textOfPhoto")),
                cursor.getBlob(cursor.getColumnIndex("scorer_photo")),
                cursor.getBlob(cursor.getColumnIndex("appearance_photo")),
                cursor.getBlob(cursor.getColumnIndex("logo")),
                cursor.getBlob(cursor.getColumnIndex("photo"))
        );
    }

    public int getId() {
        return id;
    }

    public int getTitle() {
        return title;
    }

    public int getPlayed() {
        return played;
    }

    public int getGoalScored() {
        return goalScored;
    }

    public int getRanking() {
        return ranking;
    }

    public int getGoalConceded() {
        return goalConceded;
    }

    public int getPoints() {
        return points;
    }

    public int getWin() {
        return win;
    }

    public int getDraw() {
        return draw;
    }

    public int getLoss() {
        return loss;
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public String getSquad() {
        return squad;
    }

    public String getTop_scorer() {
        return top_scorer;
    }

    public String getMost_appearance() {
        return most_appearance;
    }

    public String getCountry() {
        return country;
    }

    public String getTextOfPhoto() {
        return textOfPhoto;
    }

    public byte[] getScorerPhoto() {
        return scorerPhoto;
    }

    public byte[] getLogo() {
        return logo;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public byte[] getAppearance_photo() {
        return appearance_photo;
    }
}
