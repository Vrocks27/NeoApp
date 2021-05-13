package com.va.neoapp.presentation.home.fragments.childfragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.va.neoapp.R;

public class CoursesFragment extends Fragment {

    private Context mContext;
    private ChipGroup chipGroup;
    private String[] chipsArray = {"Engineering", "Medical", "BioTechnology"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_courses, container, false);
        mContext = getActivity();
        initGUI(view);
        initData();
        return view;
    }

    private void initData() {

    }

    private void initGUI(View view) {
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
                Chip selectedChip=group.findViewById(checkedId);
                Log.e("id_error", String.valueOf(checkedId));
                if (selectedChip!=null){
                    Toast.makeText(getActivity(),selectedChip.getChipText().toString(),Toast.LENGTH_SHORT).show();
                    Log.e("ID",selectedChip.getChipText().toString());
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

}
