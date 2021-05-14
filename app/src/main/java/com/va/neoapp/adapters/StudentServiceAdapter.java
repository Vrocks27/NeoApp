package com.va.neoapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.va.neoapp.R;
import com.va.neoapp.models.StudentServiceUpdateModel;

import java.util.List;

public class StudentServiceAdapter extends RecyclerView.Adapter<StudentServiceAdapter.MyViewHolder> {
    private Context mContext;
    private List<StudentServiceUpdateModel> studentServiceUpdateModels;


    public StudentServiceAdapter(Context mContext, List<StudentServiceUpdateModel> studentServiceUpdateModels) {
        this.mContext = mContext;
        this.studentServiceUpdateModels = studentServiceUpdateModels;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_faq, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.faq_question.setText(studentServiceUpdateModels.get(position).getService().toString());
        holder.faq_answer.setText(studentServiceUpdateModels.get(position).getServiceAnswer().toString());

        holder.image_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.image_uparrow.setVisibility(View.VISIBLE);
                holder.image_arrow.setVisibility(View.GONE);
                holder.view_liner.setVisibility(View.GONE);
                holder.faq_answer.setVisibility(View.VISIBLE);
                holder.rl_layout.setBackgroundColor(mContext.getResources().getColor(R.color.homepage_tabs_bg));
            }
        });

        holder.image_uparrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.image_arrow.setVisibility(View.VISIBLE);
                holder.view_liner.setVisibility(View.VISIBLE);
                holder.image_uparrow.setVisibility(View.GONE);
                holder.faq_answer.setVisibility(View.GONE);
                holder.rl_layout.setBackgroundColor(mContext.getResources().getColor(R.color.white));
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
        return studentServiceUpdateModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView faq_question,faq_answer;
        AppCompatImageView image_arrow,image_uparrow;
        LinearLayout rl_layout;
        View view_liner;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            faq_question=itemView.findViewById(R.id.faq_question);
            faq_answer=itemView.findViewById(R.id.faq_answer);
            image_arrow=itemView.findViewById(R.id.image_arrow);
            image_uparrow=itemView.findViewById(R.id.image_uparrow);
            rl_layout=itemView.findViewById(R.id.rl_layout);
            view_liner=itemView.findViewById(R.id.view_liner);
        }
    }
}
