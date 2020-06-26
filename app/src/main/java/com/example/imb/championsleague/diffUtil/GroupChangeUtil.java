package com.example.imb.championsleague.diffUtil;

import android.support.v7.util.DiffUtil;

import com.example.imb.championsleague.models.ClubModel;

import java.util.List;

public class GroupChangeUtil extends DiffUtil.Callback {
    private List<ClubModel> oldData;
    private List<ClubModel> newData;

    public GroupChangeUtil(List<ClubModel> oldData, List<ClubModel> youngData) {
        this.oldData = oldData;
        this.newData = youngData;
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
        return oldData.get(oldItemPosition).getPoints() == newData.get(newItemPosition).getPoints();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldData.get(oldItemPosition).getWin() == newData.get(newItemPosition).getWin() &&
                oldData.get(oldItemPosition).getDraw() == newData.get(newItemPosition).getDraw() &&
                oldData.get(oldItemPosition).getLoss() == newData.get(newItemPosition).getLoss() &&
                oldData.get(oldItemPosition).getGoalScored() == newData.get(newItemPosition).getGoalScored() &&
                oldData.get(oldItemPosition).getGoalConceded() == newData.get(newItemPosition).getGoalConceded();
    }
}
