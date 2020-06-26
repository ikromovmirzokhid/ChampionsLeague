package com.example.imb.championsleague.diaologs;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.imb.championsleague.R;
import com.example.imb.championsleague.databases.Database;
import com.example.imb.championsleague.models.ClubSquadModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayerInfoDialog extends AlertDialog {
    private View v;
    @BindView(R.id.playerInfoBackground)
    ImageView background;
    @BindView(R.id.playerPhoto)
    ImageView playerPhoto;
    @BindView(R.id.playerName)
    TextView playerName;
    @BindView(R.id.position)
    TextView position;
    @BindView(R.id.age)
    TextView age;
    @BindView(R.id.country)
    TextView country;
    @BindView(R.id.weight)
    TextView weight;
    @BindView(R.id.height)
    TextView height;
    private ClubSquadModel playerInfo;
    private byte[] clubLogo;
    private Context context;

    public PlayerInfoDialog(@NonNull Context context, ClubSquadModel playerInfo, String clubName) {
        super(context);
        this.context = context;
        clubLogo = Database.getInstance().getClubLogo(clubName);
        v = LayoutInflater.from(context).inflate(R.layout.player_info_view, null, false);
        this.playerInfo = playerInfo;
        setView(v);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this, v);
        Glide.with(context).load(clubLogo).into(background);
        Glide.with(context).load(playerInfo.getImage()).into(playerPhoto);
        playerName.setText(playerInfo.getName());
        position.setText(playerInfo.getPosition());
        if (playerInfo.getAge() != 0)
            age.setText("" + playerInfo.getAge());
        else age.setText("-");
        country.setText(playerInfo.getCountry());
        if (playerInfo.getWeigth() != 0)
            weight.setText("" + playerInfo.getWeigth());
        else weight.setText("-");
        if (playerInfo.getHeight() != 0)
            height.setText("" + playerInfo.getHeight());
        else height.setText("-");

    }
}
