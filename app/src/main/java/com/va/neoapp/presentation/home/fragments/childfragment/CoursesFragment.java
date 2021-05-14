package com.va.neoapp.presentation.home.fragments.childfragment;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.va.neoapp.R;
import com.va.neoapp.adapters.CoursesAdapter;
import com.va.neoapp.models.Coursesmodel;
import com.va.neoapp.presentation.homeservices.activities.CourseInDetailsActivity;
import com.va.neoapp.util.GlobalMethods;

import java.util.ArrayList;
import java.util.List;

public class CoursesFragment extends Fragment {

    private Context mContext;
    private ChipGroup chipGroup;
    private String[] chipsArray = {"Engineering", "Medical", "BioTechnology"};
    private AppCompatEditText et_search;
    private RecyclerView rv_courses;
    private CoursesAdapter coursesAdapter;
    private List<Coursesmodel> coursesmodelList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_courses, container, false);
        mContext = getActivity();
        initGUI(view);
        initData();
        return view;
    }


    private void initGUI(View view) {
        et_search = view.findViewById(R.id.et_search);
        rv_courses = view.findViewById(R.id.rv_courses);

        /*search here for courses*/
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        /*set data to adapter*/
        coursesmodelList = new ArrayList<>();
        coursesmodelList.add(new Coursesmodel("Clinical Dentistry", "Masters", "Full Time", " 3 Years", "May/Sept", "IELET, TOEFL",getResources().getString(R.string.dummy_text)));
        coursesmodelList.add(new Coursesmodel("Oncology", "Bachelors", "Full Time", " 5 Years", "May/Sept", "IELET, TOEFL",getResources().getString(R.string.dummy_text)));
        coursesmodelList.add(new Coursesmodel("Radiology", "Masters", "Full Time", " 3 Years", "May/Sept", "IELET, TOEFL",getResources().getString(R.string.dummy_text)));
        coursesmodelList.add(new Coursesmodel("Pathology", "Bachelors", "Full Time", " 5 Years", "May/Sept", "IELET, TOEFL",getResources().getString(R.string.dummy_text)));
        coursesmodelList.add(new Coursesmodel("BioMedical", "Masters", "Full Time", " 3 Years", "May/Sept", "IELET, TOEFL",getResources().getString(R.string.dummy_text)));
        coursesmodelList.add(new Coursesmodel("Dental", "Masters", "Full Time", " 3 Years", "May/Sept", "IELET, TOEFL",getResources().getString(R.string.dummy_text)));

        coursesAdapter = new CoursesAdapter(mContext, coursesmodelList, new CoursesAdapter.onItemSelectedListener() {
            @Override
            public void onItemSelected(Coursesmodel coursesmodel) {
                Bundle bundle=new Bundle();
                bundle.putSerializable("course",coursesmodel);
                GlobalMethods.callForWordActivity(mContext, CourseInDetailsActivity.class, bundle, false, true);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false);
        rv_courses.setLayoutManager(linearLayoutManager);
        rv_courses.setAdapter(coursesAdapter);


        /*chips*/
        chipGroup = view.findViewById(R.id.courses_chip);
        for (String s : chipsArray) {
            Chip chip = new Chip(getActivity());
            chip.setText(s);
            /*chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Toast.makeText(mContext, "Clicked " + buttonView.getId(), Toast.LENGTH_SHORT).show();
                    Log.e("Error",buttonView.getText().toString());
                }
            });*/
            //chip.setCloseIconEnabled(true);
            //chip.setCloseIconResource(R.drawable.your_icon);
            //chip.setChipIconResource(R.drawable.your_icon);
            //chip.setChipBackgroundColorResource(R.color.red);
            //chip.setTextAppearanceResource(R.style.ChipTextStyle);
            chip.setElevation(5);

            chipGroup.addView(chip);
            //chip.setChipDrawable(mContext.getResources().getDrawable(R.drawable.text_background));
        }

        chipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                Chip selectedChip = group.findViewById(checkedId);
                Log.e("id_error", String.valueOf(checkedId));
                if (selectedChip != null) {
                    Toast.makeText(getActivity(), selectedChip.getChipText().toString(), Toast.LENGTH_SHORT).show();
                    Log.e("ID", selectedChip.getChipText().toString());
                }
            }
        });

        /*Chip chip = new Chip(mContext);

        chip.setText("ABC");
        chip.setChipBackgroundColorResource(R.color.colorAccent);
        chip.setCloseIconVisible(true);
        chip.setTextColor(getResources().getColor(R.color.white));
        chip.setTextAppearance(R.style.ChipTextAppearance);*/
    }

    private void filter(String text) {
        ArrayList<Coursesmodel> filteredList = new ArrayList<>();
        for (Coursesmodel item : coursesmodelList) {
            if (item.getCourseName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        coursesAdapter.filterList(filteredList);
    }

    private void initData() {

    }

}
