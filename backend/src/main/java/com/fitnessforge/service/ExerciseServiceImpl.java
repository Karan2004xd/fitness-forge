package com.fitnessforge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fitnessforge.repository.ExerciseRepository;

@Service
public class ExerciseServiceImpl implements ExerciseService {

  @Autowired
  private ExerciseRepository exerciseRepository;
}
