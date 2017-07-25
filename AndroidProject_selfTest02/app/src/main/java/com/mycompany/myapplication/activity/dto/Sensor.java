package com.mycompany.myapplication.activity.dto;

/**
 * Created by Administrator on 2017-07-21.
 */

public class Sensor {
    private String sensorName;
    private double sensorValX;
    private double sensorValY;
    private double sensorValZ;

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public double getSensorValX() {
        return sensorValX;
    }

    public void setSensorValX(double sensorValX) {
        this.sensorValX = sensorValX;
    }

    public double getSensorValY() {
        return sensorValY;
    }

    public void setSensorValY(double sensorValY) {
        this.sensorValY = sensorValY;
    }

    public double getSensorValZ() {
        return sensorValZ;
    }

    public void setSensorValZ(double sensorValZ) {
        this.sensorValZ = sensorValZ;
    }
}
