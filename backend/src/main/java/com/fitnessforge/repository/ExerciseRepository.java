package com.fitnessforge.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fitnessforge.entity.Exercise;

/** 
 * <b>Description:</b>
 * <p>
 *  Interface which provides the methods to perform CRUD operation on the database 
 *  table entity {@link com.fitnessforge.entity.Exercise} 
 * </p>
 * */
@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long>, JpaSpecificationExecutor<Exercise> {

  /** 
   * Fetches the total count of the exercises currently
   * stored in the database
   *
   * @return total count of exercise stored in the table
   * */
  @Query("SELECT COUNT(e) FROM Exercise e")
  Long getDataCount();

  /** 
   * This methods fetches exercises in a pagination format,
   * meaning it fetches pages based on their size and page number.
   *
   * @param pageable an object of interface org.springframework.data.domain.Pageable
   * @return an object of org.springframework.data.domain.Page
   * */
  Page<Exercise> findAll(Pageable pageable);

  /** 
   * Fetches the {@link com.fitnessforge.entity.Exercise} by its numeric id.
   *
   * @param exerciseId the numeric id of entity {@link com.fitnessforge.entity.Exercise} 
   * @return Optional {@link com.fitnessforge.entity.Exercise}
   * */
  Optional<Exercise> findByExerciseId(Long exerciseId);

  /** 
   * This methods fetches exercises in a pagination format,
   * meaning it fetches pages based on their size and page number.
   *
   * It also filters the pages based on the specification passed in the parameter
   *
   * @param pageable an object of interface org.springframework.data.domain.Pageable
   * @param specification an object of interface org.springframework.data.jpa.domain.Specification
   * @return an object of org.springframework.data.domain.Page
   * */
  Page<Exercise> findAll(Specification<Exercise> specification, Pageable pageable);
}
