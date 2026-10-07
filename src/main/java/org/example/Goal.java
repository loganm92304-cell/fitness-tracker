package org.example;

public class Goal {
    private final String goalId;
    private final String workoutType;
    private final double targetCalories;
    private double currentCalories;

    public Goal(String goalId, String workoutType, double targetCalories) {
        if (targetCalories <= 0) {
            throw new IllegalArgumentException("Target calories must be positive.");
        }
        this.goalId = goalId;
        this.workoutType = workoutType;
        this.targetCalories = targetCalories;
        this.currentCalories = 0.0;
    }

    public void addProgress(double calories) {
        if (calories > 0) {
            this.currentCalories += calories;
        }
    }

    public boolean isAchieved() {
        return this.currentCalories >= this.targetCalories;
    }

    public String getGoalId() { return goalId; }
    public String getWorkoutType() { return workoutType; }
    public double getTargetCalories() { return targetCalories; }
    public double getCurrentCalories() { return currentCalories; }
}