package com.example.imb.championsleague.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.imb.championsleague.R;
import com.example.imb.championsleague.databases.Database;
import com.example.imb.championsleague.fragments.ClubsFragmentActivity;
import com.example.imb.championsleague.fragments.HomeFragment;
import com.example.imb.championsleague.fragments.MatchdayFragment;
import com.example.imb.championsleague.fragments.MatchesDrawersFragment;
import com.example.imb.championsleague.fragments.StandingsFragmentActivity;
import com.example.imb.championsleague.models.ClubModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.main_drawer_layout)
    DrawerLayout dLayout;
    @BindView(R.id.nView)
    NavigationView nView;
    ActionBarDrawerToggle mToggle;
    android.support.v4.app.FragmentTransaction transaction, navigationTransaction;
    private SharedPreferences sPref;
    private ClubsFragmentActivity clubsFragment;
    private HomeFragment homeFragment;
    private StandingsFragmentActivity standingsFragment;
    private MatchesDrawersFragment matchesFragment;
    private MatchdayFragment matchdayFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Database.init(this);

        sPref = getSharedPreferences("club", MODE_PRIVATE);
        String clubName = sPref.getString("clubName", "");
        if (clubName.equals("")) {
            Intent intent = new Intent(this, ChooseClubActivity.class);
            startActivity(intent);
        } else {
            clubsFragment = new ClubsFragmentActivity();
            homeFragment = HomeFragment.getInstance(clubName);
            standingsFragment = new StandingsFragmentActivity();
            matchesFragment = new MatchesDrawersFragment();
            matchdayFragment = MatchdayFragment.getInstance(1);
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.mainContainer, homeFragment);

            nView.getMenu().getItem(0).setChecked(true);
            transaction.commit();
        }

        nView.setNavigationItemSelectedListener(item -> {
            navigationTransaction = getSupportFragmentManager().beginTransaction();
            if (item.getItemId() == R.id.clubs) {
                navigationTransaction.replace(R.id.mainContainer, clubsFragment);
                dLayout.closeDrawers();
            } else if (item.getItemId() == R.id.home) {
                navigationTransaction.replace(R.id.mainContainer, homeFragment);
                dLayout.closeDrawers();
            } else if (item.getItemId() == R.id.standings) {
                navigationTransaction.replace(R.id.mainContainer, standingsFragment);
                dLayout.closeDrawers();

            } else if (item.getItemId() == R.id.matches) {
                navigationTransaction.replace(R.id.mainContainer, matchesFragment);
                dLayout.closeDrawers();
            } else if (item.getItemId() == R.id.change) {
                Intent intent = new Intent(this, ChooseClubActivity.class);
                startActivity(intent);
            }
            item.setChecked(true);
            navigationTransaction.commit();
            return true;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.change_favourite_team) {
            Intent intent = new Intent(this, ChooseClubActivity.class);
            startActivity(intent);
        }
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    public void setToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        mToggle = new ActionBarDrawerToggle(this, dLayout, R.string.open, R.string.close);
        dLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
