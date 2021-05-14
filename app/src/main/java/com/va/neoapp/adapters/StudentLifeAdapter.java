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
import com.va.neoapp.models.StudentLifeModel;
import com.va.neoapp.util.GlobalMethods;

import java.util.List;

public class StudentLifeAdapter extends RecyclerView.Adapter<StudentLifeAdapter.MyViewHolder> {

    private Context mContext;
    private List<StudentLifeModel> studentLifeModels;
    private StudentLifeAdapter.onItemSelecteListener onItemSelecteListener;

    public StudentLifeAdapter(Context mContext, List<StudentLifeModel> studentLifeModels, StudentLifeAdapter.onItemSelecteListener onItemSelecteListener) {
        this.mContext = mContext;
        this.studentLifeModels = studentLifeModels;
        this.onItemSelecteListener = onItemSelecteListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_student_life,parent,false);
        return new StudentLifeAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if (studentLifeModels!=null && studentLifeModels.size()>0){
            holder.tv_lifetitle.setText(studentLifeModels.get(position).getTitle());
            holder.tv_lifedescription.setText(studentLifeModels.get(position).getDescription());
            GlobalMethods.loadImageWithDefault(mContext,studentLifeModels.get(position).getImageURL(),holder.image_studentlife);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemSelecteListener.onItemSelected(studentLifeModels.get(position));
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
        return studentLifeModels.size();
    }

    public interface onItemSelecteListener{
        void onItemSelected(StudentLifeModel position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        AppCompatImageView image_studentlife;
        AppCompatTextView tv_lifetitle,tv_lifedescription;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image_studentlife=itemView.findViewById(R.id.image_studentlife);
            tv_lifetitle=itemView.findViewById(R.id.tv_lifetitle);
            tv_lifedescription=itemView.findViewById(R.id.tv_lifedescription);
        }
    }
}
