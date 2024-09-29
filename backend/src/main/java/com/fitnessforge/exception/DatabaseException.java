package com.fitnessforge.exception;

public class DatabaseException extends RuntimeException {
  public static enum DatabaseExceptionTypes {
    MEMBER_NOT_FOUND,
    MEMBER_ALREADY_EXISTS,
    CONSTRAINT_VIOLATIONS,
    UNDEFINED_EXCEPTION
  };

  protected DatabaseException() {}

  public DatabaseException(DatabaseExceptionTypes exceptionType, String className) {
    super(getExceptionMessage(exceptionType, className));
  }

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
