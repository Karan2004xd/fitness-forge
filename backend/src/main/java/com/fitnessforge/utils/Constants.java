package com.fitnessforge.utils;

/** 
 * <b>Description:</b>
 * <p>
 *  An Constants class which contains constants globally shared by all classes.
 * </p>
 * */
public class Constants {

  /** 
   * Empty defatult constructor
   * */
  public Constants() {}

  /** 
   * The minimum threshold of the required exercises in the database.
   * */
  public static final int REQUIRED_EXERCISES_COUNT = 500;

  /** 
   * The API endpoint from where the exercise data is fetched from
   *
   * (For more information: https://github.com/yuhonas/free-exercise-db)
   * */
  public static final String EXERCISE_DB_URL_ENDPOINT = "https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/dist/exercises.json";
}
