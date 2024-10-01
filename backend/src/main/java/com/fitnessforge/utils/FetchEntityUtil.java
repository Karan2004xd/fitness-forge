package com.fitnessforge.utils;

import java.util.Optional;

import com.fitnessforge.exception.DatabaseException;
import com.fitnessforge.exception.DatabaseExceptionTypes;

/** 
 * <b>Description:</b>
 * <p>
 *  Utility for unwraping the fetched entity {@link com.fitnessforge.entity} objects
 * </p>
 * */
public class FetchEntityUtil {

  /** 
   * Empty default constructor
   * */
  public FetchEntityUtil() {}

  /** 
   * Helper utility for unwraping the Optional objects while making necessary checks.
   *
   * @param <T> the type of the entity being passed in 
   * @param entity an Optional object of Generic class type T
   * @param entityClass class type of the Generic class type T
   * @return Generic class Type T
   * @throws DatabaseException if entity is empty.
   * */
  public static <T> T GetEntity(Optional<T> entity, Class<T> entityClass)
    throws DatabaseException 
  {
    if (entity.isPresent()) return entity.get();
    throw new DatabaseException(DatabaseExceptionTypes.MEMBER_NOT_FOUND, entityClass.getName());
  }
}
