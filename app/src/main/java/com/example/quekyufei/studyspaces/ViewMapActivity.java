package com.example.quekyufei.studyspaces;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.quekyufei.studyspaces.database.DatabaseFactory;
import com.example.quekyufei.studyspaces.database.DatabaseInterface;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.function.Predicate;

public class ViewMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        List<StudySpace> spaceList = db.getStudySpaces(getApplicationContext());
        for(StudySpace space : spaceList){
            LatLng position = new LatLng(space.getLatitude(),space.getLongitude());
            mMap.addMarker(new MarkerOptions().position(position).title(space.getName()));
        }

        Predicate<StudySpace> hasWifi = ss -> ss.isWifi();
        Predicate<StudySpace> hasAircon = ss -> ss.isAircon();
        Predicate<StudySpace> hasPower = ss -> ss.isPower();
        Predicate<StudySpace> hasFood = ss -> ss.isFood();
        Predicate<StudySpace> canDiscuss = ss -> ss.isDiscussion();

        Predicate<StudySpace> criteria = ss -> true;


    }
}