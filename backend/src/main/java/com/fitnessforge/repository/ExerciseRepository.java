package com.fitnessforge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fitnessforge.entity.Exercise;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

  @Query("SELECT COUNT(e) FROM Exercise e")
  Long getDataCount();

  Optional<Exercise> findById(Long id);
}
