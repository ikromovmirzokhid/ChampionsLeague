package com.example.imb.championsleague.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.imb.championsleague.R;
import com.example.imb.championsleague.diaologs.PlayerInfoDialog;
import com.example.imb.championsleague.models.ClubSquadModel;
import com.example.imb.championsleague.models.PositionModel;
import com.example.imb.championsleague.models.SquadViewHeadModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class SquadListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<SquadViewHeadModel> data;
    private PlayerInfoDialog dialog;
    private String clubName;

    public SquadListAdapter(List<SquadViewHeadModel> data, String clubName) {
        this.data = data;
        this.clubName = clubName;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1)
            return new Holder1(LayoutInflater.from(parent.getContext()).inflate(R.layout.postion_item_list, parent, false));
        else if (viewType == 2)
            return new Holder2(LayoutInflater.from(parent.getContext()).inflate(R.layout.squad_list_item, parent, false));
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 1) {
            Holder1 vHolder = (Holder1) holder;
            PositionModel pData = (PositionModel) data.get(position);
            vHolder.pos.setText(pData.getPosition());
        } else if (holder.getItemViewType() == 2) {
            Holder2 vHolder = (Holder2) holder;
            ClubSquadModel sData = (ClubSquadModel) data.get(position);
            vHolder.playerName.setText(sData.getName());
            vHolder.playerNationality.setText(sData.getCountry());
            if (sData.getNumber() != 0)
                vHolder.playerNumber.setText("" + sData.getNumber());
            Glide.with(vHolder.itemView.getContext()).load(sData.getImage()).into(vHolder.playerImage);

            vHolder.itemView.setOnClickListener(v -> {
                dialog = new PlayerInfoDialog(vHolder.itemView.getContext(), sData, clubName);
                dialog.show();
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (data.get(position).getType() == 1) {
            return 1;
        } else if (data.get(position).getType() == 2) {
            return 2;
        }

        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Holder1 extends RecyclerView.ViewHolder {
        @BindView(R.id.position)
        TextView pos;

        public Holder1(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class Holder2 extends RecyclerView.ViewHolder {
        @BindView(R.id.playerImage)
        CircleImageView playerImage;
        @BindView(R.id.playerName)
        TextView playerName;
        @BindView(R.id.playerNationality)
        TextView playerNationality;
        @BindView(R.id.playerNumber)
        TextView playerNumber;

        public Holder2(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
