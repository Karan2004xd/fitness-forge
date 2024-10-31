package com.fitnessforge.service;

import java.util.List;
import java.util.Map;

import com.fitnessforge.entity.Workout;

/** 
 * <b>Description:</b>
 * <p>
 *  The interface providing services for {@link com.fitnessforge.entity.Workout}
 * </p>
 * */
public interface WorkoutService {

  /** 
   * Creates a new workout entry.
   *
   * @param workout an object of {@link com.fitnessforge.entity.Workout} entity
   * @param memberId the id of entity {@link com.fitnessforge.entity.Member}
   * @return an object of {@link com.fitnessforge.entity.Workout} entity
   * */
  Workout createNewWorkout(Workout workout, Long memberId);

  /** 
   * Fetches the workout by its id.
   *
   * @param id the id of the workout
   * @return an object of {@link com.fitnessforge.entity.Workout} entity
   * */
  Workout getWorkout(Long id);

  /** 
   * Fetches the workout by its name.
   *
   * @param name name of the workout
   * @return an object of {@link com.fitnessforge.entity.Workout} entity
   * */
  Workout getWorkout(String name);

  /** 
   * Fetches the exercises based on workout template defined,
   * which is passed in the parameter.
   *
   * @param workout an object of {@link com.fitnessforge.entity.Workout} entity
   * @return an list of map of category and list of Exercise ( List(Map(String, List(Object))) )
   * */
  List<Map<String, Object>> getWorkoutExercises(Workout workout);

  /** 
   * Updates the existing workout with new workout passed
   *
   * @param newWorkout an object of {@link com.fitnessforge.entity.Workout} entity
   * @return an object of {@link com.fitnessforge.entity.Workout} entity
   * */
  Workout updateWorkout(Workout newWorkout);

  /** 
   * Deletes the existing workout by its id.
   *
   * @param id the id of the workout
   * @param memberId the id of entity {@link com.fitnessforge.entity.Member}
   * */
  void deleteWorkout(Long id, Long memberId);
}
