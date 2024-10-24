package com.fitnessforge.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fitnessforge.entity.Exercise;
import com.fitnessforge.entity.Workout;
import com.fitnessforge.service.WorkoutService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/workout")
public class WorkoutController {

  @Autowired
  private WorkoutService workoutService;

  @PostMapping("/create")
  public ResponseEntity<Workout> createWorkout(@Valid @RequestBody Workout workout) {
    return new ResponseEntity<>(workoutService.createNewWorkout(workout), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Workout> getWorkout(@PathVariable Long id) {
    return new ResponseEntity<>(workoutService.getWorkout(id), HttpStatus.OK);
  }

  @GetMapping("")
  public ResponseEntity<Workout> getWorkoutByName(
    @RequestParam(name = "name", required = true) String name
  ) {
    return new ResponseEntity<>(workoutService.getWorkout(name), HttpStatus.OK);
  }

  @GetMapping("/{id}/exercises")
  public ResponseEntity<List<Map<String, List<Exercise>>>> getWorkoutExercises(@PathVariable Long id) {
    Workout workout = workoutService.getWorkout(id);
    return new ResponseEntity<>(workoutService.getWorkoutExercises(workout), HttpStatus.OK);
  }
}
