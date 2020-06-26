package com.example.imb.championsleague.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.imb.championsleague.R;
import com.example.imb.championsleague.activities.MainActivity;
import com.example.imb.championsleague.adapters.MatchesFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MatchesDrawersFragment extends android.support.v4.app.Fragment {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.matchdayTitle)
    TabLayout matchdayTitle;
    @BindView(R.id.vPager)
    ViewPager viewPager;
    private List<String> title = new ArrayList<>();
    private MatchesFragmentAdapter adapter;
    private int[] matchday = new int[6];

    public MatchesDrawersFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.matches_and_drawers_view, container, false);
        ButterKnife.bind(this, v);

        ((MainActivity) getActivity()).setToolbar(toolbar);
        title.clear();
        for (int i = 1; i <= 6; i++) {
            title.add("Matchday " + i);
            matchday[i - 1] = i;
        }
        matchdayTitle.setupWithViewPager(viewPager);

        adapter = new MatchesFragmentAdapter(getChildFragmentManager(), title, matchday);
        viewPager.setAdapter(adapter);

        return v;
    }
}
