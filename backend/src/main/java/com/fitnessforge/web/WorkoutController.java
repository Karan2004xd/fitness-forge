package com.fitnessforge.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
