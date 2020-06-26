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

import com.example.imb.championsleague.R;
import com.example.imb.championsleague.adapters.ClubMatchesAdapter;
import com.example.imb.championsleague.adapters.ClubMatchesAdapter1;
import com.example.imb.championsleague.databases.Database;
import com.example.imb.championsleague.models.MatchdayModel;
import com.example.imb.championsleague.models.MatchdayModel1;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("ValidFragment")
public class MatchdayFragment extends Fragment {
    @BindView(R.id.matchesList)
    RecyclerView matchesListView;
    private ClubMatchesAdapter1 adapter;
    private List<MatchdayModel1> matchdayData = new ArrayList<>();
    private Cursor cursor;
    private int matchday;

    @SuppressLint("ValidFragment")
    private MatchdayFragment(int matchday) {
        this.matchday = matchday;

    }

    public static MatchdayFragment getInstance(int matchday) {
        return new MatchdayFragment(matchday);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.matches_viewpager_view, container, false);
        ButterKnife.bind(this, v);

        cursor = Database.getInstance().getMatchesData(matchday);

        cursor.moveToFirst();
        matchdayData.clear();
        do {
            matchdayData.add(MatchdayModel1.getInstance(cursor));
        } while (cursor.moveToNext());

        adapter = new ClubMatchesAdapter1(matchdayData);

        matchesListView.setAdapter(adapter);
        matchesListView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return v;
    }
}
