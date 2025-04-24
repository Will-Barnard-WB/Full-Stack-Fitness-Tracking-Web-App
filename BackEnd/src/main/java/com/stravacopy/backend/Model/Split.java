package com.stravacopy.backend.Model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;

import java.time.LocalDateTime;

@Setter
@Getter
public class Split
{

    private Double timeStamp;
    private Double longitude;
    private Double latitude;
    private Double altitude;
    private Double distance;
    private Double speed;
    private Double cadence;
    private Double sumDuration;

    private Double heartRate;

    public Split( double distance, double speed, double heartRate)
    {
        //this.timeStamp = timeStamp;
        this.distance = distance;
        this.speed = speed;
        this.heartRate = heartRate;

    }

    public Split()
    {

    }

    public Split(double timeStamp, double longitude, double latitude, double altitude,
                 double distance, double speed, double cadence, double heartRate)
    {
        this.timeStamp = timeStamp;
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
        this.distance = distance;
        this.speed = speed;
        this.cadence = cadence;
        this.heartRate = heartRate;
    }

    public double getDistance()
    {
        return distance;
    }

    public double getSpeed()
    {
        return speed;
    }

    //public Integer getCadence() {
    //return cadence;
    //}

    //public LocalDateTime getTimeStamp()
    //{
    //    return timeStamp;
    //}


    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    public void setCadence(Double cadence) {
        this.cadence = cadence;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public void setHeartRate(Double heartRate) {
        this.heartRate = heartRate;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public void setSumDuration(Double sumDuration) {
        this.sumDuration = sumDuration;
    }

    public void setTimeStamp(Double timeStamp){
        this.timeStamp = timeStamp;
    }

    public Double getTimeStamp(){
        return this.timeStamp;
    }

    @Override
    public String toString()
    {
        return "Split{" +
                ", Longitude=" + longitude +
                ", Latitude=" + latitude +
                ", Altitude=" + altitude +
                ", distance=" + distance +
                ", speed=" + speed +
                ", heartRate=" + heartRate +
                '}';
    }

    public Double getHeartRate() {
        return this.heartRate;
    }

    // Add further attributes as needed
}
