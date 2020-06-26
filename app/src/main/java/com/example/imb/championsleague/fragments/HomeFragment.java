package com.example.imb.championsleague.fragments;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.imb.championsleague.R;
import com.example.imb.championsleague.activities.MainActivity;
import com.example.imb.championsleague.databases.Database;
import com.example.imb.championsleague.models.ClubModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {
    @BindView(R.id.logoInToolbar)
    ImageView logoInToolbar;
    @BindView(R.id.toolbarMain)
    Toolbar toolbar;
    private BottomNavigationView bNavigation;
    private static Cursor cursor;
    private static ClubModel club;
    private FragmentTransaction mainTransaction;

    @SuppressLint("ValidFragment")
    private HomeFragment() {
    }

    public static HomeFragment getInstance(String name) {
        cursor = Database.getInstance().getClubData(name);
        cursor.moveToFirst();
        club = ClubModel.getInstance(cursor);
        return new HomeFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_fragment_view, container, false);
        ButterKnife.bind(this, v);
        ((MainActivity) getActivity()).setToolbar(toolbar);
        //Glide.with(getActivity()).load(R.drawable.main_background).into(background);
        Glide.with(getActivity()).load(club.getLogo()).into(logoInToolbar);
        toolbar.setTitle(club.getName());

        FavoriteClubGroupFragment clubGroup = FavoriteClubGroupFragment.getInstance(club.getGroup(), getActivity());
        FavoriteClubMatches clubMatches = FavoriteClubMatches.getInstance(club.getName());
        ClubSquadFragment squadFragment = ClubSquadFragment.getInstance(club.getName(), club.getSquad());
        ClubHistoryFragment historyFragment = ClubHistoryFragment.getInstance(club);
        mainTransaction = getFragmentManager().beginTransaction();
        mainTransaction.replace(R.id.container, clubGroup);
        mainTransaction.commit();

        bNavigation = v.findViewById(R.id.navigationView);
        bNavigation.setOnNavigationItemSelectedListener(item -> {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            if (item.getItemId() == R.id.group) {

                transaction.replace(R.id.container, clubGroup);
            } else if (item.getItemId() == R.id.matches) {
                transaction.replace(R.id.container, clubMatches);
            } else if (item.getItemId() == R.id.squad) {
                transaction.replace(R.id.container, squadFragment);
            } else if (item.getItemId() == R.id.history)
                transaction.replace(R.id.container, historyFragment);
            transaction.commit();
            return true;
        });
        return v;
    }

}
