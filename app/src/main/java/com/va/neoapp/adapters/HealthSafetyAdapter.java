package com.va.neoapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.va.neoapp.R;
import com.va.neoapp.models.HealthSafetyModel;
import com.va.neoapp.util.GlobalMethods;

import java.util.List;

public class HealthSafetyAdapter extends RecyclerView.Adapter<HealthSafetyAdapter.MyViewHolder> {

    private Context mContext;
    private List<HealthSafetyModel> healthSafetyList;
    private HealthSafetyAdapter.onItemClickListener onItemClickListener;

    public HealthSafetyAdapter(Context mContext, List<HealthSafetyModel> healthSafetyList, HealthSafetyAdapter.onItemClickListener onItemClickListener) {
        this.mContext = mContext;
        this.healthSafetyList = healthSafetyList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public HealthSafetyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_latest_videos, parent, false);
        return new HealthSafetyAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HealthSafetyAdapter.MyViewHolder holder, int position) {
        if (healthSafetyList != null && healthSafetyList.size() > 0) {

            holder.tv_title_video.setText(healthSafetyList.get(position).getTitle());
            holder.tv_video_description.setText(healthSafetyList.get(position).getDescription());
            GlobalMethods.loadImageWithDefault(mContext, healthSafetyList.get(position).getThumbnail_url(), holder.img_thumbnail);

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null) {
                    onItemClickListener.itemSelected(healthSafetyList.get(position));
                }
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return healthSafetyList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView tv_title_video, tv_video_description;
        AppCompatImageView img_thumbnail;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title_video = itemView.findViewById(R.id.tv_title_video);
            tv_video_description = itemView.findViewById(R.id.tv_video_description);
            img_thumbnail = itemView.findViewById(R.id.img_thumbnail);
        }
    }

    public interface onItemClickListener {
        void itemSelected(HealthSafetyModel healthSafetyModel);
    }
}
