package com.fitnessforge.exception;

/** 
 * <b>Description</b>
 * <p>
 *  Enum class containing various exception that could be thrown while performing operations
 *  on database.
 * </p>
 * */
public enum DatabaseExceptionTypes {
  /** 
   * Member not found in the database
   * */
  MEMBER_NOT_FOUND,

  /** 
   * Member already exists in the database
   * */
  MEMBER_ALREADY_EXISTS,

  /** 
   * Database constraint voilation exception
   * */
  CONSTRAINT_VIOLATIONS,

  /** 
   * Undefined exception was caught
   * */
  UNDEFINED_EXCEPTION
};
