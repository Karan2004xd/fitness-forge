package com.fitnessforge.entity;

import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "workout")
public class Workout {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @NotEmpty(message = "Need to specify you fitness Levels")
  @Column(name = "level")
  private List<String> level;

  @NotBlank(message = "Name cannot be blank")
  @Column(name = "name", unique = true)
  private String name;

  @NotEmpty(message = "Need to select atleast one category")
  @Column(name = "workout_categories")
  private List<String> workoutCategories;

  @NotEmpty(message = "Need to select atleast one day")
  @Column(name = "workout_days")
  private List<String> workoutDays;

  @NotNull(message = "Need to specify the duration of workout")
  @Column(name = "duration")
  private int duration;

  @Column(name = "set")
  private String set;

  @Column(name = "reps")
  private String reps;

  @NotEmpty(message = "Need to specify the equipments availability")
  @Column(name = "equipments")
  private List<String> equipments;

  @NotNull(message = "Need to specify the rest duration")
  @Column(name = "rest_duration")
  private int restDuration;

  @Column(name = "cardio_days")
  private List<String> CardioDays;

  @Column(name = "cardio_duration")
  private int cardioDuration;

  @Column(name = "exercise_to_include")
  private List<String> exerciseToInclude;

  @Column(name = "exercise_to_exclude")
  private List<String> exerciseToExclude;

  public Workout() {

  }

  public List<String> getEquipments() {
    return equipments;
  }
  public List<String> getExerciseToExclude() {
    return exerciseToExclude;
  }
  public List<String> getExerciseToInclude() {
    return exerciseToInclude;
  }
  public Long getId() {
    return id;
  }
  public String getReps() {
    return reps;
  }
  public int getDuration() {
    return duration;
  }
  public int getRestDuration() {
    return restDuration;
  }
  public List<String> getCardioDays() {
    return CardioDays;
  }
  public List<String> getWorkoutDays() {
    return workoutDays;
  }
  public List<String> getWorkoutCategories() {
    return workoutCategories;
  }
  public String getName() {
    return name;
  }
  public List<String> getLevel() {
    return level;
  }
  public String getSet() {
    return set;
  }
  public int getCardioDuration() {
    return cardioDuration;
  }

  public void setCardioDays(List<String> cardioDays) {
    CardioDays = cardioDays;
  }
  public void setEquipments(List<String> equipments) {
    this.equipments = equipments;
  }
  public void setExerciseToExclude(List<String> exerciseToExclude) {
    this.exerciseToExclude = exerciseToExclude;
  }
  public void setExerciseToInclude(List<String> exerciseToInclude) {
    this.exerciseToInclude = exerciseToInclude;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public void setDuration(int duration) {
    this.duration = duration;
  }
  public void setReps(String reps) {
    this.reps = reps;
  }
  public void setRestDuration(int restDuration) {
    this.restDuration = restDuration;
  }
  public void setWorkoutDays(List<String> workoutDays) {
    this.workoutDays = workoutDays;
  }
  public void setWorkoutCategories(List<String> workoutCategories) {
    this.workoutCategories = workoutCategories;
  }
  public void setName(String name) {
    this.name = name;
  }
  public void setLevel(List<String> level) {
    this.level = level;
  }
  public void setSet(String set) {
    this.set = set;
  }
  public void setCardioDuration(int cardioDuration) {
    this.cardioDuration = cardioDuration;
  }
}
