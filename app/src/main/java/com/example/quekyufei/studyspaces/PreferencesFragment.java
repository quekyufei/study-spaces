package com.example.quekyufei.studyspaces;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Filter;

public class PreferencesFragment extends Fragment {

    private FilterSpacesInterface activityInterface;

    public boolean wifi = false;
    public boolean aircon = false;
    public boolean power = false;
    public boolean food = false;
    public boolean discussion = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.map_filter_fragment, container, false);
        Button doneButton = view.findViewById(R.id.filterDoneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wifi = ((CheckBox)view.findViewById(R.id.wifiCheckbox)).isChecked();
                aircon = ((CheckBox)view.findViewById(R.id.airconCheckbox)).isChecked();
                power = ((CheckBox)view.findViewById(R.id.powerCheckbox)).isChecked();
                food = ((CheckBox)view.findViewById(R.id.foodCheckbox)).isChecked();
                discussion = ((CheckBox)view.findViewById(R.id.discussionCheckbox)).isChecked();
                activityInterface.filtersUpdated();
            }
        });



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