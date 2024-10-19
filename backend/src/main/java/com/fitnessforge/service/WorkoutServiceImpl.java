package com.fitnessforge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    Workout checkWorkout = FetchEntityUtil.GetEntity(workoutRepository.findByName(workout.getName()), Workout.class);

    if (checkWorkout != null) {
      throw new DatabaseException(DatabaseExceptionTypes.MEMBER_ALREADY_EXISTS, WorkoutService.class.getName());
    }
    return workoutRepository.save(workout);
  }
}
