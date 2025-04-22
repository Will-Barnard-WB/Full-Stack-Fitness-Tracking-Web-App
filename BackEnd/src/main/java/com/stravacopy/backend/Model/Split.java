package com.stravacopy.backend.Model;

import org.springframework.data.mongodb.core.aggregation.ArrayOperators;

import java.time.LocalDateTime;

public class Split {

    private LocalDateTime timeStamp;
    private Integer Longitude;
    private Integer Latitude;
    private Integer Altitude;
    private int distance;
    private int speed;
    //private int cadence;
    private int heartRate;

    public Split(LocalDateTime timeStamp, int distance, int speed, int heartRate)
    {
        this.timeStamp = timeStamp;
        this.distance = distance;
        this.speed = speed;
        this.heartRate = heartRate;

    }
    public Integer getDistance() {
        return distance;
    }

    public Integer getSpeed() {
        return speed;
    }

    public Integer getHeartRate() {
        return heartRate;
    }

    //public Integer getCadence() {
        //return cadence;
    //}

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    @Override
    public String toString() {
        return "Split{" +
                "timeStamp=" + timeStamp +
                ", Longitude=" + Longitude +
                ", Latitude=" + Latitude +
                ", Altitude=" + Altitude +
                ", distance=" + distance +
                ", speed=" + speed +
                ", heartRate=" + heartRate +
                '}';
    }

    // Add further attributes as needed
}
