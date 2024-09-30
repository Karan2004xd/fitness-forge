package com.fitnessforge.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "token")
public class Token {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "token_string", unique = true)
  @NotBlank(message = "Token cannot be blank")
  private String tokenString;

  @Column(name = "created_on")
  @Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime createdOn;

  @Column(name = "valid_hours")
  @NotNull(message = "Number of hours the token is valid is required")
  private int validHours;

  @Column(name = "member_id")
  @NotNull(message = "Member id associated with this token is required")
  private Long memberId;

  public Token() {}

  public Token(String tokenString, int validHours, Long memberId) {
    this.tokenString = tokenString;
    this.validHours = validHours;
    this.memberId = memberId;
  }

  public Long getId() {
    return id;
  }
  public LocalDateTime getCreatedOn() {
    return createdOn;
  }
  public int getValidHours() {
    return validHours;
  }
  public String getTokenString() {
    return tokenString;
  }
  public Long getMemberId() {
    return memberId;
  }
  
  public void setCreatedOn(LocalDateTime createdOn) {
    this.createdOn = createdOn;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public void setTokenString(String tokenString) {
    this.tokenString = tokenString;
  }
  public void setValidHours(int validHours) {
    this.validHours = validHours;
  }
  public void setMemberId(Long memberId) {
    this.memberId = memberId;
  }
}
