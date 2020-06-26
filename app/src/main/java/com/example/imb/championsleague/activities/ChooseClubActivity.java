package com.example.imb.championsleague.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.imb.championsleague.R;
import com.example.imb.championsleague.adapters.ChooseFavoriteClubAdapter;
import com.example.imb.championsleague.databases.Database;
import com.example.imb.championsleague.models.ChooseClubModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChooseClubActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, ChooseFavoriteClubAdapter.FinishListener {
    @BindView(R.id.rView)
    RecyclerView rView;
    private List<ChooseClubModel> club = new ArrayList<>();
    private ChooseFavoriteClubAdapter adapter;
    @BindView(R.id.background)
    ImageView background;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_club);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);


        Glide.with(this).load(R.drawable.choose_activity_background).into(background);

        Cursor cursor = Database.getInstance().getClubAndLogoData();
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                club.add(ChooseClubModel.getInstance(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        adapter = new ChooseFavoriteClubAdapter(club);
        adapter.setListener(this);
        rView.setAdapter(adapter);
        rView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_clubs, menu);
        MenuItem item = menu.findItem(R.id.search);
        android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.toLowerCase();
        List<ChooseClubModel> newData = new ArrayList<>();
        for (ChooseClubModel model : club) {
            String clubName = model.getClubName();
            clubName = clubName.toLowerCase();
            if (clubName.contains(newText))
                newData.add(model);
        }
        adapter.setFilter(newData);
        return true;
    }

    @Override
    public void finishListener() {
        finish();
    }
}
