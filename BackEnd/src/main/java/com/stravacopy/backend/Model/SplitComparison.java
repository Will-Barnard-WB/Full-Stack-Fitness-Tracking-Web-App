package com.stravacopy.backend.Model;

import lombok.Getter;

@Getter
public class SplitComparison {
    // Getters
    private final int index;
    private final int speedChange;
    private final int heartRateChange;
    private final int distanceChange;
    // more statistical comparisons...

    public SplitComparison(int index, int speedChange, int heartRateChange, int distanceChange) {
        this.index = index;
        this.speedChange = speedChange;
        this.heartRateChange = heartRateChange;
        this.distanceChange = distanceChange;
    }

}