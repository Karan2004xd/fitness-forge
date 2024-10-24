package com.fitnessforge.utils;

public class ExerciseRange {
  private int[] reps;
  private int[] sets;
  private int[] duration;

  public ExerciseRange(int[] reps, int[] sets) {
    this.sets = sets;
    this.reps = reps;
  }

  public ExerciseRange(int[] duration) {
    this.duration = duration;
  }

  public ExerciseRange(int[] reps, int[] sets, int[] duration) {
    this.reps = reps;
    this.sets = sets;
    this.duration = duration;
  }

  public int[] getReps() {
    return reps;
  }
  public int[] getSets() {
    return sets;
  }
  public int[] getDuration() {
    return duration;
  }
}
