package com.fitnessforge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fitnessforge.entity.Workout;

/** 
 * <b>Description:</b>
 * <p>
 *  Interface which provides the methods to perform CRUD operation on the database 
 *  table entity {@link com.fitnessforge.entity.Workout} 
 * </p>
 * */
@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {

  /** 
   * Fetches the {@link com.fitnessforge.entity.Workout} by its name, if it
   * exists in the database.
   *
   * @param name the name of entity {@link com.fitnessforge.entity.Workout} 
   * @return Optional {@link com.fitnessforge.entity.Exercise}
   * */
  Optional<Workout> findByName(String name);

  /** 
   * Deletes the existing {@link com.fitnessforge.entity.Workout} by its
   * numeric id from the database.
   *
   * @param id the numeric id of the {@link com.fitnessforge.entity.Workout}
   * */
  void deleteById(Long id);
}
