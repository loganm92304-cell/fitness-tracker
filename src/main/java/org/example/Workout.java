package org.example;

import java.time.LocalDate;

public class Workout {
    private final String id;
    private final String type;
    private final int durationMinutes;
    private final double caloriesBurned;
    private final LocalDate date;

    public Workout(String id, String type, int durationMinutes, double caloriesBurned, LocalDate date) {
        if (durationMinutes <= 0) {
            throw new IllegalArgumentException("Duration must be greater than zero.");
        }
        if (caloriesBurned < 0) {
            throw new IllegalArgumentException("Calories burned cannot be negative.");
        }
        this.id = id;
        this.type = type;
        this.durationMinutes = durationMinutes;
        this.caloriesBurned = caloriesBurned;
        this.date = date;
    }

    public String getId() { return id; }
    public String getType() { return type; }
    public int getDurationMinutes() { return durationMinutes; }
    public double getCaloriesBurned() { return caloriesBurned; }
    public LocalDate getDate() { return date; }
}