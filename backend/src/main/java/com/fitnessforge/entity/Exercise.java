package com.fitnessforge.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "exercise")
public class Exercise {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "forceType")
  private String forceType;

  @Column(name = "level", nullable = false)
  private String level;

  @Column(name = "mechanic")
  private String mechanic;

  @Column(name = "equipment")
  private String equipment;

  @Column(name = "primaryMuscles", nullable = false)
  private String primaryMuscles;

  @Column(name = "secondaryMuscles", nullable = false)
  private String secondaryMuscles;

  @Column(name = "instructions", nullable = false)
  private String instructions;

  @Column(name = "category", nullable = false)
  private String category;

  @Column(name = "images", nullable = false)
  private String images;

  public String getCategory() {
    return category;
  }
  public String getEquipment() {
    return equipment;
  }
  public Long getId() {
    return id;
  }
  public String getImages() {
    return images;
  }
  public String getForceType() {
    return forceType;
  }
  public String getLevel() {
    return level;
  }
  public String getMechanic() {
    return mechanic;
  }
  public String getName() {
    return name;
  }
  public String getInstructions() {
    return instructions;
  }
  public String getPrimaryMuscles() {
    return primaryMuscles;
  }
  public String getSecondaryMuscles() {
    return secondaryMuscles;
  }
  
  public void setCategory(String category) {
    this.category = category;
  }
  public void setEquipment(String equipment) {
    this.equipment = equipment;
  }
  public void setForceType(String forceType) {
    this.forceType = forceType;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public void setImages(String images) {
    this.images = images;
  }
  public void setInstructions(String instructions) {
    this.instructions = instructions;
  }
  public void setLevel(String level) {
    this.level = level;
  }
  public void setMechanic(String mechanic) {
    this.mechanic = mechanic;
  }
  public void setName(String name) {
    this.name = name;
  }
  public void setPrimaryMuscles(String primaryMuscles) {
    this.primaryMuscles = primaryMuscles;
  }
  public void setSecondaryMuscles(String secondaryMuscles) {
    this.secondaryMuscles = secondaryMuscles;
  }
}
