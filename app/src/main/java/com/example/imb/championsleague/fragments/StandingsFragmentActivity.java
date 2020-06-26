package com.example.imb.championsleague.fragments;

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
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.imb.championsleague.R;
import com.example.imb.championsleague.activities.MainActivity;
import com.example.imb.championsleague.adapters.StandingsAdapter;
import com.example.imb.championsleague.databases.Database;
import com.example.imb.championsleague.models.ClubModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StandingsFragmentActivity extends Fragment {
    @BindView(R.id.toolbar)
    android.support.v7.widget.Toolbar toolbar;
    @BindView(R.id.background)
    ImageView background;
    @BindView(R.id.groupsList)
    RecyclerView groupListView;
    private List<List<ClubModel>> allGroupsData = new ArrayList<>();
    private List<ClubModel> groupData = new ArrayList<>();
    private StandingsAdapter adapter;

    public StandingsFragmentActivity() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.standings_view, container, false);
        ButterKnife.bind(this, v);
        ((MainActivity) getActivity()).setToolbar(toolbar);
        Glide.with(getActivity()).load(R.drawable.clubs_background).into(background);

        for (int i = 0; i < 8; i++) {
            groupData = new ArrayList<>();
            groupData = Database.getInstance().getGroupDataList("" + (char) (65 + i));
            allGroupsData.add(groupData);
        }
        adapter = new StandingsAdapter(allGroupsData);
        groupListView.setAdapter(adapter);
        groupListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return v;
    }
}
