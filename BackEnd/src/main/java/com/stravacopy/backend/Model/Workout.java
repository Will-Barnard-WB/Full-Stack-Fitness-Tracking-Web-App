package com.stravacopy.backend.Model;

import java.time.LocalDateTime;
import java.util.List;

public class Workout {

    private int id;
    private LocalDateTime dateTime;
    private List<Split> splits;
    private RunningStats runningStats;
    // Add further attributes as needed

    public Workout()
    {

    }

    public List<Split> getSplits() {
        return splits;
    }

    public RunningStats getRunningStats() {
        return runningStats;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public LocalDateTime getDateTime()
    {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime)
    {
        this.dateTime = dateTime;
    }

    public void setRunningStats(RunningStats runningStats)
    {
        this.runningStats = runningStats;
    }

    public void setSplits(List<Split> splits)
    {
        this.splits = splits;
    }
}
