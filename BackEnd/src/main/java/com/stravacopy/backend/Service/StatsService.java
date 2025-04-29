package com.stravacopy.backend.Service;

import com.stravacopy.backend.Model.*;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.Duration;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class StatsService {

    public RunningStats generateWorkOutStats(Workout workout) {
        if (workout == null) {
            return new RunningStats((double) 0, 0, 0, 0, 0, 0, 0, (double) 0, 0);
        }

        List<Split> splits = workout.getSplits();
        if (splits == null) {
            splits = Collections.emptyList();
        }

        double totalDistance = 0;
        double totalHeartRate = 0;
        double totalSpeed = 0;
        double count = 0;
        double highestSpeed = 0;
        double highestHeartRate = 0;

        for (Split split : splits) {
            if (split == null) {
                continue;
            }

            totalHeartRate += split.getHeartRate();
            totalSpeed += split.getSpeed();

            count++;

        }

        double avgHeartRate = count > 0 ? (double) totalHeartRate / count : 0;
        double avgSpeed = count > 0 ? (double) totalSpeed / count : 0;

        double fastest1kTime = GenerateFastestSegments(splits, 1000);
        double fastest5kTime = GenerateFastestSegments(splits, 5000);
        double fastest10kTime = GenerateFastestSegments(splits, 10000);

        workout.setSplitComparisons(compareSplits(splits));
        totalDistance = splits.getLast().getDistance();

        return new RunningStats(totalDistance, avgHeartRate, avgSpeed, fastest1kTime,fastest5kTime,fastest10kTime, highestSpeed, highestHeartRate, 0);
    }

    public Stats generateUserStats(List<Workout> workouts){
        if (workouts == null) {
            workouts = Collections.emptyList();
        }


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
            if (workout == null) {
                continue;
            }
            RunningStats workoutStats = generateWorkOutStats(workout);
            if (workoutStats == null) {
                continue;
            }
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
        if (splits == null || splits.isEmpty()) {
            return 0;
        }

        Double fastestTime = null;

        for (int start = 0; start < splits.size(); start++) {
            Split startSplit = splits.get(start);
            if (startSplit == null) {
                continue;
            }

            Double startTime = startSplit.getTimeStamp();
            if (startTime == null) {
                continue;
            }

            long totalDistance = 0;

            for (int end = start; end < splits.size(); end++) {
                Split endSplit = splits.get(end);
                if (endSplit == null) {
                    continue;
                }

                double endDistance = endSplit.getDistance();
                totalDistance += (long) endDistance;

                if (totalDistance >= distance) {
                    Double endTime = endSplit.getTimeStamp();
                    if (endTime != null) {
                        double segmentTime = endTime - startTime;
                        if (fastestTime == null || segmentTime < fastestTime) {
                            fastestTime = segmentTime;
                        }
                    }
                    break;
                }
            }
        }

        return fastestTime != null ? fastestTime : 0;

    }
    public Leaderboard getLeaderboardByType(String type, List<User> users) {
        if (type == null || users == null) {
            return new Leaderboard(Collections.emptyList());
        }

        List<LeaderboardEntry> leaderboard = new ArrayList<>();

        for (User user : users) {
            if (user == null) {
                continue;
            }
            RunningStats stats = user.getUserStatistics();
            if (stats == null) {
                continue;
            }
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
        if (splits == null || splits.size() < 2) {
            return Collections.emptyList();
        }

        List<SplitComparison> comparisons = new ArrayList<>();

        for (int i = 1; i < splits.size(); i++) {
            Split previous = splits.get(i - 1);
            Split current = splits.get(i);

            if (previous == null || current == null) {
                continue;
            }

            double speedChange = current.getSpeed() - previous.getSpeed();
            double heartRateChange = current.getHeartRate() - previous.getHeartRate();
            double distanceChange = current.getDistance() - previous.getDistance();

            comparisons.add(new SplitComparison((double) i, speedChange, heartRateChange, distanceChange));
        }

        return comparisons;
    }
}
