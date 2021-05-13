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
import com.va.neoapp.models.LatestNewModel;

import java.util.List;

public class LatestNewsAdapter extends RecyclerView.Adapter<LatestNewsAdapter.MyViewHolder> {
    private Context mContext;
    private List<LatestNewModel> latestNewModels;

    public LatestNewsAdapter(Context mContext, List<LatestNewModel> latestNewModels) {
        this.mContext = mContext;
        this.latestNewModels = latestNewModels;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_latest_news, parent, false);
        return new LatestNewsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (latestNewModels != null && latestNewModels.size() > 0) {
            holder.tv_news_title.setText(latestNewModels.get(position).getTitle());
            holder.tv_news_description.setText(latestNewModels.get(position).getDescription());
            holder.image_news.setImageDrawable(latestNewModels.get(position).getDrawable());
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return latestNewModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView tv_news_title, tv_news_description;
        AppCompatImageView image_news;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_news_description = itemView.findViewById(R.id.tv_news_description);
            tv_news_title = itemView.findViewById(R.id.tv_news_title);
            image_news = itemView.findViewById(R.id.image_news);
        }
    }
}
