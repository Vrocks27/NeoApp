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
import com.va.neoapp.models.HomeGrid;

import java.util.List;

public class HomeGridAdapter extends RecyclerView.Adapter<HomeGridAdapter.ViewHolder> {

    private Context context;
    private List<HomeGrid> homeGridList;
    private HomeGridAdapter.OnItemViewListener OnItemViewListener;

    public HomeGridAdapter(Context context, List<HomeGrid> homeGridList) {
        this.context = context;
        this.homeGridList = homeGridList;
    }

    @NonNull
    @Override
    public HomeGridAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_update_grid_item, parent, false);
        return new HomeGridAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeGridAdapter.ViewHolder holder, int position) {
        if (homeGridList !=null && homeGridList.size() >0 ){

            holder.image_grid_home.setImageDrawable(homeGridList.get(position).getDrawable());
            holder.text_grid.setText(homeGridList.get(position).getTextGrid());
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
        return homeGridList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        AppCompatTextView text_grid;
        AppCompatImageView image_grid_home;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text_grid = itemView.findViewById(R.id.text_grid);
            image_grid_home = itemView.findViewById(R.id.image_grid_home);
        }
    }

    public interface OnItemViewListener {
        void selectedItem(HomeGrid homeGrid);
    }
    public void SetOnItemClickListener(final HomeGridAdapter.OnItemViewListener OnItemViewListener) {
        this.OnItemViewListener = OnItemViewListener;
    }
}
