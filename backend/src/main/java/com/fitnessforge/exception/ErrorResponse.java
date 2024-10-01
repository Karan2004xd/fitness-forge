package com.fitnessforge.exception;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/** 
 * <b>Description:</b>
 * <p>
 *  An Helper class for generating error messages for excpetions occured in api calls
 * </p>
 * */
public class ErrorResponse {
  
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
  private LocalDateTime timestamp;

  private List<String> messages;

  /** 
   * Sets the current timestamp and the list of error messages
   * @param messages List of error messages
   * */
  public ErrorResponse(List<String> messages) {
    this.timestamp = LocalDateTime.now();
    this.messages = messages;
  }

  /** 
   * Getter for the list of error messages
   * @return List of error messages
   * */
  public List<String> getMessages() {
    return messages;
  }

  /** 
   * Getter for the timestamp of the error message
   * @return The timestamp on which the message was generated or occured
   * */
  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  /** 
   * Setter for list of error messages
   * @param messages List of error messages
   * */
  public void setMessages(List<String> messages) {
    this.messages = messages;
  }

  /** 
   * Setter for timestamp of the error message
   * @param timestamp The timestamp of error message
   * */
  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }
}
