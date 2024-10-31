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
import com.fitnessforge.entity.Member;
import com.fitnessforge.entity.Workout;
import com.fitnessforge.exception.DatabaseException;
import com.fitnessforge.exception.DatabaseExceptionTypes;
import com.fitnessforge.repository.ExerciseRepository;
import com.fitnessforge.repository.MemberRepository;
import com.fitnessforge.repository.WorkoutRepository;
import com.fitnessforge.utils.ExerciseRange;
import com.fitnessforge.utils.FetchEntityUtil;
import com.fitnessforge.utils.SpecificationUtils;
import com.fitnessforge.utils.WorkoutConstants;

/** 
 * <b>Description:</b>
 * <p>
 *  Concrete Implementation class for interface {@link com.fitnessforge.service.MemberService}
 * </p>
 * */
@Service
public class WorkoutServiceImpl implements WorkoutService {

  @Autowired
  private WorkoutRepository workoutRepository;

  @Autowired
  private ExerciseRepository exerciseRepository;

  @Autowired
  private MemberRepository memberRepository;

  /** 
   * Empty Default constructor
   * */
  public WorkoutServiceImpl() {

  }

  /** 
   * Updates the workouts column of the member by adding the
   * newly created workout id to the workouts column
   *
   * @param workout an object of entity {@link com.fitnessforge.entity.Workout}
   * @param memberId the id of the entity {@link com.fitnessforge.entity.Member}
   * @param remove an helper variable to notify whehter delete operation to be performed or not
   * */
  protected void updateMemberWorkouts(Workout workout, Long memberId, boolean remove) {
    Member member = FetchEntityUtil.GetEntity(memberRepository.findById(memberId), Member.class);
    List<Long> workouts = member.getWorkouts();

    if (remove) {
      workouts.remove(workout.getId());
    } else {
      if (workouts == null) {
        workouts = new ArrayList<>(List.of(workout.getId()));
      } else {
        workouts.add(workout.getId());
      }
    }
    member.setWorkouts(workouts);
    member.setId(memberId);

    memberRepository.save(member);
  }

  /** 
   * Creates a new workout entry in the database, if not already present.
   *
   * @param workout an object of {@link com.fitnessforge.entity.Workout} entity
   * @return an object of {@link com.fitnessforge.entity.Workout} entity
   * */
  @Override
  public Workout createNewWorkout(Workout workout, Long memberId) {
    try {
      FetchEntityUtil.GetEntity(workoutRepository.findByName(workout.getName()), Workout.class);
      throw new DatabaseException(DatabaseExceptionTypes.MEMBER_ALREADY_EXISTS, WorkoutService.class.getName());
    } catch (DatabaseException e) {
      Workout workoutToSave = workoutRepository.save(workout);
      updateMemberWorkouts(workout, memberId, false);
      return workoutToSave;
    }
  }

  /** 
   * Fetches the workout by its name, if it exists.
   *
   * @param name name of the workout
   * @return an object of {@link com.fitnessforge.entity.Workout} entity
   * */
  @Override
  public Workout getWorkout(String name) {
    Workout workout = FetchEntityUtil.GetEntity(workoutRepository.findByName(name), Workout.class);
    return workout;
  }

  /** 
   * Fetches the workout by its id, if it exists.
   *
   * @param id the id of the workout
   * @return an object of {@link com.fitnessforge.entity.Workout} entity
   * */
  @Override
  public Workout getWorkout(Long id) {
    Workout workout = FetchEntityUtil.GetEntity(workoutRepository.findById(id), Workout.class);
    return workout;
  }

  /** 
   * The method checks if the current day is present in the
   * provided list of days.
   *
   * @param days List of names of days of the week
   * @return boolean value of whether the day exists or not.
   * */
  protected boolean checkCurrentDay(List<String> days) {
    LocalDate currentData = LocalDate.now();
    String currentDay = currentData.getDayOfWeek().toString().toLowerCase();

    return days.contains(currentDay);
  }

  /** 
   * Calculates and provided the total duration of a single exercise
   * in seconds based on the total duration of the workout.
   *
   * @param workout an object of entity {@link com.fitnessforge.entity.Workout}
   * @return the total duration of a single exercise (in seconds).
   * */
  protected int getExerciseDuration(Workout workout) {
    int totalExerciseDuration = workout.getDuration();

    if (checkCurrentDay(workout.getCardioDays())) {
      totalExerciseDuration -= workout.getCardioDuration();
    }
    totalExerciseDuration *= 60;

    return totalExerciseDuration;
  }

  /** 
   * Calculate average time required for an exercise to complete based on
   * its duration of reps and rest duration
   *
   * @param duration rep duration of the exericse
   * @param restDuration duration of rest between sets
   * @return average duration of the exercise based on its duration and rest duration.
   * */
  protected int getTotalDuration(int[] duration, int restDuration) {
    int totalDuration = ((duration[1] + duration[0]) / 2) * 60;
    return totalDuration + restDuration;
  }

  /** 
   * Calculate average time required for an exercise to complete based on
   * its duration of sets, reps and rest duration
   *
   * @param sets set range of the exercise
   * @param reps rep range of the exercise
   * @param restDuration duration of rest between sets
   * @return average duration of the exercise based on its sets, reps and rest duration.
   * */
  protected int getTotalDuration(int[] sets, int[] reps, int restDuration) {
    int baseSets = (sets[1] + sets[0]) / 2;
    int baseReps = (reps[1] + reps[0]) / 2;

    int totalRestDuration = (baseSets - 1) * restDuration;
    int totalDuration = (baseSets * baseReps * WorkoutConstants.AVERAGE_REP_DURATION) + totalRestDuration;

    return totalDuration;
  }

  /** 
   * Calculates and returns a map of string and integer, where
   * the string is the category name and the integer is the number
   * of exercises that need to be included based on duration.
   *
   * @param workout an object of entity {@link com.fitnessforge.entity.Workout}
   * @return an map of string and integer i.e Map(String, Integer)
   * */
  protected Map<String, Integer> getExercisesMap(Workout workout) {
    int exerciseDuration = getExerciseDuration(workout);
    String level = workout.getLevel();

    Map<String, Integer> exerciseMap = new HashMap<>();

    String category = workout.getWorkoutCategory();
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

    if (checkCurrentDay(workout.getCardioDays())) {
      ExerciseRange exerciseRangeTwo = WorkoutConstants.GetRange(WorkoutConstants.CARDIO, level);
      int totalDuration = getTotalDuration(exerciseRangeTwo.getDuration(), workout.getRestDuration());
      int exerciseToIncludeTwo = exerciseDuration / totalDuration;

      exerciseMap.put(WorkoutConstants.CARDIO, exerciseToIncludeTwo);
    }
    return exerciseMap;
  }

  /** 
   * Compiles a full list of different variations of workouts, 
   * based on the workout templete with their specifications
   * defined in it.
   *
   * @param workout an object of entity {@link com.fitnessforge.entity.Workout}
   * @return an list of map of category and list of Exercise ( List(Map(String, List(Exercise))) )
   * */
  @Override
  public List<Map<String, List<Exercise>>> getWorkoutExercises(Workout workout) {
    List<Map<String, List<Exercise>>> result = new ArrayList<>();

    if (checkCurrentDay(workout.getWorkoutDays())) {

      List<String> exercisesToExclude = workout.getExerciseToExclude();

      Map<String, Integer> exerciseMap = getExercisesMap(workout);

      for (String category : exerciseMap.keySet()) {
        Specification<Exercise> spec = Specification.where(null);
        Map<String, List<Exercise>> resultWorkout = new LinkedHashMap<>();

        spec = spec.and(SpecificationUtils.like(WorkoutConstants.LEVEL, workout.getLevel()));
        spec = spec.and(SpecificationUtils.in(WorkoutConstants.EQUIPMENTS, workout.getEquipments()));
        spec = spec.and(SpecificationUtils.like(WorkoutConstants.WORKOUT_CATEGORIES, category));
        spec = spec.and(SpecificationUtils.notIn(WorkoutConstants.EXERCISE_TO_EXCLUDE, exercisesToExclude));

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

  /** 
   * Updates the existing workout with new workout passed as a parameter,
   * if the existing workout is found.
   *
   * @param newWorkout an object of {@link com.fitnessforge.entity.Workout} entity
   * @return an object of {@link com.fitnessforge.entity.Workout} entity
   * */
  @Override
  public Workout updateWorkout(Workout newWorkout) {
    Long id = newWorkout.getId();
    FetchEntityUtil.GetEntity(workoutRepository.findById(id), Workout.class);

    return workoutRepository.save(newWorkout);
  }

  /** 
   * Deletes the existing workout by its id.
   *
   * @param id the id of the workout
   * */
  @Override
  public void deleteWorkout(Long id, Long memberId) {
    Workout workout = FetchEntityUtil.GetEntity(workoutRepository.findById(id), Workout.class);
    workoutRepository.deleteById(id);
    updateMemberWorkouts(workout, memberId, true);
  }
}
