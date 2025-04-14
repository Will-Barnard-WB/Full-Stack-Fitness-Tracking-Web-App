package com.stravacopy.backend.model;

public class RunningStats {
    private long totalDistance;
    private double avgHeartRate;

    public RunningStats(long totalDistance, double avgHeartRate) {
        this.totalDistance = totalDistance;
        this.avgHeartRate = avgHeartRate;
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
        // might add more to comparisons like personal best split and max/min of data

        return comparisons;
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

    //additional setters and getters
}
