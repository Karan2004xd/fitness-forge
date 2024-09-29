package com.fitnessforge.utils;

import java.util.Optional;

import com.fitnessforge.exception.DatabaseException;
import com.fitnessforge.exception.DatabaseException.DatabaseExceptionTypes;

public class FetchEntityUtil {
  public static <T> T GetEntity(Optional<T> entity, Class<T> entityClass)
    throws DatabaseException 
  {
    if (entity.isPresent()) return entity.get();
    throw new DatabaseException(DatabaseExceptionTypes.MEMBER_NOT_FOUND, entityClass.getName());
  }
}
