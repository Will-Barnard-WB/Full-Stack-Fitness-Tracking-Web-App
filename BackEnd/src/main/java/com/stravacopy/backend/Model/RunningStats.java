package com.stravacopy.backend.Model;

import lombok.Getter;

import java.util.List;
import java.util.ArrayList;

@Getter
public class RunningStats {
    private final long totalDistance;
    private final double avgHeartRate;
    private final double avgSpeed;
    private final double fastest1kPace;
    private final double fastest5kPace;
    private final double fastest10kPace;
    private final double highestSpeed;
    private final int highestHeartRate;
    private final double longestDistance;

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

            comparisons.add(new SplitComparison(i, speedChange, heartRateChange, distanceChange));
        }

        return comparisons;
    }

}

