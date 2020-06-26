package com.example.imb.championsleague.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.util.DiffUtil;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.imb.championsleague.R;
import com.example.imb.championsleague.adapters.ClubMatchesAdapter;
import com.example.imb.championsleague.databases.Database;
import com.example.imb.championsleague.diffUtil.GroupChangeUtil;
import com.example.imb.championsleague.models.ClubModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteClubGroupFragment extends Fragment {
    private List<ClubModel> club = new ArrayList<>();
    @BindView(R.id.groupTitle)
    TextView group;
    @BindView(R.id.firstClubLogo)
    ImageView firstClubLogo;
    @BindView(R.id.secondClubLogo)
    ImageView secondClubLogo;
    @BindView(R.id.thirdClubLogo)
    ImageView thirdClubLogo;
    @BindView(R.id.fourthClubLogo)
    ImageView fourthClubLogo;
    @BindView(R.id.firstClub)
    TextView firstClub;
    @BindView(R.id.secondClub)
    TextView secondClub;
    @BindView(R.id.thirdClub)
    TextView thirdClub;
    @BindView(R.id.fourthClub)
    TextView fourthClub;
    @BindView(R.id.firstClubP)
    TextView firstClubP;
    @BindView(R.id.secondClubP)
    TextView secondClubP;
    @BindView(R.id.thirdClubP)
    TextView thirdClubP;
    @BindView(R.id.fourthClubP)
    TextView fourthClubP;
    @BindView(R.id.firstClubGD)
    TextView firstClubGD;
    @BindView(R.id.secondClubGD)
    TextView secondClubGD;
    @BindView(R.id.thirdClubGD)
    TextView thirdClubGD;
    @BindView(R.id.fourthClubGD)
    TextView fourthClubGD;
    @BindView(R.id.firstClubPts)
    TextView firstClubPts;
    @BindView(R.id.secondClubPts)
    TextView secondClubPts;
    @BindView(R.id.thirdClubPts)
    TextView thirdClubPts;
    @BindView(R.id.fourthClubPts)
    TextView fourthClubPts;
    private Context context;
    private String grp;


    @SuppressLint("ValidFragment")
    private FavoriteClubGroupFragment() {
    }

    @SuppressLint("ValidFragment")
    private FavoriteClubGroupFragment(String group, Context context) {
        this.context = context;
        grp = group;
        Cursor cursor = Database.getInstance().getGroupData(group);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                club.add(ClubModel.getInstance(cursor));
            } while (cursor.moveToNext());
        }
    }

    public static FavoriteClubGroupFragment getInstance(String group, Context context) {

        return new FavoriteClubGroupFragment(group, context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.groups_table, container, false);
        ButterKnife.bind(this, v);
        OnUpdateGroup();

        sortGroup();

        firstClub.setText(club.get(0).getName());
        secondClub.setText(club.get(1).getName());
        thirdClub.setText(club.get(2).getName());
        fourthClub.setText(club.get(3).getName());

        setImage(firstClubLogo, club.get(0).getLogo());
        setImage(secondClubLogo, club.get(1).getLogo());
        setImage(thirdClubLogo, club.get(2).getLogo());
        setImage(fourthClubLogo, club.get(3).getLogo());

        /*Glide.with(context).load(club.get(0)).into(firstClubLogo);
        Glide.with(context).load(club.get(1)).into(secondClubLogo);
        Glide.with(context).load(club.get(2)).into(thirdClubLogo);
        Glide.with(context).load(club.get(3)).into(fourthClubLogo);*/

        firstClubP.setText("" + club.get(0).getPlayed());
        secondClubP.setText("" + club.get(1).getPlayed());
        thirdClubP.setText("" + club.get(2).getPlayed());
        fourthClubP.setText("" + club.get(3).getPlayed());

        firstClubGD.setText("" + (club.get(0).getGoalScored() - club.get(0).getGoalConceded()));
        secondClubGD.setText("" + (club.get(1).getGoalScored() - club.get(1).getGoalConceded()));
        thirdClubGD.setText("" + (club.get(2).getGoalScored() - club.get(2).getGoalConceded()));
        fourthClubGD.setText("" + (club.get(3).getGoalScored() - club.get(3).getGoalConceded()));

        firstClubPts.setText("" + club.get(0).getPoints());
        secondClubPts.setText("" + club.get(1).getPoints());
        thirdClubPts.setText("" + club.get(2).getPoints());
        fourthClubPts.setText("" + club.get(3).getPoints());

        group.setText("Group " + club.get(0).getGroup());

        return v;
    }

    private void sortGroup() {
        Collections.sort(club, (l1, l2) -> {
            if (l1.getPoints() > l2.getPoints())
                return -1;
            if (l1.getPoints() < l2.getPoints())
                return 1;
            if (l1.getPoints() == l2.getPoints()) {
                int GD1 = l1.getGoalScored() - l1.getGoalConceded();
                int GD2 = l2.getGoalScored() - l2.getGoalConceded();
                if (GD1 > GD2)
                    return -1;
                else if (GD1 < GD2)
                    return 1;
            }
            return 0;
        });

    }

    public void OnUpdateGroup() {
        List<ClubModel> newData = new ArrayList<>();
        newData = Database.getInstance().getGroupDataList(grp);
        GroupChangeUtil util = new GroupChangeUtil(club, newData);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(util);
        club.clear();
        club = newData;
    }

    private void setImage(ImageView image, byte[] logo) {
        image.setImageBitmap(BitmapFactory.decodeByteArray(logo, 0, logo.length));
    }
}
