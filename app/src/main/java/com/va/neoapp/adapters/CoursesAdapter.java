package com.va.neoapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.va.neoapp.R;
import com.va.neoapp.models.Coursesmodel;

import java.util.ArrayList;
import java.util.List;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.MyViewHolder> {
    private Context mContext;
    private List<Coursesmodel> coursesmodels;
    private CoursesAdapter.onItemSelectedListener onItemSelectedListener;


    public CoursesAdapter(Context mContext, List<Coursesmodel> coursesmodels,CoursesAdapter.onItemSelectedListener onItemSelectedListener) {
        this.mContext = mContext;
        this.coursesmodels = coursesmodels;
        this.onItemSelectedListener=onItemSelectedListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_courses, parent, false);
        return new CoursesAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (coursesmodels != null && coursesmodels.size() > 0) {
            holder.tv_course_name.setText(coursesmodels.get(position).getCourseName());
            holder.tv_course_type.setText(coursesmodels.get(position).getCourseType());
            holder.tv_course_period.setText(coursesmodels.get(position).getCoursePeriod());
            holder.tv_course_timeperiod.setText(coursesmodels.get(position).getCourseTimePeriod());
            holder.tv_course_intake.setText("Intake: "+coursesmodels.get(position).getIntakePeriod());
            holder.tv_course_eligiblecriteria.setText("Eligibility Criteria: "+coursesmodels.get(position).getEligibilityCriteria());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemSelectedListener.onItemSelected(coursesmodels.get(position));
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return coursesmodels.size();
    }

    public void filterList(ArrayList<Coursesmodel> filteredList) {
            coursesmodels = filteredList;
            notifyDataSetChanged();
    }

    public interface onItemSelectedListener{
        void onItemSelected(Coursesmodel coursesmodel);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        AppCompatTextView tv_course_name, tv_course_type,
                tv_course_period, tv_course_timeperiod,
                tv_course_intake, tv_course_eligiblecriteria;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_course_name=itemView.findViewById(R.id.tv_course_name);
            tv_course_type=itemView.findViewById(R.id.tv_course_type);
            tv_course_period=itemView.findViewById(R.id.tv_course_period);
            tv_course_timeperiod=itemView.findViewById(R.id.tv_course_timeperiod);
            tv_course_intake=itemView.findViewById(R.id.tv_course_intake);
            tv_course_eligiblecriteria=itemView.findViewById(R.id.tv_course_eligible_criteria);
        }
    }
}
