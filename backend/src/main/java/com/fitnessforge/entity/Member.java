package com.fitnessforge.entity;

import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

/**
 * <b>Description:</b>
 * <p>
 *  This is a member Entity class.
 *  It holds the blueprint of all the member data.
 * </p>
 * */
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

  @Column(name = "fitness_level")
  private String fitnessLevel;

  @Column(name = "weight")
  private float weight;

  @Column(name = "height")
  private float height;

  @NotBlank(message = "Email of the member is required")
  @Column(name = "email", unique = true)
  private String email;

  // @NotBlank(message = "Password cannot be blank")
  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "joined_on")
  @Temporal(TemporalType.DATE)
  private Date joinedOn = new Date();

  /** 
   * Default Constructor of Member class
   * */
  public Member() {}

  /** 
   * Getter of the private attribute age
   * @return The age of the member
   * */
  public int getAge() {
    return age;
  }

  /** 
   * Getter of the private attribute email
   * @return The email of the member
   * */
  public String getEmail() {
    return email;
  }

  /** 
   * Getter of the private attribute gender
   * @return The gender of the member (male / female)
   * */
  public String getGender() {
    return gender;
  }

  /** 
   * Getter of the private attribute height
   * @return The height of the member (in foot)
   * */
  public float getHeight() {
    return height;
  }

  /** 
   * Getter of the private attribute id 
   * @return The id of member (generated using {@link jakarta.persistence.GeneratedValue})
   * */
  public Long getId() {
    return id;
  }

  /** 
   * Getter of the private attribute name 
   * @return The name of the member 
   * */
  public String getName() {
    return name;
  }

  /** 
   * Getter of the private attribute password 
   * @return The password of the member (which will be encrypted by the service layer {@link com.fitnessforge.service.MemberService})
   * */
  public String getPassword() {
    return password;
  }

  /** 
   * Getter of the private attribute weight 
   * @return The weight of the member (in kilogram)
   * */
  public float getWeight() {
    return weight;
  }

  /** 
   * Getter of the private attribute fitnessLevel 
   * @return The fitness level of the member (beginner / intermediate / advanced)
   * */
  public String getFitnessLevel() {
    return fitnessLevel;
  }

  /** 
   * Getter of the private attribute joinedOn 
   * @return The date of joining of the member
   * */
  public Date getJoinedOn() {
    return joinedOn;
  }

  /** 
   * Setter of the private attribute age 
   * @param age the age of member
   * */
  public void setAge(int age) {
    this.age = age;
  }

  /** 
   * Setter of the private attribute email 
   * @param email the email of member
   * */
  public void setEmail(String email) {
    this.email = email;
  }

  /** 
   * Setter of the private attribute fitnessLevel 
   * @param fitnessLevel The fitness level of member (beginner / intermediate / advanced)
   * */
  public void setFitnessLevel(String fitnessLevel) {
    this.fitnessLevel = fitnessLevel;
  }

  /** 
   * Setter of the private attribute gender 
   * @param gender The gender of member (male / female)
   * */
  public void setGender(String gender) {
    this.gender = gender;
  }

  /** 
   * Setter of the private attribute height 
   * @param height The height of member
   * */
  public void setHeight(float height) {
    this.height = height;
  }

  /** 
   * Setter of the private attribute id 
   * @param id The id of member
   * */
  public void setId(Long id) {
    this.id = id;
  }

  /** 
   * Setter of the private attribute name 
   * @param name The name of member
   * */
  public void setName(String name) {
    this.name = name;
  }

  /** 
   * Setter of the private attribute password 
   * @param password The password of member
   * */
  public void setPassword(String password) {
    this.password = password;
  }

  /** 
   * Setter of the private attribute weight 
   * @param weight The weight of member
   * */
  public void setWeight(float weight) {
    this.weight = weight;
  }

  /** 
   * Setter of the private attribute joinedOn 
   * @param joinedOn The date on which the member joined on
   * */
  public void setJoinedOn(Date joinedOn) {
    this.joinedOn = joinedOn;
  }
}
