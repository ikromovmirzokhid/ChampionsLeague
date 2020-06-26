package com.example.imb.championsleague.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.imb.championsleague.R;
import com.example.imb.championsleague.databases.Database;
import com.example.imb.championsleague.models.ClubModel;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("ValidFragment")
public class ClubHistoryFragment extends Fragment {
    private ClubModel club;
    @BindView(R.id.clubLogo)
    ImageView clubLogo;
    @BindView(R.id.clubName)
    TextView clubName;
    @BindView(R.id.country)
    ImageView country;
    @BindView(R.id.countryName)
    TextView countryName;
    @BindView(R.id.clubPhoto)
    ImageView clubPhoto;
    @BindView(R.id.photoInfo)
    TextView photoInfo;
    @BindView(R.id.wonTitles)
    TextView titles;
    @BindView(R.id.mostAppearancePlayer)
    ImageView mostAppearance;
    @BindView(R.id.mostAppearancePlayerName)
    TextView playerName;
    @BindView(R.id.mostScorerPlayer)
    ImageView scorerPhoto;
    @BindView(R.id.mostScorerPlayerName)
    TextView scorerName;

    @SuppressLint("ValidFragment")
    private ClubHistoryFragment(ClubModel club) {
        this.club = club;
    }

    public static ClubHistoryFragment getInstance(ClubModel club) {
        return new ClubHistoryFragment(club);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.statistics_view, container, false);
        ButterKnife.bind(this, v);

        Glide.with(getActivity()).load(club.getLogo()).into(clubLogo);
        clubName.setText(club.getName());
        Glide.with(getActivity()).load(Database.getInstance().getClubNationality(club.getCountry())).into(country);
        countryName.setText(club.getCountry());
        if (club.getPhoto() != null) {
            Glide.with(getActivity()).load(club.getPhoto()).into(clubPhoto);
            photoInfo.setText(club.getTextOfPhoto());
        } else {
            clubPhoto.setVisibility(View.GONE);
            photoInfo.setVisibility(View.GONE);
        }
        titles.setText("" + club.getTitle());
        Glide.with(getActivity()).load(club.getAppearance_photo()).into(mostAppearance);
        playerName.setText(club.getMost_appearance());
        Glide.with(getActivity()).load(club.getScorerPhoto()).into(scorerPhoto);
        scorerName.setText(club.getTop_scorer());

        return v;
    }
}
