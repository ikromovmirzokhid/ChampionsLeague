package com.example.imb.championsleague.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.imb.championsleague.R;
import com.example.imb.championsleague.activities.MainActivity;
import com.example.imb.championsleague.models.ChooseClubModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChooseFavoriteClubAdapter extends RecyclerView.Adapter<ChooseFavoriteClubAdapter.ViewHolder> {

    private List<ChooseClubModel> club;
    private SharedPreferences sPref;
    private FinishListener listener;

    public void setListener(FinishListener listener) {
        this.listener = listener;
    }

    public ChooseFavoriteClubAdapter(List<ChooseClubModel> club) {
        this.club = club;
    }

    public void setClub(List<ChooseClubModel> club) {
        this.club = club;
    }

    @NonNull
    @Override
    public ChooseFavoriteClubAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.clubs_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChooseFavoriteClubAdapter.ViewHolder holder, int position) {
        holder.clubName.setText(club.get(position).getClubName());
        Glide.with(holder.itemView.getContext()).load(club.get(position).getClubImage()).into(holder.clubImage);
        sPref = holder.itemView.getContext().getSharedPreferences("club", Context.MODE_PRIVATE);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), MainActivity.class);
            SharedPreferences.Editor editor = sPref.edit();
            editor.putString("clubName", club.get(position).getClubName());
            editor.apply();
            v.getContext().startActivity(intent);
            listener.finishListener();
        });

    }

    @Override
    public int getItemCount() {
        return club.size();
    }

    public void setFilter(List<ChooseClubModel> filter) {
        club = filter;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.clubImage)
        ImageView clubImage;
        @BindView(R.id.clubName)
        TextView clubName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    public interface FinishListener{
        void finishListener();
    }
}
