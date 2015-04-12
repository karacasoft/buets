package com.kompesavengers.buets.model;

/**
 * Created by triforce on 11/04/15.
 */
public class Place {
    private int id;
    private String name;
    private float coordLat;
    private float coordLong;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCoordLat() {
        return coordLat;
    }

    public void setCoordLat(float coordLat) {
        this.coordLat = coordLat;
    }

    public float getCoordLong() {
        return coordLong;
    }

    public void setCoordLong(float coordLong) {
        this.coordLong = coordLong;
    }
}
