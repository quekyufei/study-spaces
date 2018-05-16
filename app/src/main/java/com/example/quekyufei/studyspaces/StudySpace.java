package com.example.quekyufei.studyspaces;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.android.gms.maps.model.Marker;

import java.io.Serializable;

@Entity
public class StudySpace implements Serializable {
    @PrimaryKey
    @NonNull
    private int id;
    private String name;
    private double latitude;
    private double longitude;
    private int capacity;
    private String wifi;
    private String aircon;
    private String power;
    private String food;
    private String discussion;

    @Ignore
    private Marker mapsMarker;

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isWifi() {
        return Boolean.parseBoolean(wifi);
    }

    public boolean isAircon() {
        return Boolean.parseBoolean(aircon);
    }

    public boolean isPower() {
        return Boolean.parseBoolean(power);
    }

    public boolean isFood() {
        return Boolean.parseBoolean(food);
    }

    public boolean isDiscussion() {
        return Boolean.parseBoolean(discussion);
    }

    public Marker getMapsMarker() {
        return mapsMarker;
    }

    public void setMapsMarker(Marker mapsMarker) {
        this.mapsMarker = mapsMarker;
    }

    public String getWifi() {
        return wifi;
    }

    public String getAircon() {
        return aircon;
    }

    public String getPower() {
        return power;
    }

    public String getFood() {
        return food;
    }

    public String getDiscussion() {
        return discussion;
    }

    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    public void setAircon(String aircon) {
        this.aircon = aircon;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public void setDiscussion(String discussion) {
        this.discussion = discussion;
    }
}
