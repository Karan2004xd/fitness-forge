package com.fitnessforge.utils;

/** 
 * <b>Description:</b>
 * <p>
 *  An Helper class for {@link com.fitnessforge.service.WorkoutService}
 *  which helps in algorithm of exercise selection, providing the necessary
 *  details of exercise like reps, sets and duration.
 * </p>
 * */
public class ExerciseRange {
  private int[] reps;
  private int[] sets;
  private int[] duration;

  /** 
   * Default Empty Constructor
   * */
  public ExerciseRange() {

  }

  /** 
   * Sets the reps and sets of exercise.
   *
   * @param reps the repetations of the exercises.
   * @param sets the number of times the reps are to be performed
   * */
  public ExerciseRange(int[] reps, int[] sets) {
    this.sets = sets;
    this.reps = reps;
  }

  /** 
   * Sets the duration of static exercises like
   * planks, holding position exercises.
   *
   * @param duration the duration of static exercise.
   * */
  public ExerciseRange(int[] duration) {
    this.duration = duration;
  }

  /** 
   * Sets the reps, sets and duration of exercise.
   *
   * @param reps the repetations of the exercises.
   * @param sets the number of times the reps are to be performed
   * @param duration the duration of static exercise.
   * */
  public ExerciseRange(int[] reps, int[] sets, int[] duration) {
    this.reps = reps;
    this.sets = sets;
    this.duration = duration;
  }

  /** 
   * Getter of private attribute reps
   *
   * @return reps of the exercise.
   * */
  public int[] getReps() {
    return reps;
  }

  /** 
   * Getter of private attribute sets 
   *
   * @return sets of the exercise.
   * */
  public int[] getSets() {
    return sets;
  }

  /** 
   * Getter of private attribute duration 
   *
   * @return duration of the exercise.
   * */
  public int[] getDuration() {
    return duration;
  }
}
