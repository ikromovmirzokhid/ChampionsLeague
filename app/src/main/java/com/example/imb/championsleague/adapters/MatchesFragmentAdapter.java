package com.example.imb.championsleague.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.imb.championsleague.fragments.FavoriteClubGroupFragment;
import com.example.imb.championsleague.fragments.FavoriteClubMatches;
import com.example.imb.championsleague.fragments.MatchdayFragment;

import java.util.List;

public class MatchesFragmentAdapter extends FragmentPagerAdapter {
    private List<String> title;
    private int[] matchday;

    public MatchesFragmentAdapter(FragmentManager fm, List<String> title, int[] matchday) {
        super(fm);
        this.matchday = matchday;
        this.title = title;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return MatchdayFragment.getInstance(matchday[position]);
        } else if (position == 1) {
            return MatchdayFragment.getInstance(matchday[position]);
        } else if (position == 2) {
            return MatchdayFragment.getInstance(matchday[position]);
        } else if (position == 3) {
            return MatchdayFragment.getInstance(matchday[position]);
        } else if (position == 4) {
            return MatchdayFragment.getInstance(matchday[position]);
        } else if (position == 5) {
            return MatchdayFragment.getInstance(matchday[position]);
        } else if (position == 6) {
            return MatchdayFragment.getInstance(matchday[position]);
        }

        return null;
    }

    @Override
    public int getCount() {
        return title.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }
}
