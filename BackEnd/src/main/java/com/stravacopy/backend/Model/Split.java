package com.stravacopy.backend.Model;

import org.springframework.data.mongodb.core.aggregation.ArrayOperators;

import java.time.LocalDateTime;

public class Split {

    private LocalDateTime timeStamp;
    private int longitude;
    private int latitude;
    private int altitude;
    private int distance;
    private int speed;
    private int cadence;
    private int heartRate;

    public Split(LocalDateTime timeStamp, int distance, int speed, int heartRate)
    {
        this.timeStamp = timeStamp;
        this.distance = distance;
        this.speed = speed;
        this.heartRate = heartRate;

    }

    public Split(LocalDateTime timeStamp, int longitude, int latitude, int altitude,
                 int distance, int speed,int cadence, int heartRate) {
        this.timeStamp = timeStamp;
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
        this.distance = distance;
        this.speed = speed;
        this.cadence = cadence;
        this.heartRate = heartRate;
    }

    public int getDistance() {
        return distance;
    }

    public int getSpeed() {
        return speed;
    }

    public int getHeartRate() {
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
                ", Longitude=" + longitude +
                ", Latitude=" + latitude +
                ", Altitude=" + altitude +
                ", distance=" + distance +
                ", speed=" + speed +
                ", heartRate=" + heartRate +
                '}';
    }

    // Add further attributes as needed
}
