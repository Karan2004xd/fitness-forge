package com.fitnessforge.entity;

import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * <b>Description:</b>
 * <p>
 *  This is a workout Entity class.
 *  It holds the blueprint of all the workout data.
 * </p>
 * */
@Entity
@Table(name = "workout")
public class Workout {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @NotEmpty(message = "Need to specify you fitness Level")
  @Column(name = "level")
  private String level;

  @NotBlank(message = "Name cannot be blank")
  @Column(name = "name", unique = true)
  private String name;

  @NotEmpty(message = "Need to select atleast one category")
  @Column(name = "workoutCategory")
  private String workoutCategory;

  @NotEmpty(message = "Need to select atleast one day")
  @Column(name = "workout_days")
  private List<String> workoutDays;

  @NotNull(message = "Need to specify the duration of workout")
  @Column(name = "duration")
  private int duration;

  @NotEmpty(message = "Need to specify the equipments availability")
  @Column(name = "equipments")
  private List<String> equipments;

  @NotNull(message = "Need to specify the rest duration")
  @Column(name = "restDuration")
  private int restDuration;

  @Column(name = "cardioDays")
  private List<String> CardioDays;

  @Column(name = "cardioDuration")
  private int cardioDuration;

  @Column(name = "exerciseToExclude")
  private List<String> exerciseToExclude;

  /** 
   * Default Constructor of Member class
   * */
  public Workout() {}

  /** 
   * Getter of the private attribute equipments 
   * @return List of equipments
   * */
  public List<String> getEquipments() {
    return equipments;
  }

  /** 
   * Getter of the private attribute exerciseToExclude 
   * @return List of exerciseToExclude 
   * */
  public List<String> getExerciseToExclude() {
    return exerciseToExclude;
  }

  /** 
   * Getter of the private attribute id 
   * @return id of the workout
   * */
  public Long getId() {
    return id;
  }

  /** 
   * Getter of the private attribute duration 
   * @return duration of workout
   * */
  public int getDuration() {
    return duration;
  }

  /** 
   * Getter of the private attribute restDuration 
   * @return restDuration of workout
   * */
  public int getRestDuration() {
    return restDuration;
  }

  /** 
   * Getter of the private attribute cardioDays 
   * @return List of cardioDays 
   * */
  public List<String> getCardioDays() {
    return CardioDays;
  }

  /** 
   * Getter of the private attribute workoutDays 
   * @return List of workoutDays 
   * */
  public List<String> getWorkoutDays() {
    return workoutDays;
  }

  /** 
   * Getter of the private attribute workoutCategory 
   * @return String of workoutCategory 
   * */
  public String getWorkoutCategory() {
    return workoutCategory;
  }

  /** 
   * Getter of the private attribute name 
   * @return name of the workout
   * */
  public String getName() {
    return name;
  }

  /** 
   * Getter of the private attribute level 
   * @return level of workout
   * */
  public String getLevel() {
    return level;
  }

  /** 
   * Getter of the private attribute cardioDuration 
   * @return cardioDuration of workout
   * */
  public int getCardioDuration() {
    return cardioDuration;
  }

  /** 
   * Setter of the private attribute cardioDays 
   * @param cardioDays list of days of cardio 
   * */
  public void setCardioDays(List<String> cardioDays) {
    CardioDays = cardioDays;
  }

  /** 
   * Setter of the private attribute equipments 
   * @param equipments list of available equipments
   * */
  public void setEquipments(List<String> equipments) {
    this.equipments = equipments;
  }

  /** 
   * Setter of the private attribute exerciseToExclude 
   * @param exerciseToExclude list of exercise to not include
   * */
  public void setExerciseToExclude(List<String> exerciseToExclude) {
    this.exerciseToExclude = exerciseToExclude;
  }

  /** 
   * Setter of the private attribute id 
   * @param id id of the workout
   * */
  public void setId(Long id) {
    this.id = id;
  }

  /** 
   * Setter of the private attribute duration 
   * @param duration duration of workout
   * */
  public void setDuration(int duration) {
    this.duration = duration;
  }

  /** 
   * Setter of the private attribute restDuration 
   * @param restDuration duration of rest between reps
   * */
  public void setRestDuration(int restDuration) {
    this.restDuration = restDuration;
  }

  /** 
   * Setter of the private attribute workoutDays 
   * @param workoutDays list of days of workout 
   * */
  public void setWorkoutDays(List<String> workoutDays) {
    this.workoutDays = workoutDays;
  }

  /** 
   * Setter of the private attribute workoutCategory 
   * @param workoutCategory category of exercise
   * */
  public void setWorkoutCategory(String workoutCategory) {
    this.workoutCategory = workoutCategory;
  }

  /** 
   * Setter of the private attribute name 
   * @param name the name of the workout
   * */
  public void setName(String name) {
    this.name = name;
  }

  /** 
   * Setter of the private attribute level 
   * @param level level of exercises
   * */
  public void setLevel(String level) {
    this.level = level;
  }

  /** 
   * Setter of the private attribute cardioDuration
   * @param cardioDuration the duration of cardio session
   * */
  public void setCardioDuration(int cardioDuration) {
    this.cardioDuration = cardioDuration;
  }
}
