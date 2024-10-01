package com.fitnessforge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fitnessforge.exception.DatabaseException;
import com.fitnessforge.exception.ErrorResponse;

/** 
 * <b>Description:</b>
 * <p>
 *  Central Application Exception handler for handling exceptions occuring throughout the application
 * </p>
 * */
@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

  /** 
   * Generates an error response {@link com.fitnessforge.exception.ErrorResponse} for
   * the {@link com.fitnessforge.exception.DatabaseException} exceptions
   *
   * @param error an object of RuntimeException
   * @return an object of org.springframework.http.ResponseEntity
   * */
  @ExceptionHandler({DatabaseException.class})
  public ResponseEntity<Object> handleDatabaseException(RuntimeException error) {
    ErrorResponse errorResponse = new ErrorResponse(Arrays.asList(error.getMessage()));
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  /** 
   * Generates an error response {@link com.fitnessforge.exception.ErrorResponse}
   * for invalid argument types or data passed
   *
   * @param error an object of RuntimeException
   * @return an object of org.springframework.http.ResponseEntity
   * */
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatusCode status, WebRequest request) {

    List<String> errors = new ArrayList<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> errors.add(error.getDefaultMessage()));
    return new ResponseEntity<>(new ErrorResponse(errors), HttpStatus.BAD_REQUEST);
  }
}
