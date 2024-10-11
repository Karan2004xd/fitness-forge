package com.fitnessforge.utils;

import org.springframework.data.jpa.domain.Specification;

public class SpecificationUtils {

  public static <T> Specification<T> equal(String field, Object value) {
    return (root, query, cb) -> value == null ? null : cb.equal(root.get(field), value);
  }

  public static <T> Specification<T> like(String field, Object value) {
    return (root, query, cb) -> (value == null) ? null : cb.like(cb.lower(root.get(field)), "%" + value + "%");
  }
}
