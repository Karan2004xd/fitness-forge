package com.fitnessforge.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitnessforge.entity.Exercise;
import com.fitnessforge.entity.Workout;
import com.fitnessforge.exception.DatabaseException;
import com.fitnessforge.exception.DatabaseExceptionTypes;
import com.fitnessforge.repository.WorkoutRepository;
import com.fitnessforge.utils.FetchEntityUtil;

@Service
public class WorkoutServiceImpl implements WorkoutService {

  @Autowired
  private WorkoutRepository workoutRepository;

  @Override
  public Workout createNewWorkout(Workout workout) {
    try {
      FetchEntityUtil.GetEntity(workoutRepository.findByName(workout.getName()), Workout.class);
      throw new DatabaseException(DatabaseExceptionTypes.MEMBER_ALREADY_EXISTS, WorkoutService.class.getName());
    } catch (DatabaseException e) {
      return workoutRepository.save(workout);
    }
  }

  @Override
  public Workout getWorkout(String name) {
    Workout workout = FetchEntityUtil.GetEntity(workoutRepository.findByName(name), Workout.class);
    return workout;
  }
  
  @Override
  public Workout getWorkout(Long id) {
    Workout workout = FetchEntityUtil.GetEntity(workoutRepository.findById(id), Workout.class);
    return workout;
  }


  @Override
  public Map<String, List<Exercise>> getWorkoutExercises(int page, int size, Workout workout) {
    return null;
  }
}
