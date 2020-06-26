package com.example.imb.championsleague.adapters;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.imb.championsleague.R;
import com.example.imb.championsleague.databases.Database;
import com.example.imb.championsleague.models.MatchdayModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClubMatchesAdapter extends RecyclerView.Adapter<ClubMatchesAdapter.ViewHolder> {
    private List<MatchdayModel> matchesList;
    private int win1, win2, draw1, draw2, loss1, loss2, point1, point2;

    public ClubMatchesAdapter(List<MatchdayModel> matchesList) {
        this.matchesList = matchesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.matches_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.firstTeamName.setText(matchesList.get(position).getClub1());
        holder.secondTeamName.setText(matchesList.get(position).getClub2());
        holder.matchDate.setText(matchesList.get(position).getDate());
        holder.timeOfMatch.setText(matchesList.get(position).getTime());
        holder.saveEditBtn.setText(matchesList.get(position).getStatus());
        Glide.with(holder.itemView).load(matchesList.get(position).getClub1Logo()).into(holder.firstTeam);
        Glide.with(holder.itemView).load(matchesList.get(position).getClub2Logo()).into(holder.secondTeam);
        Glide.with(holder.itemView).load(R.drawable.matches_background).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                holder.layout.setBackground(resource);
            }
        });
        if (matchesList.get(position).getGoalsOfClub1() != null && matchesList.get(position).getGoalsOfClub2() != null) {
            holder.firstTeamScore.setText("" + matchesList.get(position).getGoalsOfClub1());
            holder.secondTeamScore.setText("" + matchesList.get(position).getGoalsOfClub2());
        } else {
            holder.firstTeamScore.setText("-");
            holder.secondTeamScore.setText("-");
        }
        holder.firstTeamScore.setOnClickListener(v -> {
            PopupMenu menu = new PopupMenu(holder.itemView.getContext(), v);
            MenuInflater inflater = menu.getMenuInflater();
            inflater.inflate(R.menu.score_menu, menu.getMenu());
            menu.show();
            menu.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.one:
                        holder.firstTeamScore.setText("" + 1);
                        break;
                    case R.id.two:
                        holder.firstTeamScore.setText("" + 2);
                        break;
                    case R.id.three:
                        holder.firstTeamScore.setText("" + 3);
                        break;
                    case R.id.four:
                        holder.firstTeamScore.setText("" + 4);
                        break;
                    case R.id.five:
                        holder.firstTeamScore.setText("" + 5);
                        break;
                    case R.id.six:
                        holder.firstTeamScore.setText("" + 6);
                        break;
                    case R.id.seven:
                        holder.firstTeamScore.setText("" + 7);
                        break;
                    case R.id.eight:
                        holder.firstTeamScore.setText("" + 8);
                        break;
                    case R.id.nine:
                        holder.firstTeamScore.setText("" + 9);
                        break;
                }
                return true;
            });
        });

        holder.secondTeamScore.setOnClickListener(v -> {
            PopupMenu menu = new PopupMenu(holder.itemView.getContext(), v);
            MenuInflater inflater = menu.getMenuInflater();
            inflater.inflate(R.menu.score_menu, menu.getMenu());
            menu.show();
            menu.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.zero:
                        holder.secondTeamScore.setText("" + 0);
                        break;
                    case R.id.one:
                        holder.secondTeamScore.setText("" + 1);
                        break;
                    case R.id.two:
                        holder.secondTeamScore.setText("" + 2);
                        break;
                    case R.id.three:
                        holder.secondTeamScore.setText("" + 3);
                        break;
                    case R.id.four:
                        holder.secondTeamScore.setText("" + 4);
                        break;
                    case R.id.five:
                        holder.secondTeamScore.setText("" + 5);
                        break;
                    case R.id.six:
                        holder.secondTeamScore.setText("" + 6);
                        break;
                    case R.id.seven:
                        holder.secondTeamScore.setText("" + 7);
                        break;
                    case R.id.eight:
                        holder.secondTeamScore.setText("" + 8);
                        break;
                    case R.id.nine:
                        holder.secondTeamScore.setText("" + 9);
                        break;
                }
                return true;
            });
        });

        if (holder.saveEditBtn.getText().toString().equals("edit")) {
            holder.firstTeamScore.setClickable(false);
            holder.secondTeamScore.setClickable(false);
        }

        holder.saveEditBtn.setOnClickListener(v -> {
            if (!holder.firstTeamScore.getText().toString().equals("-") && !holder.secondTeamScore.getText().toString().equals("-")) {

                int firstScore = Integer.parseInt(holder.firstTeamScore.getText().toString());
                int secondScore = Integer.parseInt(holder.secondTeamScore.getText().toString());
                whoWon(firstScore, secondScore);
                if (holder.saveEditBtn.getText().toString().equals("save")) {

                    Database.getInstance().updateStatistics(1, firstScore, secondScore, point1, win1, draw1, loss1, holder.firstTeamName.getText().toString());
                    Database.getInstance().updateStatistics(1, secondScore, firstScore, point2, win2, draw2, loss2, holder.secondTeamName.getText().toString());

                    Database.getInstance().updateStatus(matchesList.get(position).getId(), matchesList.get(position).getMatchday(),
                            "" + firstScore, "" + secondScore, "edit");

                    holder.saveEditBtn.setText("edit");
                    holder.firstTeamScore.setClickable(false);
                    holder.secondTeamScore.setClickable(false);
                } else if (holder.saveEditBtn.getText().toString().equals("edit")) {

                    Database.getInstance().undoStatistics(1, firstScore, secondScore, point1, win1, draw1, loss1, holder.firstTeamName.getText().toString());
                    Database.getInstance().undoStatistics(1, secondScore, firstScore, point2, win2, draw2, loss2, holder.secondTeamName.getText().toString());
                    Database.getInstance().updateStatus(matchesList.get(position).getId(), matchesList.get(position).getMatchday(),
                            null, null, "save");

                    holder.firstTeamScore.setClickable(true);
                    holder.secondTeamScore.setClickable(true);
                    holder.saveEditBtn.setText("save");
                }
            }
        });

    }

    private void whoWon(int firstScore, int secondScore) {
        if (firstScore > secondScore) {
            win1 = 1;
            win2 = 0;
            draw1 = 0;
            draw2 = 0;
            loss1 = 0;
            loss2 = 1;
            point1 = 3;
            point2 = 0;
        } else if (firstScore == secondScore) {
            win1 = 0;
            win2 = 0;
            draw1 = 1;
            draw2 = 1;
            loss1 = 0;
            loss2 = 0;
            point1 = 1;
            point2 = 1;
        } else if (firstScore < secondScore) {
            win1 = 0;
            win2 = 1;
            draw1 = 0;
            draw2 = 0;
            loss1 = 1;
            loss2 = 0;
            point1 = 0;
            point2 = 3;
        }


    }

    @Override
    public int getItemCount() {
        return matchesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.background)
        ConstraintLayout layout;
        @BindView(R.id.firstTeamName)
        TextView firstTeamName;
        @BindView(R.id.secondTeamName)
        TextView secondTeamName;
        @BindView(R.id.matchDate)
        TextView matchDate;
        @BindView(R.id.firstTeam)
        ImageView firstTeam;
        @BindView(R.id.secondTeam)
        ImageView secondTeam;
        @BindView(R.id.firstTeamScore)
        TextView firstTeamScore;
        @BindView(R.id.secondTeamScore)
        TextView secondTeamScore;
        @BindView(R.id.timeOfMatch)
        TextView timeOfMatch;
        @BindView(R.id.saveEdit)
        Button saveEditBtn;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}

