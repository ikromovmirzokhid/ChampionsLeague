package com.example.imb.championsleague.models;

public class PositionModel extends SquadViewHeadModel {
    private String position;

    public PositionModel(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public int getType() {
        return 1;
    }
}
