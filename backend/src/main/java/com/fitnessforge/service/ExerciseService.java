package com.fitnessforge.service;

import java.util.List;
import java.util.Map;

import com.fitnessforge.entity.Exercise;

/** 
 * <b>Description:</b>
 * <p>
 *  The interface providing services for {@link com.fitnessforge.entity.Exercise}
 * </p>
 * */
public interface ExerciseService {

  /** 
   * This method makes request to the 3rd party api for
   * the exercises dataset and populates the database only if 
   * sufficient number of exercises are not present in the database
   *
   * */
  void populateDataIfNeeded();

  /** 
   * This method uses pagination to fetch the fetch the data,
   * in page number and size format.
   *
   * @param page The page number of data to fetch from.
   * @param size The size of total entries to fetch.
   * @return an List {@link com.fitnessforge.entity.Exercise}
   * */
  List<Exercise> getExerciseByPage(int page, int size);

  /** 
   * This method uses pagination to fetch the fetch the data,
   * in page number and size format.
   *
   * @param page The page number of data to fetch from.
   * @param size The size of total entries to fetch.
   * @param filters Key value pair of request parameters and data.
   * @return an List {@link com.fitnessforge.entity.Exercise}
   * */
  List<Exercise> getExerciseByPage(int page, int size, Map<String, List<String>> filters);

  /** 
   * This method uses pagination to fetch the fetch the data,
   * in page number and size format.
   *
   * @return total count of the exercise in the database.
   * */
  Long getTotalExercisesCount();

  /** 
   * Fetches the {@link com.fitnessforge.entity.Exercise} by its numeric id.
   *
   * @param exerciseId the numeric id of entity {@link com.fitnessforge.entity.Exercise} 
   * @return an object of entity {@link com.fitnessforge.entity.Exercise}
   * */
  Exercise getExerciseById(Long exerciseId);
}
