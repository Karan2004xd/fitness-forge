package com.fitnessforge.utils;

import java.util.HashMap;
import java.util.Map;

public class WorkoutConstants {

  public static final int AVERAGE_REP_DURATION = 45;

  public static final String LEVEL = "level";
  public static final String WORKOUT_CATEGORIES = "category";
  public static final String EQUIPMENTS = "equipment";

  // Level
  public static final String BEGINNER = "beginner";
  public static final String INTERMEDIATE = "intermediate";
  public static final String EXPERT = "expert";

  // Categories
  public static final String POWERLIFTING = "powerlifting";
  public static final String STRENGTH = "strength";
  public static final String STRETCHING = "stretching";
  public static final String CARDIO = "cardio";
  public static final String OLYMPIC_WEIGHTLIFTING = "olympic weightlifting";
  public static final String STRONGMAN = "strongman";
  public static final String POLYMETRICS = "plyometrics";

  public static final Map<String, Map<String, ExerciseRange>> exerciseMap = new HashMap<>();

  private static void addExercise(String level, String category, ExerciseRange exerciseRange) {
    exerciseMap.computeIfAbsent(category, k -> new HashMap<>()).put(level, exerciseRange);
  }

  static {
    // PowerLifting
    addExercise(BEGINNER, POWERLIFTING, new ExerciseRange(
      new int[]{2, 4},
      new int[]{5, 8}
    ));
    addExercise(INTERMEDIATE, POWERLIFTING, new ExerciseRange(
      new int[]{3, 5},
      new int[]{3, 6}
    ));
    addExercise(EXPERT, POWERLIFTING, new ExerciseRange(
      new int[]{4, 6},
      new int[]{1, 5}
    ));

    // Strength
    addExercise(BEGINNER, STRENGTH, new ExerciseRange(
      new int[]{2, 3},
      new int[]{5, 8}
    ));
    addExercise(INTERMEDIATE, STRENGTH, new ExerciseRange(
      new int[]{3, 5},
      new int[]{4, 6}
    ));
    addExercise(EXPERT, STRENGTH, new ExerciseRange(
      new int[]{4, 6},
      new int[]{3, 5}
    ));

    // Olympic Weightlifting 
    addExercise(BEGINNER, OLYMPIC_WEIGHTLIFTING, new ExerciseRange(
      new int[]{2, 3},
      new int[]{3, 5}
    ));
    addExercise(INTERMEDIATE, OLYMPIC_WEIGHTLIFTING, new ExerciseRange(
      new int[]{3, 4},
      new int[]{2, 4}
    ));
    addExercise(EXPERT, OLYMPIC_WEIGHTLIFTING, new ExerciseRange(
      new int[]{4, 6},
      new int[]{1, 3}
    ));

    // Strongman
    addExercise(BEGINNER, STRONGMAN, new ExerciseRange(
      new int[]{2, 3},
      new int[]{3, 5}
    ));
    addExercise(INTERMEDIATE, STRONGMAN, new ExerciseRange(
      new int[]{3, 4},
      new int[]{2, 4}
    ));
    addExercise(EXPERT, STRONGMAN, new ExerciseRange(
      new int[]{4, 6},
      new int[]{1, 3}
    ));

    // Polymetrics 
    addExercise(BEGINNER, POLYMETRICS, new ExerciseRange(
      new int[]{1, 2},
      new int[]{8, 12}
    ));
    addExercise(INTERMEDIATE, POLYMETRICS, new ExerciseRange(
      new int[]{2, 3},
      new int[]{10, 15}
    ));
    addExercise(EXPERT, POLYMETRICS, new ExerciseRange(
      new int[]{3, 5},
      new int[]{12, 20}
    ));

    // Stretching 
    addExercise(BEGINNER, STRETCHING, new ExerciseRange(
      new int[]{15, 20}
    ));
    addExercise(INTERMEDIATE, STRETCHING, new ExerciseRange(
      new int[]{20, 30}
    ));
    addExercise(EXPERT, STRETCHING, new ExerciseRange(
      new int[]{30, 45}
    ));

    // Cardio 
    addExercise(BEGINNER, CARDIO, new ExerciseRange(
      new int[]{15, 25}
    ));
    addExercise(INTERMEDIATE, CARDIO, new ExerciseRange(
      new int[]{25, 40}
    ));
    addExercise(EXPERT, CARDIO, new ExerciseRange(
      new int[]{45, 60}
    ));
  }

  public static ExerciseRange GetRange(String category, String level) {
    Map<String, ExerciseRange> levelMap = exerciseMap.get(category);
    if (levelMap != null) {
      return levelMap.get(level);
    }
    return null;
  }
}
