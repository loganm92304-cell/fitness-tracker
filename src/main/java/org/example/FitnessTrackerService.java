package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FitnessTrackerService {
    private final List<Workout> workouts = new ArrayList<>();
    private final List<Goal> goals = new ArrayList<>();

    public void logWorkout(Workout workout) {
        if (workout == null) {
            throw new IllegalArgumentException("Workout cannot be null.");
        }
        workouts.add(workout);

        for (Goal goal : goals) {
            if (goal.getWorkoutType().equalsIgnoreCase(workout.getType())) {
                goal.addProgress(workout.getCaloriesBurned());
            }
        }
    }

    public void setGoal(Goal goal) {
        if (goal == null) {
            throw new IllegalArgumentException("Goal cannot be null.");
        }
        goals.add(goal);
    }

    public List<Workout> getWorkoutsByType(String type) {
        return workouts.stream()
                .filter(w -> w.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    public double getTotalCaloriesBurned() {
        return workouts.stream()
                .mapToDouble(Workout::getCaloriesBurned)
                .sum();
    }

    public Optional<Goal> findGoalById(String goalId) {
        return goals.stream()
                .filter(g -> g.getGoalId().equals(goalId))
                .findFirst();
    }

    public List<Workout> getAllWorkouts() {
        return new ArrayList<>(workouts);
    }

    public List<Goal> getAllGoals() {
        return new ArrayList<>(goals);
    }
}