package com.example.quekyufei.studyspaces;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PreferencesFragment extends Fragment {

    private FilterSpacesInterface activityInterface;
    private List<CheckBox> checkBoxList = new ArrayList<>();

    public boolean[] mcriteria = new boolean[5];

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean[] savedCriteria = getArguments().getBooleanArray("filterCriteria");
        if(savedCriteria != null)
            System.arraycopy(savedCriteria,0,mcriteria,0,5);
        else
            Arrays.fill(mcriteria,false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.map_filter_fragment, container, false);

        Button doneButton = view.findViewById(R.id.filterDoneButton);
        checkBoxList.add(view.findViewById(R.id.wifiCheckbox));
        checkBoxList.add(view.findViewById(R.id.airconCheckbox));
        checkBoxList.add(view.findViewById(R.id.powerCheckbox));
        checkBoxList.add(view.findViewById(R.id.foodCheckbox));
        checkBoxList.add(view.findViewById(R.id.discussionCheckbox));

        doneButton.setOnClickListener((View v)-> activityInterface.filtersDone());

        for(int i = 0; i < 5; i++){
            checkBoxList.get(i).setOnClickListener((View v)->{
                    mcriteria[0] = checkBoxList.get(0).isChecked();
                    mcriteria[1] = checkBoxList.get(1).isChecked();
                    mcriteria[2] = checkBoxList.get(2).isChecked();
                    mcriteria[3] = checkBoxList.get(3).isChecked();
                    mcriteria[4] = checkBoxList.get(4).isChecked();
                    activityInterface.filtersUpdated();
            });

            checkBoxList.get(i).setChecked(mcriteria[i]);
        }

        return view;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try{
            activityInterface = (FilterSpacesInterface)context;
        }catch (ClassCastException e){
            e.printStackTrace();
        }
    }
}