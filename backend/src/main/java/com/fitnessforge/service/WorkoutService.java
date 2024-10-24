package com.fitnessforge.service;

import java.util.List;
import java.util.Map;

import com.fitnessforge.entity.Exercise;
import com.fitnessforge.entity.Workout;

public interface WorkoutService {

  Workout createNewWorkout(Workout workout);

  Workout getWorkout(Long id);

  Workout getWorkout(String name);

  List<Map<String, List<Exercise>>> getWorkoutExercises(Workout workout);
}
