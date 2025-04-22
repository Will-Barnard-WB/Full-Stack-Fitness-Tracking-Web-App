package com.stravacopy.backend.Model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class Workout {

    private int id;
    private LocalDateTime dateTime;
    private List<Split> splits;
    private RunningStats runningStats;
    // Add further attributes as needed

    public Workout()
    {

    }

}
