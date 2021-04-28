package com.va.neoapp.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.va.neoapp.R;
import com.va.neoapp.models.ChatMain;
import com.va.neoapp.util.GlobalMethods;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder>{

    private ArrayList<ChatMain> usersList;
    private Activity activity;

    private OnItemClickListener mItemClickListener;

    public ChatAdapter(Activity activity, ArrayList<ChatMain> usersList) {
        this.activity = activity;
        this.usersList = usersList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_user_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ChatMain chatItem = usersList.get(position);
        if (chatItem != null) {
            holder.user_name.setText(chatItem.getUsername());
            GlobalMethods.loadImageWithDefault(activity, chatItem.getImageURL(),holder.profile_image);
            if (chatItem.getStatus().equalsIgnoreCase("online")) {
                holder.user_indicator.setImageResource(R.drawable.online_user_indicator);
            }

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
        return usersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private AppCompatImageView profile_image, user_indicator;
        private AppCompatTextView user_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profile_image = itemView.findViewById(R.id.profile_image);
            // profile_image.setImageDrawable();
            user_name = itemView.findViewById(R.id.user_name);
            user_indicator = itemView.findViewById(R.id.user_indicator);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(v, getPosition(), usersList.get(getAdapterPosition()));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, ChatMain user);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
}
