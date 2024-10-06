package com.fitnessforge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fitnessforge.entity.Exercise;
import com.fitnessforge.repository.ExerciseRepository;
import com.fitnessforge.utils.Constants;

@Service
public class ExerciseServiceImpl implements ExerciseService {

  @Autowired
  private ExerciseRepository exerciseRepository;

  @Autowired
  private RestTemplate restTemplate;

  @Override
  public void populateDataIfNeeded() {
    Long count = exerciseRepository.getDataCount();

    if (count < Constants.REQUIRED_EXERCISES_COUNT) {
      Exercise[] exercises = restTemplate.getForObject(Constants.EXERCISE_DB_URL_ENDPOINT, Exercise[].class);

      exerciseRepository.saveAll(List.of(exercises));
    }
  }
}
