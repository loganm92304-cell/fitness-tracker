# Fitness Tracker Application

A simple Java application designed to track fitness activities, log workouts, set target goals, and monitor calorie burning progress without external frameworks.

## Project Description
The **Fitness Tracker Application** manages user workout logs and fitness goals using Object-Oriented Programming (OOP) principles.

Key functionality includes:
- Logging workouts (Duration, Activity Type, Calories Burned, Date).
- Setting fitness goals for specific activities.
- Automatic progress tracking on active goals when matching workouts are logged.
- Filtering workouts by activity type and calculating aggregate metrics.

---

## Clean Code Practices

### 1. Single Responsibility Principle (SRP)
Each class has a single, clearly defined role:
- `Workout`: Immutable model storing individual workout activity metadata.
- `Goal`: Manages targeted workout goals and calorie accumulation tracking.
- `FitnessTrackerService`: Handles business logic, query streams, and coordination between workouts and goals.

### 2. Guard Clauses & Input Validation (Defensive Coding)
Constructors enforce boundaries immediately upon object creation to maintain invalid object state prevention:
```java
if (durationMinutes <= 0) {
    throw new IllegalArgumentException("Duration must be greater than zero.");
}
```

### 3. Meaningful Naming & Encapsulation
Fields are marked `private final` where appropriate to enforce immutability, and method names explicitly convey behavior (`getTotalCaloriesBurned()`, `getWorkoutsByType()`).

---

## Unit Test Suite Overview

The test suite consists of **10 JUnit 5 tests** covering both positive and negative scenarios:

| Test Name | Test Type | Assertions Used | Goal/Description |
| :--- | :--- | :--- | :--- |
| `testLogWorkout_Success` | Positive | `assertEquals` | Validates standard workout logging functionality |
| `testLogWorkout_NullThrowsException` | Negative | `assertThrows`, `assertEquals` | Ensures passing null throws an exception |
| `testWorkoutCreation_InvalidDurationThrowsException` | Negative | `assertThrows` | Ensures zero or negative duration throws an exception |
| `testGetTotalCaloriesBurned` | Positive | `assertEquals` | Verifies aggregate calorie sum calculations |
| `testGetWorkoutsByType` | Positive | `assertEquals` | Verifies stream filtering by activity type |
| `testGoalProgressAutoUpdates` | Positive | `assertTrue`, `assertEquals`, `assertFalse` | Tests automatic progress updates when a workout is logged |
| `testGoalAchievement` | Positive | `assertTrue` | Verifies goal mark as achieved when threshold is met |
| `testGoalCreation_InvalidTargetThrowsException` | Negative | `assertThrows` | Ensures non-positive calorie goals throw an exception |
| `testFindGoalById_NotFound` | Negative | `assertTrue` | Ensures missing goals return empty `Optional` |
| `testWorkoutProperties_AssertAll` | Structural | `assertAll`, `assertEquals`, `assertNotNull` | Tests property assignment across all constructor fields |

---

## Dependencies

- **JUnit 5 Jupiter API & Engine** (`org.junit.jupiter:junit-jupiter-api` & `junit-jupiter-engine`, version `5.10.2`): Used for unit test execution and assertion declarations. Dependency artifacts are fetched automatically via Maven Central Repository.
- **Apache Maven Surefire Plugin** (`maven-surefire-plugin`, version `3.2.5`): Used during the Maven build phase (`mvn test`) to discover and execute JUnit tests automatically.

---

## Troubleshooting & QAP Reflections

During initial GitHub Actions configuration, verifying that Java JDK version alignment matched local environment setup (`JDK 17`) ensured seamless build execution inside Ubuntu runner instances. Writing assertions with strict exception checking verified edge cases reliably.