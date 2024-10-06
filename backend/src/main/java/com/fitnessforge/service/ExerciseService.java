package com.fitnessforge.service;

import com.fitnessforge.entity.Exercise;

public interface ExerciseService {
  void populateDataIfNeeded();
  Exercise getExerciseById(Long id);
}
