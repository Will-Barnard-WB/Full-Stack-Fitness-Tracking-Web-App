package com.stravacopy.backend.Model;

import lombok.Getter;

import java.util.List;
import java.util.ArrayList;

@Getter
public class RunningStats {
    private final Double totalDistance;
    private final Double avgHeartRate;
    private final Double avgSpeed;
    private final double fastest1kPace;
    private final double fastest5kPace;
    private final double fastest10kPace;
    private final double highestSpeed;
    private final Double highestHeartRate;
    private final double longestDistance;

    public RunningStats(Double totalDistance, double avgHeartRate, double avgSpeed, double fastest1kPace, double fastest5kPace, double fastest10kPace, double highestSpeed, Double highestHeartRate, double longestDistance) {

        this.totalDistance = totalDistance;
        this.avgHeartRate = avgHeartRate;
        this.avgSpeed = avgSpeed;
        this.fastest1kPace = fastest1kPace;
        this.fastest5kPace = fastest5kPace;
        this.fastest10kPace = fastest10kPace;
        this.highestSpeed = highestSpeed;
        this.highestHeartRate = highestHeartRate;
        this.longestDistance = longestDistance;

    }

    public double getAvgHeartRate() {
        return avgHeartRate;
    }

    public double getAvgSpeed() {
        return avgSpeed;
    }

    public double getFastest1kPace() {
        return fastest1kPace;
    }

    public double getFastest5kPace() {
        return fastest5kPace;
    }

    public double getFastest10kPace() {
        return fastest10kPace;
    }

    public Double getHighestHeartRate() {
        return highestHeartRate;
    }

    public Double getTotalDistance() {
        return totalDistance;
    }

    public double getHighestSpeed() {
        return highestSpeed;
    }

    public double getLongestDistance() {
        return longestDistance;
    }

}

