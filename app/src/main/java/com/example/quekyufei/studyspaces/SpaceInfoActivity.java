package com.example.quekyufei.studyspaces;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class SpaceInfoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_space_info);

        Intent i = getIntent();
        StudySpace space = (StudySpace) i.getSerializableExtra("studyspace");

        String name = space.getName();
        int capacity = space.getCapacity();
        boolean wifi = space.isWifi();
        boolean aircon = space.isAircon();
        boolean power = space.isPower();
        boolean food = space.isFood();
        boolean discussion = space.isDiscussion();
        String picture = space.getImage();

        ImageView image = (ImageView) findViewById(R.id.image);
        Context context = image.getContext();
        int id = image.getResources().getIdentifier(picture,"drawable", context.getPackageName());
        image.setImageResource(id);

        TextView spacename = (TextView) findViewById(R.id.name);
        spacename.setText(name);

        TextView capa = (TextView) findViewById(R.id.capacity);
        capa.setText("Capacity: " + capacity);

        if (wifi == true) {
            CheckBox box1 = (CheckBox) findViewById(R.id.Wifibox);
            box1.setChecked(!box1.isChecked());
        }

        if (aircon == true) {
            CheckBox box2 = (CheckBox) findViewById(R.id.Airconbox);
            box2.setChecked(!box2.isChecked());
        }

        if (power == true) {
            CheckBox box3 = (CheckBox) findViewById(R.id.powerCheckbox);
            box3.setChecked(!box3.isChecked());
        }

        if (food == true) {
            CheckBox box4 = (CheckBox) findViewById(R.id.foodCheckbox);
            box4.setChecked(!box4.isChecked());
        }

        if (discussion == true) {
            CheckBox box5 = (CheckBox) findViewById(R.id.discussionCheckbox);
            box5.setChecked(!box5.isChecked());
        }


    }
}
