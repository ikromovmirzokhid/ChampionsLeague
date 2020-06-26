package com.example.imb.championsleague.diffUtil;

import android.support.v7.util.DiffUtil;

import com.example.imb.championsleague.models.MatchdayModel1;

import java.util.List;

public class MatchesResultSaveUtil extends DiffUtil.Callback {
    private List<MatchdayModel1> oldData;
    private List<MatchdayModel1> newData;

    public MatchesResultSaveUtil(List<MatchdayModel1> oldData, List<MatchdayModel1> newData) {
        this.oldData = oldData;
        this.newData = newData;
    }

    @Override
    public int getOldListSize() {
        return oldData.size();
    }

    @Override
    public int getNewListSize() {
        return newData.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldData.get(oldItemPosition).getGoalsOfClub1().equals(newData.get(newItemPosition).getGoalsOfClub1());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldData.get(oldItemPosition).getGoalsOfClub2().equals(newData.get(newItemPosition).getGoalsOfClub2());
    }
}
