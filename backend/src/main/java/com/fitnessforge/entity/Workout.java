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

  @NotEmpty(message = "Need to specify you fitness Level")
  @Column(name = "level")
  private String level;

  @NotBlank(message = "Name cannot be blank")
  @Column(name = "name", unique = true)
  private String name;

  @NotEmpty(message = "Need to select atleast one category")
  @Column(name = "workoutCategories")
  private List<String> workoutCategories;

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

  public Workout() {

  }

  public List<String> getEquipments() {
    return equipments;
  }
  public List<String> getExerciseToExclude() {
    return exerciseToExclude;
  }
  public Long getId() {
    return id;
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
  public String getLevel() {
    return level;
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
  public void setId(Long id) {
    this.id = id;
  }
  public void setDuration(int duration) {
    this.duration = duration;
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
  public void setLevel(String level) {
    this.level = level;
  }
  public void setCardioDuration(int cardioDuration) {
    this.cardioDuration = cardioDuration;
  }
}
