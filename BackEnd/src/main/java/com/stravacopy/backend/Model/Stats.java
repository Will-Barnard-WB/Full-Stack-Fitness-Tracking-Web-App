package com.stravacopy.backend.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Stats {

    private final RunningStats runningStats;
    private final List<RunningStats> allWorkoutStats;

    public Stats(RunningStats runningStats, List<RunningStats> allWorkoutStats) {
        this.runningStats = runningStats;
        this.allWorkoutStats = allWorkoutStats;
    }

    public RunningStats getRunningStats(){
        return this.runningStats;
    }

    public List<RunningStats> getAllWorkoutStats() {
        return this.allWorkoutStats;
    }
}