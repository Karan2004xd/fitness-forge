import { createSelector } from "@reduxjs/toolkit";
import { RootState } from "../store";
import { ExerciseState } from "./exercise.reducer";

export const selectExersiceReducer = (state: RootState): ExerciseState => state.exercise;

export const selectCurrentExercises = createSelector(
  selectExersiceReducer,
  (exercise: ExerciseState) => exercise.currentExercises
);

export const selectTotalExercises = createSelector(
  selectExersiceReducer,
  (exercise: ExerciseState) => exercise.totalExercises
);
