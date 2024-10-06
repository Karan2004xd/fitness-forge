package com.fitnessforge.entity;

import java.util.List;

import jakarta.persistence.*;

/**
 * <b>Description:</b>
 * <p>
 *  This is a exercise Entity class.
 *  It holds all the blueprint of exercise data 
 * </p>
 * */
@Entity
@Table(name = "exercise")
public class Exercise {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "exerciseId")
  private Long exerciseId;

  @Column(name = "id")
  private String id;

  @Column(name = "name")
  private String name;

  @Column(name = "force")
  private String force;

  @Column(name = "level")
  private String level;

  @Column(name = "mechanic")
  private String mechanic;

  @Column(name = "equipment")
  private String equipment;

  @Column(name = "primaryMuscles")
  private List<String> primaryMuscles;

  @Column(name = "secondaryMuscles")
  private List<String> secondaryMuscles;

  @Column(name = "instructions", columnDefinition = "TEXT[]")
  private List<String> instructions;

  @Column(name = "category")
  private String category;

  @Column(name = "images")
  private List<String> images;

  /** 
   * Empty default constructor
   * */
  public Exercise() {}

  /** 
   * Getter for the private attribute category
   * @return category of exercise
   * */
  public String getCategory() {
    return category;
  }

  /** 
   * Getter for the private attribute equipment
   * @return equipment of exercise
   * */
  public String getEquipment() {
    return equipment;
  }

  /** 
   * Getter for the private attribute id 
   * @return id of exercise
   * */
  public String getId() {
    return id;
  }

  /** 
   * Getter for the private attribute images 
   * @return images of the exercise
   * */
  public List<String> getImages() {
    return images;
  }

  /** 
   * Getter for the private attribute force 
   * @return force of the exercise
   * */
  public String getForce() {
    return force;
  }

  /** 
   * Getter for the private attribute level 
   * @return level of the exercise
   * */
  public String getLevel() {
    return level;
  }

  /** 
   * Getter for the private attribute mechanic 
   * @return mechanic of the exercise
   * */
  public String getMechanic() {
    return mechanic;
  }

  /** 
   * Getter for the private attribute name 
   * @return name of the exercise
   * */
  public String getName() {
    return name;
  }

  /** 
   * Getter for the private attribute instructions 
   * @return instructions of the exercise
   * */
  public List<String> getInstructions() {
    return instructions;
  }

  /** 
   * Getter for the private attribute primaryMuscles 
   * @return primary muscles targeted in the exercise
   * */
  public List<String> getPrimaryMuscles() {
    return primaryMuscles;
  }

  /** 
   * Getter for the private attribute secondaryMuscles 
   * @return secondary muscles targeted in the exercise
   * */
  public List<String> getSecondaryMuscles() {
    return secondaryMuscles;
  }

  /** 
   * Getter for the private attribute exerciseId 
   * @return the numberic id of the exercise
   * */
  public Long getExerciseId() {
    return exerciseId;
  }
  
  /** 
   * Setter for the private attribute category
   * @param category of the exercise
   * */
  public void setCategory(String category) {
    this.category = category;
  }
  
  /** 
   * Setter for the private attribute equipment 
   * @param equipment needed in the exercise
   * */
  public void setEquipment(String equipment) {
    this.equipment = equipment;
  }
  
  /** 
   * Setter for the private attribute force 
   * @param force targeted in the exercise
   * */
  public void setForceType(String force) {
    this.force = force;
  }
  
  /** 
   * Setter for the private attribute id 
   * @param id string id of the exercise
   * */
  public void setId(String id) {
    this.id = id;
  }
  
  /** 
   * Setter for the private attribute images 
   * @param images of the exercise
   * */
  public void setImages(List<String> images) {
    this.images = images;
  }
  
  /** 
   * Setter for the private attribute instructions 
   * @param instructions of the exercise
   * */
  public void setInstructions(List<String> instructions) {
    this.instructions = instructions;
  }
  
  /** 
   * Setter for the private attribute level 
   * @param level of the exercise
   * */
  public void setLevel(String level) {
    this.level = level;
  }
  
  /** 
   * Setter for the private attribute mechanic 
   * @param mechanic of the exercise
   * */
  public void setMechanic(String mechanic) {
    this.mechanic = mechanic;
  }
  
  /** 
   * Setter for the private attribute name 
   * @param name of the exercise
   * */
  public void setName(String name) {
    this.name = name;
  }
  
  /** 
   * Setter for the private attribute primaryMuscles 
   * @param primaryMuscles targeted in the exercise 
   * */
  public void setPrimaryMuscles(List<String> primaryMuscles) {
    this.primaryMuscles = primaryMuscles;
  }
  
  /** 
   * Setter for the private attribute secondaryMuscles 
   * @param secondaryMuscles targeted in the exercise 
   * */
  public void setSecondaryMuscles(List<String> secondaryMuscles) {
    this.secondaryMuscles = secondaryMuscles;
  }
  
  /** 
   * Setter for the private attribute exerciseId 
   * @param exerciseId numeric id of the exercise
   * */
  public void setExerciseId(Long exerciseId) {
    this.exerciseId = exerciseId;
  }
}
