package com.va.neoapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.va.neoapp.R;
import com.va.neoapp.models.UpdateModel;

import java.util.List;

public class HomeGridAdapter extends RecyclerView.Adapter<HomeGridAdapter.ViewHolder> {

    private Context context;
    private List<UpdateModel> updateModelList;
    private HomeGridAdapter.OnItemViewListener OnItemViewListener;

    public HomeGridAdapter(Context context, List<UpdateModel> updateModelList) {
        this.context = context;
        this.updateModelList = updateModelList;
    }

    @NonNull
    @Override
    public HomeGridAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_update_grid_item, parent, false);
        return new HomeGridAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeGridAdapter.ViewHolder holder, int position) {
        if (updateModelList!=null && updateModelList.size() >0 ){
            holder.text_grid.setText(updateModelList.get(position).getTextGrid());
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
        return updateModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        AppCompatTextView text_grid;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text_grid = itemView.findViewById(R.id.text_grid);
        }
    }

    public interface OnItemViewListener {
        void selectedItem(UpdateModel updateModel);
    }
    public void SetOnItemClickListener(final HomeGridAdapter.OnItemViewListener OnItemViewListener) {
        this.OnItemViewListener = OnItemViewListener;
    }
}
