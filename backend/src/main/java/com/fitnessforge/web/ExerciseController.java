package com.fitnessforge.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fitnessforge.entity.Exercise;
import com.fitnessforge.service.ExerciseService;

@RestController
@CrossOrigin("*")
@RequestMapping("/exercise")
public class ExerciseController {

  @Autowired
  private ExerciseService exerciseService;

  @GetMapping("/{id}")
  public ResponseEntity<Exercise> getExerciseById(@PathVariable Long id) {
    return new ResponseEntity<>(exerciseService.getExerciseById(id), HttpStatus.OK);
  }

}
