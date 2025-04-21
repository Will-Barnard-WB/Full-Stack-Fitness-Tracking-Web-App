package com.stravacopy.backend.Model;

import java.util.List;
import java.util.ArrayList;

public class RunningStats {
    private long totalDistance;
    private double avgHeartRate;
    private double avgSpeed;
    private double fastest1kPace;
    private double fastest5kPace;
    private double fastest10kPace;
    private double highestSpeed;
    private int highestHeartRate;
    private double longestDistance;

    public RunningStats(long totalDistance, double avgHeartRate, double avgSpeed, double fastest1kPace, double fastest5kPace, double fastest10kPace, double highestSpeed, int highestHeartRate, double longestDistance) {

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

    public List<SplitComparison> compareSplits(List<Split> splits) {

        List<SplitComparison> comparisons = new ArrayList<>();

        for (int i = 1; i < splits.size(); i++) {
            Split previous = splits.get(i - 1);
            Split current = splits.get(i);

            int speedChange = current.getSpeed() - previous.getSpeed();
            int heartRateChange = current.getHeartRate() - previous.getHeartRate();
            int distanceChange = current.getDistance() - previous.getDistance();

            comparisons.add(new SplitComparisonDTO(i, speedChange, heartRateChange, distanceChange));
        }

        return comparisons;
    }

    public long getTotalDistance() {
        return totalDistance;
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

    public double getHighestSpeed() {
        return highestSpeed;
    }

    public int getHighestHeartRate() {
        return highestHeartRate;
    }

    public double getLongestDistance() {
        return longestDistance;
    }


    //additional setters and getters
}

