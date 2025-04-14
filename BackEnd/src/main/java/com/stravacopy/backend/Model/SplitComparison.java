package com.stravacopy.backend.Model;

public class SplitComparison {
    private int index;
    private Integer speedChange;
    private Integer heartRateChange;
    private Integer distanceChange;
    // more statistical comparisons...

    public SplitComparison(int index, Integer speedChange, Integer heartRateChange, Integer distanceChange) {
        this.index = index;
        this.speedChange = speedChange;
        this.heartRateChange = heartRateChange;
        this.distanceChange = distanceChange;
    }

    // Getters
    public int getIndex() { return index; }
    public Integer getSpeedChange() { return speedChange; }
    public Integer getHeartRateChange() { return heartRateChange; }
    public Integer getDistanceChange() { return distanceChange; }
}