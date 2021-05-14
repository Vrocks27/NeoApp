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
import com.va.neoapp.util.GlobalMethods;

import java.util.List;

public class LatestNewsAdapter extends RecyclerView.Adapter<LatestNewsAdapter.MyViewHolder> {
    private Context mContext;
    private List<LatestNewModel> latestNewModels;
    private LatestNewsAdapter.onItemClickListener onItemClickListener;

    public LatestNewsAdapter(Context mContext, List<LatestNewModel> latestNewModels,LatestNewsAdapter.onItemClickListener onItemClickListener) {
        this.mContext = mContext;
        this.latestNewModels = latestNewModels;
        this.onItemClickListener=onItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_latest_news, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (latestNewModels != null && latestNewModels.size() > 0) {
            holder.tv_news_title.setText(latestNewModels.get(position).getTitle());
            holder.tv_news_description.setText(latestNewModels.get(position).getDescription());
            GlobalMethods.loadImageWithDefault(mContext,latestNewModels.get(position).getImage_url(),holder.image_news);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.itemSelected(latestNewModels.get(position));
            }
        });
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

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView tv_news_title, tv_news_description;
        AppCompatImageView image_news;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_news_description = itemView.findViewById(R.id.tv_news_description);
            tv_news_title = itemView.findViewById(R.id.tv_news_title);
            image_news = itemView.findViewById(R.id.image_news);
        }
    }

    public interface onItemClickListener{
        void itemSelected(LatestNewModel latestNewModel);
    }

    /*public void SetOnItemClickListener(final onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }*/
}
