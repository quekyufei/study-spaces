package com.example.quekyufei.studyspaces;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.quekyufei.studyspaces.database.DatabaseFactory;
import com.example.quekyufei.studyspaces.database.DatabaseInterface;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class ViewMapActivity extends FragmentActivity implements OnMapReadyCallback, FilterSpacesInterface {

    private GoogleMap mMap;
    private PreferencesFragment prefFragment;
    private List<StudySpace> spaceList;

    private boolean[] filterCriteria = new boolean[5];

    private Predicate<StudySpace> hasWifi = StudySpace::isWifi;
    private Predicate<StudySpace> hasAircon = StudySpace::isAircon;
    private Predicate<StudySpace> hasPower = StudySpace::isPower;
    private Predicate<StudySpace> hasFood = StudySpace::isFood;
    private Predicate<StudySpace> canDiscuss = StudySpace::isDiscussion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Arrays.fill(filterCriteria,false);

        Button preferencesButton = findViewById(R.id.preferencesButton);
        preferencesButton.setOnClickListener((View v)->{
                Bundle b = new Bundle();
                b.putBooleanArray("filterCriteria", filterCriteria);
                prefFragment = new PreferencesFragment();
                prefFragment.setArguments(b);
                FragmentTransaction fragTransac = getSupportFragmentManager().beginTransaction();
                fragTransac.replace(R.id.preferencesFragmentContainer, prefFragment)
                        .addToBackStack(null)
                        .commit();

                findViewById(R.id.transparentOverlay).setClickable(true);
                Log.d("ViewMapActivity","map disabled");
        });


    }

    @Override
    public void onBackPressed(){
        turnOffTransparentOverlay();
        super.onBackPressed();
    }

    @Override
    public void filtersUpdated(){
        Predicate<StudySpace> criteria = ss -> true;
        if(prefFragment.mcriteria[0]) criteria = criteria.and(hasWifi);
        if(prefFragment.mcriteria[1]) criteria = criteria.and(hasAircon);
        if(prefFragment.mcriteria[2]) criteria = criteria.and(hasPower);
        if(prefFragment.mcriteria[3]) criteria = criteria.and(hasFood);
        if(prefFragment.mcriteria[4]) criteria = criteria.and(canDiscuss);


        for(StudySpace ss : spaceList){
            if(!criteria.test(ss)){
                if(ss.getMapsMarker()!=null){
                    ss.getMapsMarker().remove();
                    ss.setMapsMarker(null);
                }
            }else{
                if(ss.getMapsMarker()==null){
                    LatLng position = new LatLng(ss.getLatitude(),ss.getLongitude());
                    ss.setMapsMarker(mMap.addMarker(new MarkerOptions().position(position).title(ss.getName())));
                }
            }
        }
    }

    @Override
    public void filtersDone(){
        System.arraycopy(prefFragment.mcriteria,0,filterCriteria,0,5);
        getSupportFragmentManager().popBackStack();
        turnOffTransparentOverlay();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng ntu = new LatLng(1.3483, 103.6831);
        mMap.addMarker(new MarkerOptions().position(ntu).title("Marker at NTU"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ntu,(float)15.2));

        DatabaseInterface db = new DatabaseFactory().getDatabase("local");

        spaceList = db.getStudySpaces(getApplicationContext());
        for(StudySpace space : spaceList){
            Log.d("ViewMapActivity", "name: " + space.getName() + ", " + space.isAircon());
            LatLng position = new LatLng(space.getLatitude(),space.getLongitude());
            Marker m = mMap.addMarker(new MarkerOptions().position(position).title(space.getName()));
            space.setMapsMarker(m);
        }
    }

    private void turnOffTransparentOverlay(){
        if(findViewById(R.id.transparentOverlay).isClickable()){
            findViewById(R.id.transparentOverlay).setClickable(false);
        }
    }
}