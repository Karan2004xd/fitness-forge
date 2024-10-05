package com.fitnessforge.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fitnessforge.entity.Exercise;

@Repository
public interface ExerciseRepository extends CrudRepository<Exercise, Long> {
}
