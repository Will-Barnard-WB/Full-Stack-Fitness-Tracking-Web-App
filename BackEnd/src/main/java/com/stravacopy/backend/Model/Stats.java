package com.stravacopy.backend.Model;

import lombok.Getter;

import java.util.List;

@Getter
public class Stats {

    private final RunningStats runningStats;
    private final List<RunningStats> allWorkoutStats;

    public Stats(RunningStats runningStats, List<RunningStats> allWorkoutStats) {
        this.runningStats = runningStats;
        this.allWorkoutStats = allWorkoutStats;
    }

}