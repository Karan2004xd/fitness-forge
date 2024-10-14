package com.fitnessforge.utils;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

/** 
 * <b>Description:</b>
 * <p>
 *  An Utility class for defining specification rules using 
 *  org.springframework.data.jpa.domain.Specification, the specification rules
 *  which can than be used directly on database queries.
 * </p>
 * */
public class SpecificationUtils {


  /** 
   * Empty Default Constructor
   * */
  public SpecificationUtils() {}

  /** 
   * The method takes the value passed in the paramter and uses
   * it run a query using the '=' opeartor to check for any entity equal 
   * to the passed value.
   *
   * @param <T> type of different generic specification
   * @param field The column name from the database.
   * @param value The value to compared with to be equal.
   * @return An object of org.springframework.data.jpa.domain.Specification.
   * */
  public static <T> Specification<T> equal(String field, Object value) {
    return (root, query, cb) -> value == null ? null : cb.equal(root.get(field), value);
  }

  /** 
   * The method takes the value passed in the paramter and uses
   * it run a query using the 'like' opeartor to check for any entity equal 
   * to the passed value.
   *
   * @param <T> type of different generic specification
   * @param field The column name from the database.
   * @param value The value to compared with to be like.
   * @return An object of org.springframework.data.jpa.domain.Specification.
   * */
  public static <T> Specification<T> like(String field, Object value) {
    return (root, query, cb) -> (value == null) ? null : cb.like(cb.lower(root.get(field)), "%" + value + "%");
  }

  /** 
   * The method takes the value passed in the paramter and uses
   * it run a query using the 'in' opeartor to check whether defined value in the
   * list of values in present inside the defined column.
   * 
   * @param <T> type of different generic specification
   * @param field The column name from the database.
   * @param values The value to compared with to be like.
   * @return An object of org.springframework.data.jpa.domain.Specification.
   * */
  public static <T> Specification<T> in(String field, List<?> values) {
    return (root, query, cb) -> (values == null || values.isEmpty()) ? null : root.get(field).in(values);
  }
}
