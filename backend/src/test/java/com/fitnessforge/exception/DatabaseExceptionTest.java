package com.fitnessforge.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fitnessforge.exception.DatabaseException.DatabaseExceptionTypes;

@ExtendWith(MockitoExtension.class)
public class DatabaseExceptionTest {

  @Test
  public void testForAllExceptionExpectDefault() {
    String className = DatabaseExceptionTest.class.getName();

    String testString = DatabaseException.getExceptionMessage(
      DatabaseExceptionTypes.MEMBER_NOT_FOUND, 
      DatabaseExceptionTest.class.getName()
    );

    String actualString = "\nClass : " + className + "\nException : " + "The provided member was not found";
    assertEquals(actualString, testString);
  }

}
