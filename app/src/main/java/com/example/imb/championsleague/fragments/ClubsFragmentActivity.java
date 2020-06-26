package com.example.imb.championsleague.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.imb.championsleague.R;
import com.example.imb.championsleague.activities.MainActivity;
import com.example.imb.championsleague.adapters.ClubListAdapter;
import com.example.imb.championsleague.databases.Database;
import com.example.imb.championsleague.models.ChooseClubModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClubsFragmentActivity extends Fragment {
    @BindView(R.id.clubsList)
    RecyclerView clubsListView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.background)
    ImageView background;
    private ClubListAdapter adapter;
    private List<ChooseClubModel> clubData = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View v = inflater.inflate(R.layout.clubs_view, container, false);
        ButterKnife.bind(this, v);

        ((MainActivity) getActivity()).setToolbar(toolbar);
        Glide.with(getActivity()).load(R.drawable.clubs_background).into(background);

        clubData.clear();

        Cursor cursor = Database.getInstance().getClubAndLogoData();
        cursor.moveToFirst();
        do {
            clubData.add(ChooseClubModel.getInstance(cursor));
        } while (cursor.moveToNext());
        adapter = new ClubListAdapter(clubData);
        clubsListView.setAdapter(adapter);
        clubsListView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.search_clubs, menu);
        MenuItem item = menu.findItem(R.id.search);
        android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText = newText.toLowerCase();
                List<ChooseClubModel> clubList = new ArrayList<>();
                for (ChooseClubModel club : clubData) {
                    String clubName = club.getClubName();
                    clubName = clubName.toLowerCase();
                    if (clubName.contains(newText))
                        clubList.add(club);
                }
                adapter.setFilter(clubList);
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

}
