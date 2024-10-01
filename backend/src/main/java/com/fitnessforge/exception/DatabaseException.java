package com.fitnessforge.exception;

/** 
 * <b>Description</b>
 * <p>
 *  Custom Exception class for all exception related to database 
 *  or in general while persisting the data in the database.
 * </p>
 * */
public class DatabaseException extends RuntimeException {

  /** 
   * Defined protected constructor for testing
   * */
  protected DatabaseException() {}

  /** 
   * Constructor which calls the constructor of the parent class (RuntimeException)
   * with the custom made exception message.
   * @param exceptionType enum of DatabaseExceptionTypes
   * @param className name of the class from where the exception is being thrown
   * */
  public DatabaseException(DatabaseExceptionTypes exceptionType, String className) {
    super(getExceptionMessage(exceptionType, className));
  }

  /** 
   * Helper method for generating an custom excpetion message
   * @param exceptionType enum of DatabaseExceptionTypes
   * @param className name of the class from where the exception is being thrown
   * @return A custom message defined for specific exception types
   * */
  protected static String getExceptionMessage(DatabaseExceptionTypes exceptionType, String className) {
    String message = "\nClass : " + className + "\nException : ";

    switch (exceptionType) {
      case MEMBER_NOT_FOUND:
        message += "The provided member was not found";
        break;

      case MEMBER_ALREADY_EXISTS:
        message += "The provided member already exists";
        break;

      case CONSTRAINT_VIOLATIONS:
        message += "Data base constraint where violeted";
        break;

      case UNDEFINED_EXCEPTION:
        message += "Undefined exception occured";
        break;

      default:
        message = null;
        break;
    }
    return message;
  }
}
