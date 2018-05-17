package com.example.quekyufei.studyspaces.popupinfo;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.quekyufei.studyspaces.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class PopUpInfoAdapter implements GoogleMap.InfoWindowAdapter {

    private Context context;

    public PopUpInfoAdapter(Context ctx){
        context = ctx;
    }


    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View view = ((Activity)context).getLayoutInflater().inflate(R.layout.some_layout, null);

        TextView name = view.findViewById(R.id.popUpName);
        TextView location = view.findViewById(R.id.popUpLocation);
        TextView crowdLevel = view.findViewById(R.id.popUpCrowd);
        TextView rating = view.findViewById(R.id.popUpRating);

        PopUpData data = (PopUpData)marker.getTag();
        name.setText(data.getName());
        location.setText(data.getLocation());
        crowdLevel.setText(Integer.toString(data.getCapacity()));
        rating.setText(Float.toString(data.getRating()));

        return view;
    }
}