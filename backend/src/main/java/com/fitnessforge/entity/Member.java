package com.fitnessforge.entity;

import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "member")
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @NotBlank(message = "Name of the member cannot be blank")
  @Column(name = "name")
  private String name;

  @Column(name = "age")
  private int age;

  @Column(name = "gender")
  private String gender;

  @NotBlank(message = "Fitness Level is required")
  @Column(name = "fitness_level")
  private String fitnessLevel;

  @NotNull(message = "Weight of the member is required")
  @Column(name = "weight")
  private float weight;

  @NotNull(message = "Height of the member is required")
  @Column(name = "height")
  private float height;

  @NotBlank(message = "Email of the member is required")
  @Column(name = "email", unique = true)
  private String email;

  @NotBlank(message = "Password cannot be blank")
  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "joined_on")
  @Temporal(TemporalType.DATE)
  private Date joinedOn = new Date();

  public Member() {}

  public int getAge() {
    return age;
  }
  public String getEmail() {
    return email;
  }
  public String getGender() {
    return gender;
  }
  public float getHeight() {
    return height;
  }
  public Long getId() {
    return id;
  }
  public String getName() {
    return name;
  }
  public String getPassword() {
    return password;
  }
  public float getWeight() {
    return weight;
  }
  public String getFitnessLevel() {
    return fitnessLevel;
  }
  public Date getJoinedOn() {
    return joinedOn;
  }

  public void setAge(int age) {
    this.age = age;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public void setFitnessLevel(String fitnessLevel) {
    this.fitnessLevel = fitnessLevel;
  }
  public void setGender(String gender) {
    this.gender = gender;
  }
  public void setHeight(float height) {
    this.height = height;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public void setName(String name) {
    this.name = name;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public void setWeight(float weight) {
    this.weight = weight;
  }
  public void setJoinedOn(Date joinedOn) {
    this.joinedOn = joinedOn;
  }
}
