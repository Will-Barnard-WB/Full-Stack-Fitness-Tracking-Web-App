package com.stravacopy.backend.Model;

import lombok.Getter;

@Getter
public class SplitComparison {
    // Getters
    private final Double index;
    private final Double speedChange;
    private final Double heartRateChange;
    private final Double distanceChange;
    // more statistical comparisons...

    public SplitComparison(Double index, Double speedChange, Double heartRateChange, Double distanceChange) {
        this.index = index;
        this.speedChange = speedChange;
        this.heartRateChange = heartRateChange;
        this.distanceChange = distanceChange;
    }

}