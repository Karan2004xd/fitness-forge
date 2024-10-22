package com.fitnessforge.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fitnessforge.entity.Workout;
import com.fitnessforge.repository.WorkoutRepository;

public class WorkoutServiceTest {

  @Mock
  private WorkoutRepository workoutRepository;

  @InjectMocks
  private WorkoutService workoutService = new WorkoutServiceImpl();

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  private Workout getDummyWorkout() {
    Workout workout = new Workout();
    Long id = 1L;
    String name = "testWorkout";

    List<String> workoutCategories = List.of("strength");
    List<String> workoutDays = List.of("monday","tuesday");

    int duration = 60;
    int reps = 12;

    List<String> equipments = List.of("dumbell", "machine");
    int restDuration = 30;

    List<String> cardioDays = List.of("monday");
    List<String> level = List.of("beginner");

    int sets = 3;

    workout.setId(id);
    workout.setName(name);
    workout.setCardioDays(cardioDays);
    workout.setWorkoutCategories(workoutCategories);
    workout.setWorkoutDays(workoutDays);
    workout.setDuration(duration);
    // workout.setReps(reps);
    workout.setRestDuration(restDuration);
    workout.setEquipments(equipments);
    // workout.setSets(sets);
    workout.setLevel(level);

    return workout;
  }


  @Test
  public void createWorkoutTest() {
    Workout workout = getDummyWorkout();

    when(workoutRepository.findByName(workout.getName())).thenReturn(Optional.empty());
    when(workoutRepository.save(workout)).thenReturn(workout);

    Workout testWorkout = workoutService.createNewWorkout(workout);

    assertNotNull(testWorkout);

    assertEquals(workout.getId(), testWorkout.getId());
    assertEquals(workout.getName(), testWorkout.getName());
    assertEquals(workout.getCardioDays(), testWorkout.getCardioDays());
    assertEquals(workout.getWorkoutCategories(), testWorkout.getWorkoutCategories());
    assertEquals(workout.getDuration(), testWorkout.getDuration());
    // assertEquals(workout.getReps(), testWorkout.getReps());
    assertEquals(workout.getRestDuration(), testWorkout.getRestDuration());
    assertEquals(workout.getWorkoutDays(), testWorkout.getWorkoutDays());
    assertEquals(workout.getEquipments(), testWorkout.getEquipments());
    // assertEquals(workout.getSets(), testWorkout.getSets());
    assertEquals(workout.getLevel(), testWorkout.getLevel());
  }

  @Test
  public void getWorkoutByIdTest() {
    Workout workout = getDummyWorkout();

    when(workoutRepository.findById(workout.getId())).thenReturn(Optional.of(workout));
    Workout testWorkout = workoutService.getWorkout(workout.getId());

    assertNotNull(testWorkout);

    assertEquals(workout.getId(), testWorkout.getId());
    assertEquals(workout.getName(), testWorkout.getName());
    assertEquals(workout.getCardioDays(), testWorkout.getCardioDays());
    assertEquals(workout.getWorkoutCategories(), testWorkout.getWorkoutCategories());
    assertEquals(workout.getDuration(), testWorkout.getDuration());
    // assertEquals(workout.getReps(), testWorkout.getReps());
    assertEquals(workout.getRestDuration(), testWorkout.getRestDuration());
    assertEquals(workout.getWorkoutDays(), testWorkout.getWorkoutDays());
    assertEquals(workout.getEquipments(), testWorkout.getEquipments());
    // assertEquals(workout.getSets(), testWorkout.getSets());
    assertEquals(workout.getLevel(), testWorkout.getLevel());
  }

  @Test
  public void getWorkoutByNameTest() {
    Workout workout = getDummyWorkout();

    when(workoutRepository.findByName(workout.getName())).thenReturn(Optional.of(workout));
    Workout testWorkout = workoutService.getWorkout(workout.getName());

    assertNotNull(testWorkout);

    assertEquals(workout.getId(), testWorkout.getId());
    assertEquals(workout.getName(), testWorkout.getName());
    assertEquals(workout.getCardioDays(), testWorkout.getCardioDays());
    assertEquals(workout.getWorkoutCategories(), testWorkout.getWorkoutCategories());
    assertEquals(workout.getDuration(), testWorkout.getDuration());
    // assertEquals(workout.getReps(), testWorkout.getReps());
    assertEquals(workout.getRestDuration(), testWorkout.getRestDuration());
    assertEquals(workout.getWorkoutDays(), testWorkout.getWorkoutDays());
    assertEquals(workout.getEquipments(), testWorkout.getEquipments());
    // assertEquals(workout.getSets(), testWorkout.getSets());
    assertEquals(workout.getLevel(), testWorkout.getLevel());
  }
}
