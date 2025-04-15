package com.stravacopy.backend.Model;

import java.time.LocalDateTime;
import java.util.List;

public class Workout {

    private int id;
    private LocalDateTime dateTime;
    private List<Split> splits;
    private RunningStats runningStats;
    // Add further attributes as needed


    public List<Split> getSplits() {
        return splits;
    }

    public RunningStats getRunningStats() {
        return runningStats;
    }
}
