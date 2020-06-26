package com.example.imb.championsleague.fragments;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.imb.championsleague.R;
import com.example.imb.championsleague.adapters.SquadListAdapter;
import com.example.imb.championsleague.databases.Database;
import com.example.imb.championsleague.models.ClubSquadModel;
import com.example.imb.championsleague.models.PositionModel;
import com.example.imb.championsleague.models.SquadViewHeadModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("ValidFragment")
public class ClubSquadFragment extends Fragment {
    @BindView(R.id.squadList)
    RecyclerView squadListView;
    private List<SquadViewHeadModel> squadData = new ArrayList<>();
    private Cursor allDefenders, allGoalkeepers, allMidfielders, allForwards, coach;
    private SquadListAdapter adapter;
    private String clubName;

    @SuppressLint("ValidFragment")
    private ClubSquadFragment(String clubName, String squadName) {
        this.clubName = clubName;
        allGoalkeepers = Database.getInstance().getSquadDataByPosition(squadName, "Goalkeeper");
        allDefenders = Database.getInstance().getSquadDataByPosition(squadName, "Defender");
        allMidfielders = Database.getInstance().getSquadDataByPosition(squadName, "Midfielder");
        allForwards = Database.getInstance().getSquadDataByPosition(squadName, "Forward");
        coach = Database.getInstance().getSquadDataByPosition(squadName, "Coach");

    }

    public static ClubSquadFragment getInstance(String clubName, String squadName) {
        return new ClubSquadFragment(clubName, squadName);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.squad_view, container, false);

        ButterKnife.bind(this, v);

        allGoalkeepers.moveToFirst();
        allDefenders.moveToFirst();
        allMidfielders.moveToFirst();
        allForwards.moveToFirst();
        coach.moveToFirst();

        squadData.clear();

        squadData.add(new PositionModel("Goalkeeper"));
        do {
            squadData.add(ClubSquadModel.getInstance(allGoalkeepers));
        } while (allGoalkeepers.moveToNext());
        squadData.add(new PositionModel("Defenders"));
        do {
            squadData.add(ClubSquadModel.getInstance(allDefenders));
        } while (allDefenders.moveToNext());
        squadData.add(new PositionModel("Midfielders"));
        do {
            squadData.add(ClubSquadModel.getInstance(allMidfielders));
        } while (allMidfielders.moveToNext());
        squadData.add(new PositionModel("Forwards"));
        do {
            squadData.add(ClubSquadModel.getInstance(allForwards));
        } while (allForwards.moveToNext());
        squadData.add(new PositionModel("Coach"));
        squadData.add(ClubSquadModel.getInstance(coach));

        adapter = new SquadListAdapter(squadData, clubName);
        squadListView.setAdapter(adapter);
        squadListView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return v;
    }
}
