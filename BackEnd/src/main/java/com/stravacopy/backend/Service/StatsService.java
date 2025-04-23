package com.stravacopy.backend.Service;

import com.stravacopy.backend.Model.*;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.Duration;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class StatsService {

    public RunningStats generateWorkOutStats(Workout workout) {
        List<Split> splits = workout.getSplits();

        double totalDistance = 0;
        double totalHeartRate = 0;
        double totalSpeed = 0;
        double count = 0;
        double fastest1kTime = 0;
        double fastest5kTime = 0;
        double fastest10kTime = 0;
        double highestSpeed = 0;
        double highestHeartRate = 0;

        if (splits != null) {
            for (Split split : splits) {

                totalDistance += split.getDistance();
                totalHeartRate += split.getHeartRate();
                totalSpeed += split.getSpeed();

                if (highestSpeed > split.getSpeed()) {
                    highestSpeed = split.getSpeed();
                }
                if (highestHeartRate > split.getHeartRate()) {
                    highestHeartRate = split.getHeartRate();
                }

                count++;

            }
        }

        double avgHeartRate = count > 0 ? (double) totalHeartRate / count : 0;
        double avgSpeed = count > 0 ? (double) totalSpeed / count : 0;

        fastest1kTime = GenerateFastestSegments(splits, 1000);
        fastest5kTime = GenerateFastestSegments(splits, 5000);
        fastest10kTime = GenerateFastestSegments(splits, 10000);

        workout.setSplitComparisons(compareSplits(splits));

        return new RunningStats(totalDistance, avgHeartRate, avgSpeed, fastest1kTime,fastest5kTime,fastest10kTime, highestSpeed, highestHeartRate, 0);
    }

    public Stats generateUserStats(List<Workout> workouts){

        double totalDistance = 0;
        double totalHeartRate = 0;
        double totalSpeed = 0;
        double count = 0;

        double fastest1kTime = 0;
        double fastest5kTime = 0;
        double fastest10kTime = 0;
        double longestDistance = 0;
        double highestSpeed = 0;
        double highestHeartRate = 0;

        List<RunningStats> workoutStatList = new ArrayList<>();

        for (Workout workout: workouts) {

            RunningStats workoutStats = generateWorkOutStats(workout);
            workoutStatList.add(workoutStats);

            double workoutDistance = workoutStats.getTotalDistance();
            double workoutAvgSpeed = workoutStats.getAvgSpeed();
            double workoutMaxSpeed = workoutStats.getHighestSpeed();
            double workoutHighestHeartRate = workoutStats.getHighestHeartRate();
            double workout1kTime = workoutStats.getFastest1kPace();
            double workout5kTime = workoutStats.getFastest5kPace();
            double workout10kTime = workoutStats.getFastest10kPace();

            totalDistance += workoutDistance;
            totalHeartRate += workoutStats.getAvgHeartRate();
            totalSpeed += workoutAvgSpeed;

            if (workoutDistance > longestDistance){
                longestDistance = workoutDistance;
            }
            if (workoutMaxSpeed > highestSpeed){
                highestSpeed = workoutMaxSpeed;
            }
            if (highestHeartRate > workoutHighestHeartRate){
                highestHeartRate = workoutHighestHeartRate;
            }
            if (workout1kTime > fastest1kTime){
                fastest1kTime = workout1kTime;
            }
            if (workout5kTime > fastest5kTime){
                fastest5kTime = workout5kTime;
            }
            if (workout10kTime > fastest10kTime){
                fastest10kTime = workout10kTime;
            }

            count++;
        }

        double avgHeartRate = count > 0 ? totalHeartRate / count : 0;
        double avgSpeed = count > 0 ? totalSpeed / count : 0;

        RunningStats runningStats = new RunningStats(totalDistance, avgHeartRate, avgSpeed, fastest1kTime, fastest5kTime, fastest10kTime, highestSpeed, highestHeartRate, longestDistance);
        return new Stats(runningStats,workoutStatList);
    }

    public double GenerateFastestSegments(List<Split> splits, long distance){
        Double fastestTime = null;

        for (int start = 0; start < splits.size(); start++) {
            long totalDistance = 0;
            Double startTime = splits.get(start).getTimeStamp();

            for (int end = start; end < splits.size(); end++) {
                totalDistance += splits.get(end).getDistance();

                if (totalDistance >= distance) {
                    Double endTime = splits.get(end).getTimeStamp();
                    Double segmentTime = endTime - startTime;

                    if (fastestTime == null || segmentTime.compareTo(fastestTime) < 0) {
                        fastestTime = segmentTime;
                    }

                    break;
                }
            }
        }

        assert fastestTime != null;
        return fastestTime;

    }
    public Leaderboard getLeaderboardByType(String type, List<User> users) {
        List<LeaderboardEntry> leaderboard = new ArrayList<>();

        for (User user : users) {
            RunningStats stats = user.getUserStatistics();
            double value = switch (type.toLowerCase()) {
                case "fastest5k" -> stats.getFastest5kPace();
                case "fastest1k" -> stats.getFastest1kPace();
                case "fastest10k" -> stats.getFastest10kPace();
                case "totaldistance" -> stats.getTotalDistance();
                case "topspeed" -> stats.getHighestSpeed();
                default -> -1;
            };

            if (value > 0) {
                leaderboard.add(new LeaderboardEntry(user.getName(), value));
            }
        }

        // Sorting: fastest times = ascending
        // everything else = descending
        if (type.toLowerCase().startsWith("fastest")) {
            leaderboard.sort(Comparator.comparing(LeaderboardEntry::getValue));
        } else {
            leaderboard.sort(Comparator.comparing(LeaderboardEntry::getValue).reversed());
        }

        return new Leaderboard(leaderboard);
    }

    public List<SplitComparison> compareSplits(List<Split> splits) {

        List<SplitComparison> comparisons = new ArrayList<>();

        for (int i = 1; i < splits.size(); i++) {
            Split previous = splits.get(i - 1);
            Split current = splits.get(i);

            double speedChange = current.getSpeed() - previous.getSpeed();
            double heartRateChange = current.getHeartRate() - previous.getHeartRate();
            double distanceChange = current.getDistance() - previous.getDistance();

            comparisons.add(new SplitComparison((double) i, speedChange, heartRateChange, distanceChange));
        }

        return comparisons;
    }
}
