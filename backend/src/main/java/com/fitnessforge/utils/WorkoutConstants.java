package com.fitnessforge.utils;

import java.util.HashMap;
import java.util.Map;

/** 
 * <b>Description:</b>
 * <p>
 *  An Constants class which contains constants for {@link com.fitnessforge.service.WorkoutService}
 * </p>
 * */
public class WorkoutConstants {

  /** 
   * Average duaration of repitation of a exercise.
   * */
  public static final int AVERAGE_REP_DURATION = 45;


  /** 
   * Constant for the column 'level' from {@link com.fitnessforge.entity.Exercise}
   * */
  public static final String LEVEL = "level";

  /** 
   * Constant for the column 'category' from {@link com.fitnessforge.entity.Exercise}
   * */
  public static final String WORKOUT_CATEGORIES = "category";

  /** 
   * Constant for the column 'equipment' from {@link com.fitnessforge.entity.Exercise}
   * */
  public static final String EQUIPMENTS = "equipment";

  /** 
   * Constant for the column 'name' from {@link com.fitnessforge.entity.Exercise}
   * */
  public static final String EXERCISE_TO_EXCLUDE = "name";


  /** 
   * Constant for level type beginner
   * */
  public static final String BEGINNER = "beginner";

  /** 
   * Constant for level type intermediate 
   * */
  public static final String INTERMEDIATE = "intermediate";

  /** 
   * Constant for level type expert 
   * */
  public static final String EXPERT = "expert";


  /** 
   * Constant for workout category PowerLifting
   * */
  public static final String POWERLIFTING = "powerlifting";

  /** 
   * Constant for workout category Strength 
   * */
  public static final String STRENGTH = "strength";

  /** 
   * Constant for workout category Stretching 
   * */
  public static final String STRETCHING = "stretching";

  /** 
   * Constant for workout category Cardio 
   * */
  public static final String CARDIO = "cardio";

  /** 
   * Constant for workout category Olympic Weightlifting 
   * */
  public static final String OLYMPIC_WEIGHTLIFTING = "olympic weightlifting";

  /** 
   * Constant for workout category Strongman 
   * */
  public static final String STRONGMAN = "strongman";

  /** 
   * Constant for workout category Polymetrics 
   * */
  public static final String POLYMETRICS = "plyometrics";

  /** 
   * A map with composition :: 
   *  Map(Level, Map(Workout Category, {@link com.fitnessforge.utils.ExerciseRange}))
   * */
  public static final Map<String, Map<String, ExerciseRange>> exerciseMap = new HashMap<>();

  /** 
   * Empty default constructor
   * */
  public WorkoutConstants() {

  }

  /** 
   * Insert or sets the value of the exercise map.
   *
   * @param level the level of exercise.
   * @param category the workout category of the exercise.
   * @param exerciseRange an object of class {@link com.fitnessforge.utils.ExerciseRange}
   * */
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

  /** 
   * Fetches the {@link com.fitnessforge.utils.ExerciseRange} object based
   * on the workout category and level stored in the exercise map.
   *
   * @param level the level of exercise.
   * @param category the workout category of the exercise.
   * @return an object of class {@link com.fitnessforge.utils.ExerciseRange}
   * */
  public static ExerciseRange GetRange(String category, String level) {
    Map<String, ExerciseRange> levelMap = exerciseMap.get(category);
    if (levelMap != null) {
      return levelMap.get(level);
    }
    return null;
  }
}
