package com.fitnessforge.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.fitnessforge.entity.Exercise;
import com.fitnessforge.entity.Workout;
import com.fitnessforge.exception.DatabaseException;
import com.fitnessforge.exception.DatabaseExceptionTypes;
import com.fitnessforge.repository.ExerciseRepository;
import com.fitnessforge.repository.WorkoutRepository;
import com.fitnessforge.utils.ExerciseRange;
import com.fitnessforge.utils.FetchEntityUtil;
import com.fitnessforge.utils.SpecificationUtils;
import com.fitnessforge.utils.WorkoutConstants;

@Service
public class WorkoutServiceImpl implements WorkoutService {

  @Autowired
  private WorkoutRepository workoutRepository;

  @Autowired
  private ExerciseRepository exerciseRepository;

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

  private boolean checkTodayDay(List<String> days) {
    LocalDate currentData = LocalDate.now();
    String currentDay = currentData.getDayOfWeek().toString().toLowerCase();

    return days.contains(currentDay);
  }

  private int getExerciseDuration(Workout workout) {
    int totalExerciseDuration = workout.getDuration();

    if (checkTodayDay(workout.getCardioDays())) {
      totalExerciseDuration -= workout.getCardioDuration();
    }
    totalExerciseDuration *= 60;

    return totalExerciseDuration / workout.getWorkoutCategories().size();
  }

  private int getTotalDuration(int[] duration, int restDuration) {
    int totalDuration = ((duration[1] + duration[0]) / 2) * 60;
    return totalDuration + restDuration;
  }

  private int getTotalDuration(int[] sets, int[] reps, int restDuration) {
    int baseSets = (sets[1] + sets[0]) / 2;
    int baseReps = (reps[1] + reps[0]) / 2;

    int totalRestDuration = (baseSets - 1) * restDuration;
    int totalDuration = (baseSets * baseReps * WorkoutConstants.AVERAGE_REP_DURATION) + totalRestDuration;

    return totalDuration;
  }

  private Map<String, Integer> getExercisesMap(Workout workout) {
    int exerciseDuration = getExerciseDuration(workout);
    String level = workout.getLevel();

    Map<String, Integer> exerciseMap = new HashMap<>();

    for (String category : workout.getWorkoutCategories()) {
      ExerciseRange exerciseRange = WorkoutConstants.GetRange(category, level);
      int exerciseToInclude;

      if (category == WorkoutConstants.STRETCHING) {
        int totalDuration = getTotalDuration(exerciseRange.getDuration(), workout.getRestDuration());
        exerciseToInclude = exerciseDuration / totalDuration;
      } else {
        int totalDuration = getTotalDuration(exerciseRange.getReps(), exerciseRange.getSets(), workout.getRestDuration());
        exerciseToInclude = exerciseDuration / totalDuration;
      }

      exerciseMap.put(category, exerciseToInclude);
    }

    if (checkTodayDay(workout.getCardioDays())) {
      ExerciseRange exerciseRange = WorkoutConstants.GetRange(WorkoutConstants.CARDIO, level);
      int totalDuration = getTotalDuration(exerciseRange.getDuration(), workout.getRestDuration());
      int exerciseToInclude = exerciseDuration / totalDuration;

      exerciseMap.put(WorkoutConstants.CARDIO, exerciseToInclude);
    }
    return exerciseMap;
  }

  @Override
  public List<Map<String, List<Exercise>>> getWorkoutExercises(Workout workout) {
    List<Map<String, List<Exercise>>> result = new ArrayList<>();
    if (checkTodayDay(workout.getWorkoutDays())) {
      Map<String, Integer> exerciseMap = getExercisesMap(workout);

      for (String category : exerciseMap.keySet()) {
        System.out.println(category);
        Specification<Exercise> spec = Specification.where(null);
        Map<String, List<Exercise>> resultWorkout = new LinkedHashMap<>();

        spec = spec.and(SpecificationUtils.like(WorkoutConstants.LEVEL, workout.getLevel()));
        spec = spec.and(SpecificationUtils.in(WorkoutConstants.EQUIPMENTS, workout.getEquipments()));
        spec = spec.and(SpecificationUtils.in(WorkoutConstants.WORKOUT_CATEGORIES, List.of(category)));

        List<Exercise> exercises = exerciseRepository.findAll(spec);
        int numberOfExercises = exerciseMap.get(category);
        int counter = 0;

        for (int i = 0; i < exercises.size(); i++) {
          Exercise exercise = exercises.get(i);
          if (exercise != null) {
            String newCategory = category + '_' + Integer.toString((i / numberOfExercises) + 1);
            if (resultWorkout.get(newCategory) == null) {
              resultWorkout.put(newCategory, new ArrayList<>(List.of(exercise)));
            } else {
              resultWorkout.get(newCategory).add(exercise);
            }
            counter++;

            if (counter == numberOfExercises) {
              result.add(resultWorkout);
              resultWorkout = new LinkedHashMap<>();
              counter = 0;
            }
          }
        }
      }
    }  
    return result;
  }
}
