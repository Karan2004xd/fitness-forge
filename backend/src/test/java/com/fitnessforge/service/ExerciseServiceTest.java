package com.fitnessforge.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.fitnessforge.entity.Exercise;
import com.fitnessforge.repository.ExerciseRepository;

public class ExerciseServiceTest {

  @Mock
  private ExerciseRepository exerciseRepository;

  @InjectMocks
  private ExerciseService exerciseService = new ExerciseServiceImpl();

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void getCountTest() {
    Long count = (long) 873;
    when(exerciseRepository.getDataCount()).thenReturn(count);
    Long testCount = exerciseService.getTotalExercisesCount();

    assertNotNull(testCount);
    assertEquals(count, testCount);
  }

  private boolean checkTwoListElements(List<?> listOne, List<?> listTwo) {
    if (listOne.size() != listTwo.size()) return false;

    for (int i = 0; i < listOne.size(); i++) {
      if (!listOne.get(i).equals(listTwo.get(i))) return false;
    }
    return true;
  }

  private Exercise getExerciseDummyObject() {
    List<String> images = List.of("3_4_Sit-Up/0.jpg", "3_4_Sit-Up/1.jpg");
    List<String> instructions = List.of(
      "Lie down on the floor and secure your feet. Your legs should be bent at the knees.",
      "Place your hands behind or to the side of your head. You will begin with your back on the ground. This will be your starting position.",
      "Flex your hips and spine to raise your torso toward your knees.",
      "At the top of the contraction your torso should be perpendicular to the ground. Reverse the motion, going only ¾ of the way down.",
      "Repeat for the recommended amount of repetitions."
    );
    List<String> primaryMuscles = List.of("abdominals");

    Exercise exercise = new Exercise();

    exercise.setExerciseId(1L);
    exercise.setCategory("strength");
    exercise.setEquipment("body only");
    exercise.setForce("pull");

    exercise.setId("3_4_Sit-Up");
    exercise.setImages(images);
    exercise.setInstructions(instructions);
    exercise.setLevel("beginner");

    exercise.setMechanic("compound");
    exercise.setName("3/4 Sit-Up");
    exercise.setPrimaryMuscles(primaryMuscles);

    return exercise;
  }

  @Test
  public void getExerciseByIdTest() {

    List<String> images = List.of("3_4_Sit-Up/0.jpg", "3_4_Sit-Up/1.jpg");
    List<String> instructions = List.of(
      "Lie down on the floor and secure your feet. Your legs should be bent at the knees.",
      "Place your hands behind or to the side of your head. You will begin with your back on the ground. This will be your starting position.",
      "Flex your hips and spine to raise your torso toward your knees.",
      "At the top of the contraction your torso should be perpendicular to the ground. Reverse the motion, going only ¾ of the way down.",
      "Repeat for the recommended amount of repetitions."
    );
    List<String> primaryMuscles = List.of("abdominals");

    Long id = (long) 1;
    String category = "strength";
    String equipment = "body only";
    String force = "pull";

    String idName = "3_4_Sit-Up";
    String level = "beginner";
    String mechanic = "compound";
    String name = "3/4 Sit-Up";

    Exercise exercise = getExerciseDummyObject();

    when(exerciseRepository.findByExerciseId(id)).thenReturn(Optional.of(exercise));

    Exercise testExercise = exerciseService.getExerciseById(id);

    assertNotNull(testExercise);

    assertEquals(id, testExercise.getExerciseId());
    assertEquals(category, testExercise.getCategory());
    assertEquals(equipment, testExercise.getEquipment());
    assertEquals(force, testExercise.getForce());

    assertEquals(idName, testExercise.getId());
    assertEquals(level, testExercise.getLevel());
    assertEquals(mechanic, testExercise.getMechanic());
    assertEquals(name, testExercise.getName());

    assertTrue(checkTwoListElements(primaryMuscles, testExercise.getPrimaryMuscles()));
    assertTrue(checkTwoListElements(instructions, testExercise.getInstructions()));
    assertTrue(checkTwoListElements(images, testExercise.getImages()));
  }

  @Test
  public void getExerciseByPageTest() {
    int page = 0;
    int size = 1;

    Pageable pageable = PageRequest.of(page, size);

    Exercise exercise = getExerciseDummyObject();

    List<Exercise> exerciseList = Arrays.asList(exercise);
    Page<Exercise> exercisePage = new PageImpl<>(exerciseList, pageable, exerciseList.size());

    when(exerciseRepository.findAll(pageable)).thenReturn(exercisePage);

    List<Exercise> testExercisePage = exerciseService.getExerciseByPage(page, size);

    assertNotNull(testExercisePage);
    assertTrue(checkTwoListElements(testExercisePage, exercisePage.getContent()));
  }
}
