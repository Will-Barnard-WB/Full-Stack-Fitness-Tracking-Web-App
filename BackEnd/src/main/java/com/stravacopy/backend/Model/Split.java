package com.stravacopy.backend.Model;

import java.time.LocalDateTime;

public class Split {

    private LocalDateTime timeStamp;
    private Integer Longitude;
    private Integer Latitude;
    private Integer Altitude;
    private Integer Distance;
    private Integer Speed;
    private Integer Cadence;
    private Integer HeartRate;

    // Add further attributes as needed


    public Integer getDistance() {
        return Distance;
    }

    public Integer getHeartRate() {
        return HeartRate;
    }

    public Integer getSpeed() {
        return Speed;
    }
}
