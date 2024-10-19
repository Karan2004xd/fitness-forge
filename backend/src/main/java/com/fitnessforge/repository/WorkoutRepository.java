package com.fitnessforge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fitnessforge.entity.Workout;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {

  Optional<Workout> findByName(String name);
}
