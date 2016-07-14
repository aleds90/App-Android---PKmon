package com.aleds90.android.pokemonhelper.model;

public class Gym {

    private int id;
    private String address;
    private double longitude;
    private double latitude;
    private int level;
    private String notes;

    public Gym(){}

    public Gym(String address, double longitude, long latitude, int level, String notes){
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.level = level;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
