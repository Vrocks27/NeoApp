package com.va.neoapp.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.va.neoapp.R;
import com.va.neoapp.models.ChatItem;
import com.va.neoapp.util.Constants;
import com.va.neoapp.util.GlobalMethods;

import java.util.List;


public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.ViewHolder> {


    private List<ChatItem> chatItems;
    private Activity activity;

    private OnItemClickListener mItemClickListener;

    public MessagesAdapter(Activity activity, List<ChatItem> chatItems) {
        this.activity = activity;
        this.chatItems = chatItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == Constants.MSG_TYPE_RIGHT) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_right, parent, false);
            return new ViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_left, parent, false);
            return new ViewHolder(view);
        }
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
//        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ChatItem chatItem = chatItems.get(position);
        if (chatItem != null) {
            holder.text_view_message.setText(chatItem.getMessage());

            holder.text_view_time.setText(GlobalMethods.convertDatToTime(chatItem.getTime()));
        }

//        User user = usersList.get(position);
//        if (user != null) {
//            holder.user_name.setText(user.getUsername());
//            GlobalMethods.setImageUI(activity, holder.profile_image, user.getImageURL());
//            if (user.getStatus().equalsIgnoreCase("online")) {
//                holder.user_indicator.setImageResource(R.drawable.online_user_indicator);
//            }
//
//        }

    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
       // FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if (chatItems.get(position).getSender().equalsIgnoreCase(user.getUid())) {
//            return Constants.MSG_TYPE_RIGHT;
//        } else {
            return Constants.MSG_TYPE_LEFT;
        //}
    }

    @Override
    public int getItemCount() {
        return chatItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //  private AppCompatImageView profile_image, user_indicator;
        private AppCompatTextView text_view_message, text_view_time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //profile_image = itemView.findViewById(R.id.profile_image);
            text_view_message = itemView.findViewById(R.id.text_view_message);
            text_view_time = itemView.findViewById(R.id.text_view_time);
            // user_indicator = itemView.findViewById(R.id.user_indicator);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(v, getPosition(), chatItems.get(getAdapterPosition()));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, ChatItem chatItem);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
}
