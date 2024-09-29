package com.fitnessforge.exception;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ErrorResponse {
  
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
  private LocalDateTime timestamp;

  private List<String> messages;

  public ErrorResponse(List<String> messages) {
    this.timestamp = LocalDateTime.now();
    this.messages = messages;
  }

  public List<String> getMessages() {
    return messages;
  }
  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setMessages(List<String> messages) {
    this.messages = messages;
  }
  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }
}
