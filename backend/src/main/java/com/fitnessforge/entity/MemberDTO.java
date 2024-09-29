package com.fitnessforge.entity;

import java.util.Date;

public class MemberDTO {
  private Long id;
  private String name;
  private int age;
  private String gender;
  private String fitnessLevel;
  private float weight;
  private float height;
  private String email;
  private Date joinedOn = new Date();

  public MemberDTO() {}

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
  public void setWeight(float weight) {
    this.weight = weight;
  }
  public void setJoinedOn(Date joinedOn) {
    this.joinedOn = joinedOn;
  }
}
