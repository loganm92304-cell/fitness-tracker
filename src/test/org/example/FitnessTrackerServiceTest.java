package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class FitnessTrackerServiceTest {

    private FitnessTrackerService trackerService;

    @BeforeEach
    void setUp() {
        trackerService = new FitnessTrackerService();
    }

    @Test
    @DisplayName("Positive: Successfully log a valid workout")
    void testLogWorkout_Success() {
        Workout workout = new Workout("W1", "Running", 30, 300.0, LocalDate.now());
        trackerService.logWorkout(workout);

        assertEquals(1, trackerService.getAllWorkouts().size());
        assertEquals("Running", trackerService.getAllWorkouts().get(0).getType());
    }

    @Test
    @DisplayName("Negative: Throw exception when logging null workout")
    void testLogWorkout_NullThrowsException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> trackerService.logWorkout(null)
        );
        assertEquals("Workout cannot be null.", exception.getMessage());
    }

    @Test
    @DisplayName("Negative: Throw exception for non-positive duration")
    void testWorkoutCreation_InvalidDurationThrowsException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Workout("W2", "Cycling", 0, 150.0, LocalDate.now())
        );
    }

    @Test
    @DisplayName("Positive: Calculate total calories burned across multiple workouts")
    void testGetTotalCaloriesBurned() {
        trackerService.logWorkout(new Workout("W1", "Running", 30, 300.0, LocalDate.now()));
        trackerService.logWorkout(new Workout("W2", "Swimming", 45, 450.0, LocalDate.now()));

        assertEquals(750.0, trackerService.getTotalCaloriesBurned(), 0.001);
    }

    @Test
    @DisplayName("Positive: Filter workouts correctly by type")
    void testGetWorkoutsByType() {
        trackerService.logWorkout(new Workout("W1", "Running", 30, 300.0, LocalDate.now()));
        trackerService.logWorkout(new Workout("W2", "Yoga", 60, 150.0, LocalDate.now()));
        trackerService.logWorkout(new Workout("W3", "Running", 45, 450.0, LocalDate.now()));

        List<Workout> runningWorkouts = trackerService.getWorkoutsByType("Running");
        assertEquals(2, runningWorkouts.size());
    }

    @Test
    @DisplayName("Positive: Goal progress updates automatically upon logging a workout")
    void testGoalProgressAutoUpdates() {
        Goal goal = new Goal("G1", "Running", 500.0);
        trackerService.setGoal(goal);

        trackerService.logWorkout(new Workout("W1", "Running", 30, 300.0, LocalDate.now()));

        Optional<Goal> retrievedGoal = trackerService.findGoalById("G1");
        assertTrue(retrievedGoal.isPresent());
        assertEquals(300.0, retrievedGoal.get().getCurrentCalories(), 0.001);
        assertFalse(retrievedGoal.get().isAchieved());
    }

    @Test
    @DisplayName("Positive: Goal marks as achieved when target calories are met")
    void testGoalAchievement() {
        Goal goal = new Goal("G1", "Cycling", 200.0);
        trackerService.setGoal(goal);

        trackerService.logWorkout(new Workout("W1", "Cycling", 40, 250.0, LocalDate.now()));

        Optional<Goal> retrievedGoal = trackerService.findGoalById("G1");
        assertTrue(retrievedGoal.isPresent());
        assertTrue(retrievedGoal.get().isAchieved());
    }

    @Test
    @DisplayName("Negative: Setting invalid target calories throws exception")
    void testGoalCreation_InvalidTargetThrowsException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Goal("G2", "Walking", -100.0)
        );
    }

    @Test
    @DisplayName("Negative: Finding non-existent goal returns empty optional")
    void testFindGoalById_NotFound() {
        Optional<Goal> goal = trackerService.findGoalById("NON_EXISTENT");
        assertTrue(goal.isEmpty());
    }

    @Test
    @DisplayName("Positive & Structural: Verify properties of a newly created workout")
    void testWorkoutProperties_AssertAll() {
        LocalDate now = LocalDate.now();
        Workout workout = new Workout("W99", "HIIT", 20, 200.0, now);

        assertAll("Workout Properties",
                () -> assertEquals("W99", workout.getId()),
                () -> assertEquals("HIIT", workout.getType()),
                () -> assertEquals(20, workout.getDurationMinutes()),
                () -> assertEquals(200.0, workout.getCaloriesBurned()),
                () -> assertNotNull(workout.getDate())
        );
    }
}