package com.stravacopy.backend.model;

public class RunningStats {
    private long totalDistance;
    private double avgHeartRate;

    public RunningStats(long totalDistance, double avgHeartRate) {
        this.totalDistance = totalDistance;
        this.avgHeartRate = avgHeartRate;
    }

    public long getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(long totalDistance) {
        this.totalDistance = totalDistance;
    }

    public double getAvgHeartRate() {
        return avgHeartRate;
    }

    public void setAvgHeartRate(double avgHeartRate) {
        this.avgHeartRate = avgHeartRate;
    }
}
