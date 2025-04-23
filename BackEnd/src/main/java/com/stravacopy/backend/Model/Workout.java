package com.stravacopy.backend.Model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Workout {

    private String id;
    private LocalDateTime dateTime;
    private List<Split> splits;
    private List<SplitComparison> splitComparisons;
    // Add further attributes as needed

    public Workout(String id)
    {
        this.splits = new ArrayList<Split>();
        LocalDateTime time = LocalDateTime.now();
        this.splits.add(new Split(10,12,13));
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

    @Override
    public String toString() {
        return "Workout{" +
                "id='" + id + '\'' +
                ", dateTime=" + dateTime +
                ", splits=" + splits +
                ", splitCompareStats=" + splitComparisons +
                '}';
    }

    public ArrayList<Split> initiateSplits(){
        ArrayList<Split> emptySplits = new ArrayList<Split>(); // ADD SPLITS HERE!!!
        return emptySplits;
    }
}
