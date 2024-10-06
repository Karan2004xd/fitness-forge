package com.fitnessforge.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fitnessforge.repository.ExerciseRepository;

public class ExerciseServiceTest {

  @Mock
  private ExerciseRepository exerciseRepository;

  @InjectMocks
  private ExerciseService exerciseService = new ExerciseServiceImpl();

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void getCountTest() {
    Long count = (long) 873;
    when(exerciseRepository.getDataCount()).thenReturn(count);
    Long testCount = exerciseService.getTotalExercisesCount();

    assertNotNull(testCount);
    assertEquals(count, testCount);
  }
}
