package com.fitnessforge.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "exercise")
public class Exercise {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  @JsonIgnore
  private Long id;

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

  @Column(name = "instructions", columnDefinition = "TEXT")
  private List<String> instructions;

  @Column(name = "category")
  private String category;

  @Column(name = "images")
  private List<String> images;

  public String getCategory() {
    return category;
  }
  public String getEquipment() {
    return equipment;
  }
  public Long getId() {
    return id;
  }
  public List<String> getImages() {
    return images;
  }
  public String getForce() {
    return force;
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
  public List<String> getInstructions() {
    return instructions;
  }
  public List<String> getPrimaryMuscles() {
    return primaryMuscles;
  }
  public List<String> getSecondaryMuscles() {
    return secondaryMuscles;
  }
  
  public void setCategory(String category) {
    this.category = category;
  }
  public void setEquipment(String equipment) {
    this.equipment = equipment;
  }
  public void setForceType(String force) {
    this.force = force;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public void setImages(List<String> images) {
    this.images = images;
  }
  public void setInstructions(List<String> instructions) {
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
  public void setPrimaryMuscles(List<String> primaryMuscles) {
    this.primaryMuscles = primaryMuscles;
  }
  public void setSecondaryMuscles(List<String> secondaryMuscles) {
    this.secondaryMuscles = secondaryMuscles;
  }
}
