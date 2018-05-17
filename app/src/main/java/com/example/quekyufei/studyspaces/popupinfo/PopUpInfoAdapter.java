package com.example.quekyufei.studyspaces.popupinfo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.quekyufei.studyspaces.R;
import com.example.quekyufei.studyspaces.SpaceInfoActivity;
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
        View view = ((Activity)context).getLayoutInflater().inflate(R.layout.pop_up_for_map, null);

        TextView name = view.findViewById(R.id.popupName);
        TextView location = view.findViewById(R.id.popupLocation);
        TextView crowdLevel = view.findViewById(R.id.popupCrowdLevel);
        RatingBar rating = view.findViewById(R.id.popupRatingBar);
        Button moreinfoBtn = view.findViewById(R.id.popupInfoBtn);

        PopUpData data = (PopUpData)marker.getTag();
        name.setText(data.getName());
        location.setText(data.getLocation());
        crowdLevel.setText(Integer.toString(data.getCapacity()));
        rating.setNumStars((int)data.getRating());

        moreinfoBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SpaceInfoActivity.class);
                intent.putExtra("key", data);
                context.startActivity(intent);
            }
        });



        return view;
    }
}