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

import com.bumptech.glide.Glide;
import com.example.imb.championsleague.R;
import com.example.imb.championsleague.adapters.ClubMatchesAdapter;
import com.example.imb.championsleague.databases.Database;
import com.example.imb.championsleague.models.MatchdayModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("ValidFragment")
public class FavoriteClubMatches extends Fragment {
    @BindView(R.id.list)
    RecyclerView matchesView;
    List<MatchdayModel> matches = new ArrayList<>();
    private String clubName;
    private ClubMatchesAdapter adapter;

    @SuppressLint("ValidFragment")
    private FavoriteClubMatches(String clubName) {
        this.clubName = clubName;
    }

    public static FavoriteClubMatches getInstance(String clubName) {
        return new FavoriteClubMatches(clubName);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.favorite_club_matches_view, container, false);
        ButterKnife.bind(this, v);


        matches.clear();
        Cursor cursor;
        for (int i = 1; i <= 6; i++) {
            cursor = Database.getInstance().getClubMatches(clubName, i);
            cursor.moveToFirst();
            matches.add(MatchdayModel.getInstance(cursor));
        }
        adapter = new ClubMatchesAdapter(matches);
        matchesView.setAdapter(adapter);
        matchesView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return v;
    }
}
