package com.example.imb.championsleague.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.imb.championsleague.R;
import com.example.imb.championsleague.models.ChooseClubModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClubListAdapter extends RecyclerView.Adapter<ClubListAdapter.ViewHolder> {
    List<ChooseClubModel> clubList;

    public ClubListAdapter(List<ChooseClubModel> clubList) {
        this.clubList = clubList;
    }

    public void setFilter(List<ChooseClubModel> clubList) {
        this.clubList = clubList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.clubs_fragment_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.clubName.setText(clubList.get(position).getClubName());
        Glide.with(holder.itemView).load(clubList.get(position).getClubImage()).into(holder.clubLogo);

    }

    @Override
    public int getItemCount() {
        return clubList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.clubImage)
        ImageView clubLogo;
        @BindView(R.id.clubName)
        TextView clubName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
