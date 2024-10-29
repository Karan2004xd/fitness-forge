import { createSelector } from "@reduxjs/toolkit";
import { RootState } from "../store";
import { WorkoutState } from "./workout.reducer";

export const selectWorkoutReducer = (state: RootState): WorkoutState => state.workout;

export const selectCurrentWorkout = createSelector(
  selectWorkoutReducer,
  (state: WorkoutState) => state.currentWorkout
);

export const selectLastWorkout = createSelector(
  selectWorkoutReducer,
  (state: WorkoutState) => state.lastWorkout
);

export const selectFormFields = createSelector(
  selectWorkoutReducer,
  (state: WorkoutState) => state.formFields
);

export const selectCompleted = createSelector(
  selectWorkoutReducer,
  (state: WorkoutState) => state.completed
);

export const selectCurrentTemplates = createSelector(
  selectWorkoutReducer,
  (state: WorkoutState) => state.currentTemplates
);
