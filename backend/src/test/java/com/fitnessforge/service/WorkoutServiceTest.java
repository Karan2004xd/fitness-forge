package com.fitnessforge.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fitnessforge.entity.Member;
import com.fitnessforge.entity.Workout;
import com.fitnessforge.repository.MemberRepository;
import com.fitnessforge.repository.WorkoutRepository;

public class WorkoutServiceTest {

  @Mock
  private WorkoutRepository workoutRepository;

  @Mock
  private MemberRepository memberRepository;

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

    String workoutCategory = "strength";
    List<String> workoutDays = List.of("monday","tuesday");

    int duration = 60;

    List<String> equipments = List.of("dumbell", "machine");
    int restDuration = 30;

    List<String> cardioDays = List.of("monday");
    String level = "beginner";

    workout.setId(id);
    workout.setName(name);
    workout.setCardioDays(cardioDays);
    workout.setWorkoutCategory(workoutCategory);
    workout.setWorkoutDays(workoutDays);
    workout.setDuration(duration);
    workout.setRestDuration(restDuration);
    workout.setEquipments(equipments);
    workout.setLevel(level);

    return workout;
  }

  private void testWorkoutEquality(Workout workout, Workout testWorkout) {
    assertNotNull(testWorkout);
    assertNotNull(workout);

    assertEquals(workout.getId(), testWorkout.getId());
    assertEquals(workout.getName(), testWorkout.getName());
    assertEquals(workout.getCardioDays(), testWorkout.getCardioDays());
    assertEquals(workout.getWorkoutCategory(), testWorkout.getWorkoutCategory());
    assertEquals(workout.getDuration(), testWorkout.getDuration());
    assertEquals(workout.getRestDuration(), testWorkout.getRestDuration());
    assertEquals(workout.getWorkoutDays(), testWorkout.getWorkoutDays());
    assertEquals(workout.getEquipments(), testWorkout.getEquipments());
    assertEquals(workout.getLevel(), testWorkout.getLevel());
  }

  @Test
  public void createWorkoutTest() {
    Workout workout = getDummyWorkout();
    Member member = new Member();

    member.setName("test");
    member.setId(1L);
    member.setEmail("test@gmail.com");
    member.setPassword("test123");

    Long id = 1L;

    when(workoutRepository.findByName(workout.getName())).thenReturn(Optional.empty());
    when(workoutRepository.save(workout)).thenReturn(workout);
    when(memberRepository.findById(1L)).thenReturn(Optional.of(member));

    Workout testWorkout = workoutService.createNewWorkout(workout, id);

    testWorkoutEquality(workout, testWorkout);
  }

  @Test
  public void getWorkoutByIdTest() {
    Workout workout = getDummyWorkout();

    when(workoutRepository.findById(workout.getId())).thenReturn(Optional.of(workout));
    Workout testWorkout = workoutService.getWorkout(workout.getId());

    testWorkoutEquality(workout, testWorkout);
  }

  @Test
  public void getWorkoutByNameTest() {
    Workout workout = getDummyWorkout();

    when(workoutRepository.findByName(workout.getName())).thenReturn(Optional.of(workout));
    Workout testWorkout = workoutService.getWorkout(workout.getName());

    testWorkoutEquality(workout, testWorkout);
  }

  @Test
  public void checkCurrentDayTest() {
    WorkoutServiceImpl workoutServiceImpl = new WorkoutServiceImpl();
    String currentDay = LocalDate.now().getDayOfWeek().toString().toLowerCase();
    assertTrue(workoutServiceImpl.checkCurrentDay(List.of("sunday", "monday", currentDay)));
    assertFalse(workoutServiceImpl.checkCurrentDay(List.of("sunday", "monday", "day")));
  }

  @Test
  public void getExerciseDurationTest() {
    Workout workout = getDummyWorkout();
    WorkoutServiceImpl workoutServiceImpl = new WorkoutServiceImpl();

    int duration = workout.getDuration();
    int cardioDuration = workout.getCardioDuration();

    int testOne = ((duration) * 60);
    int testTwo = ((duration + cardioDuration) * 60);

    assertEquals(workoutServiceImpl.getExerciseDuration(workout), testOne);

    workout.setCardioDays(List.of("friday"));

    assertEquals(workoutServiceImpl.getExerciseDuration(workout), testTwo);
  }

  @Test
  public void updateWorkoutTest() {
    Workout workout = getDummyWorkout();
    Long id = workout.getId();
    List<String> cardioDays = List.of("monday", "tuesday");

    when(workoutRepository.findById(id)).thenReturn(Optional.of(workout));
    Workout existingWorkout = workoutService.getWorkout(id);

    assertNotNull(existingWorkout);
    testWorkoutEquality(workout, existingWorkout);

    workout.setCardioDays(cardioDays);
    when(workoutRepository.save(workout)).thenReturn(workout);
    
    Workout testWorkout = workoutService.updateWorkout(workout);
    assertEquals(testWorkout.getCardioDays(), workout.getCardioDays());
  }
}
