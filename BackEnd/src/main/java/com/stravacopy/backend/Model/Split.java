package com.stravacopy.backend.Model;

import java.time.LocalDateTime;

public class Split {

    private final int timeStamp;
    private final long positionLong;
    private final long positionLat;
    private final long distance;
    private final long altitude;
    private final long speed;
    private final int cadence;
    private final int heartRate;

    public Split(int timeStamp, long positionLong, long positionLat, long distance, long altitude, long speed, int cadence, int heartRate) {
        this.timeStamp = timeStamp;
        this.positionLong = positionLong;
        this.positionLat = positionLat;
        this.distance = distance;
        this.altitude = altitude;
        this.speed = speed;
        this.cadence = cadence;
        this.heartRate = heartRate;
    }

    public int getCadence() {
        return cadence;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public long getDistance() {
        return distance;
    }

    public int getTimeStamp() {
        return timeStamp;
    }

    public long getAltitude() {
        return altitude;
    }

    public long getPositionLat() {
        return positionLat;
    }

    public long getPositionLong() {
        return positionLong;
    }

    public long getSpeed() {
        return speed;
    }
}
