package com.example.imb.championsleague.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.imb.championsleague.R;
import com.example.imb.championsleague.models.ClubModel;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.graphics.Bitmap.CompressFormat.JPEG;

public class StandingsAdapter extends RecyclerView.Adapter<StandingsAdapter.ViewHolder> {
    private List<List<ClubModel>> standingsData;

    public StandingsAdapter(List<List<ClubModel>> standingsData) {
        this.standingsData = standingsData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.groups_table, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        List<ClubModel> club;
        club = standingsData.get(position);
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

        holder.firstClub.setText(club.get(0).getName());
        holder.secondClub.setText(club.get(1).getName());
        holder.thirdClub.setText(club.get(2).getName());
        holder.fourthClub.setText(club.get(3).getName());

        Glide.with(holder.itemView.getContext()).load(club.get(0).getLogo()).into(holder.firstClubLogo);
        Glide.with(holder.itemView.getContext()).load(club.get(1).getLogo()).into(holder.secondClubLogo);
        Glide.with(holder.itemView.getContext()).load(club.get(2).getLogo()).into(holder.thirdClubLogo);
        Glide.with(holder.itemView.getContext()).load(club.get(3).getLogo()).into(holder.fourthClubLogo);

        /*setImage(holder.firstClubLogo, club.get(0).getLogo());
        setImage(holder.secondClubLogo, club.get(1).getLogo());
        setImage(holder.thirdClubLogo, club.get(2).getLogo());
        setImage(holder.fourthClubLogo, club.get(3).getLogo());*/


        holder.firstClubP.setText("" + club.get(0).getPlayed());
        holder.secondClubP.setText("" + club.get(1).getPlayed());
        holder.thirdClubP.setText("" + club.get(2).getPlayed());
        holder.fourthClubP.setText("" + club.get(3).getPlayed());

        holder.firstClubGD.setText("" + (club.get(0).getGoalScored() - club.get(0).getGoalConceded()));
        holder.secondClubGD.setText("" + (club.get(1).getGoalScored() - club.get(1).getGoalConceded()));
        holder.thirdClubGD.setText("" + (club.get(2).getGoalScored() - club.get(2).getGoalConceded()));
        holder.fourthClubGD.setText("" + (club.get(3).getGoalScored() - club.get(3).getGoalConceded()));

        holder.firstClubPts.setText("" + club.get(0).getPoints());
        holder.secondClubPts.setText("" + club.get(1).getPoints());
        holder.thirdClubPts.setText("" + club.get(2).getPoints());
        holder.fourthClubPts.setText("" + club.get(3).getPoints());

        holder.group.setText("Group " + club.get(0).getGroup());
    }

    private void setImage(ImageView image, byte[] logo) {
        image.setImageBitmap(BitmapFactory.decodeByteArray(logo, 0, logo.length));
    }

    @Override
    public int getItemCount() {
        return standingsData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
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

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
