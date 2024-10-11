package com.fitnessforge.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fitnessforge.entity.Exercise;
import com.fitnessforge.service.ExerciseService;

/** 
 * <b>Description:</b>
 * <p>
 *  Concrete Controller class for {@link com.fitnessforge.service.ExerciseService} services and entity
 * </p>
 * */
@RestController
@CrossOrigin("*")
@RequestMapping("/exercise")
public class ExerciseController {

  @Autowired
  private ExerciseService exerciseService;

  /** 
   * Empty default constructor
   * */
  public ExerciseController() {}

  /** 
   * Fetches data in pagination format which by page number and size
   *
   * @param page number of the page.
   * @param size number of entries to display in a page.
   * @return an object of org.springframework.http.ResponseEntity
   * */
  @GetMapping("/page")
  public ResponseEntity<List<Exercise>> getExerciseByPage(
    @RequestParam(value = "page", defaultValue = "0") int page,
    @RequestParam(value = "size", defaultValue = "10") int size
  ) {
    return new ResponseEntity<>(exerciseService.getExerciseByPage(page, size), HttpStatus.OK);
  }

  @GetMapping("/page/filter")
  public ResponseEntity<List<Exercise>> getExerciseByPageWithFilter(
    @RequestParam(value = "page", defaultValue = "0") int page,
    @RequestParam(value = "size", defaultValue = "10") int size,

    // optional filters
    @RequestParam(value = "category", required = false) String category,
    @RequestParam(value = "equipment", required = false) String equipment,
    @RequestParam(value = "force", required = false) String force,
    @RequestParam(value = "level", required = false) String level,
    @RequestParam(value = "mechanic", required = false) String mechanic,
    @RequestParam(value = "name", required = false) String name 
  ) {

    Map<String, String> filters = new HashMap<>();

    filters.put("category", category);
    filters.put("equipment", equipment);
    filters.put("force", force);
    filters.put("level", level);
    filters.put("mechanic", mechanic);
    filters.put("name", name);

    return new ResponseEntity<>(exerciseService.getExerciseByPage(page, size, filters), HttpStatus.OK);
  }

  /** 
   * Fetches the total exercises present in the database
   *
   * @return an object of org.springframework.http.ResponseEntity
   * */
  @GetMapping("/count")
  public ResponseEntity<Map<String, Long>> getTotalExercisesCount() {
    Map<String, Long> response = new HashMap<>();
    response.put("count", exerciseService.getTotalExercisesCount());
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  /** 
   * Fetches the {@link com.fitnessforge.entity.Exercise} by its numeric id
   *
   * @param exerciseId the numeric id of entity {@link com.fitnessforge.entity.Exercise}
   * @return an object of org.springframework.http.ResponseEntity
   * */
  @GetMapping("/{exerciseId}")
  public ResponseEntity<Exercise> getExerciseById(@PathVariable Long exerciseId) {
    return new ResponseEntity<>(exerciseService.getExerciseById(exerciseId), HttpStatus.OK);
  }
}
