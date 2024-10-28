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
