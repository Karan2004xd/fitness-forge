package com.fitnessforge.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fitnessforge.entity.Workout;
import com.fitnessforge.service.WorkoutService;

import jakarta.validation.Valid;

/** 
 * <b>Description:</b>
 * <p>
 *  Concrete Controller class for {@link com.fitnessforge.service.WorkoutService} services and entity
 * </p>
 * */
@RestController
@CrossOrigin("*")
@RequestMapping("/workout")
public class WorkoutController {

  @Autowired
  private WorkoutService workoutService;

  /** 
   * Empty Default Constructor
   * */
  public WorkoutController() {

  }

  /** 
   * Creates a new workout entry in the database if already not existing.
   *
   * @param workout an object of entity {@link com.fitnessforge.entity.Workout}
   * @param memberId the id of entity {@link com.fitnessforge.entity.Member}
   * @return an object of org.springframework.http.ResponseEntity
   * */
  @PostMapping("/create")
  public ResponseEntity<Workout> createWorkout(
    @Valid @RequestBody Workout workout,
    @RequestParam(name = "memberId", required = true) Long memberId
  ) {
    return new ResponseEntity<>(workoutService.createNewWorkout(workout, memberId), HttpStatus.CREATED);
  }

  /** 
   * Fetches the workout based on the id based in the path variable
   *
   * @param id the id of {@link com.fitnessforge.entity.Workout}
   * @return an object of org.springframework.http.ResponseEntity
   * */
  @GetMapping("/{id}")
  public ResponseEntity<Workout> getWorkout(@PathVariable Long id) {
    return new ResponseEntity<>(workoutService.getWorkout(id), HttpStatus.OK);
  }

  /** 
   * Fetches the workout based on the name passed of the workout
   * passed in the request parameters.
   *
   * @param name the name of the workout
   * @return an object of org.springframework.http.ResponseEntity
   * */
  @GetMapping("")
  public ResponseEntity<Workout> getWorkoutByName(
    @RequestParam(name = "name", required = true) String name
  ) {
    return new ResponseEntity<>(workoutService.getWorkout(name), HttpStatus.OK);
  }

  /** 
   * Fetches the list of exercises variations based on the workout template
   * passed, which is fetched using the id passed in the path variable.
   *
   * @param id the id of the workout.
   * @return an object of org.springframework.http.ResponseEntity
   * */
  @GetMapping("/{id}/exercises")
  public ResponseEntity<List<Map<String, Object>>> getWorkoutExercises(@PathVariable Long id) {
    Workout workout = workoutService.getWorkout(id);
    return new ResponseEntity<>(workoutService.getWorkoutExercises(workout), HttpStatus.OK);
  }

  /** 
   * Updates the existing workout based on the id of the passed workout
   *
   * @param workout an object of entity {@link com.fitnessforge.entity.Workout}
   * @return an object of org.springframework.http.ResponseEntity
   * */
  @PutMapping("/update")
  public ResponseEntity<Workout> updateWorkout(@Valid @RequestBody Workout workout) {
    return new ResponseEntity<>(workoutService.updateWorkout(workout), HttpStatus.OK);
  }

  /** 
   * Deletes the workout based on the id of the workout passed
   * in the request params and also updates the deleted id in the
   * entity {@link com.fitnessforge.entity.Workout}
   *
   * @param workoutId the id of entity {@link com.fitnessforge.entity.Workout}
   * @param memberId the id of entity {@link com.fitnessforge.entity.Member}
   * @return an object of org.springframework.http.ResponseEntity
   * */
  @DeleteMapping("/delete")
  public ResponseEntity<HttpStatus> deleteWorkout(
    @RequestParam(name = "workoutId", required = true) Long workoutId,
    @RequestParam(name = "memberId", required = true) Long memberId 
  ) {
    workoutService.deleteWorkout(workoutId, memberId);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
