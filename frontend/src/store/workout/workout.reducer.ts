import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { Exercise } from "../exercise/exercise.types";
import { Workout } from "./workout.types";

export type WorkoutExerciseType = {
  name: string;
  exercises: Exercise[];
};

export type WorkoutState = {
  currentWorkout: Workout | null;
  error: Object | null;
  workoutExercises: WorkoutExerciseType[];
};

const initialState: WorkoutState = {
  currentWorkout: null,
  error: null,
  workoutExercises: []
};

const workoutSlice = createSlice({
  name: 'workout',
  initialState,
  reducers: {
    createWorkoutStart: (state, action: PayloadAction<{ workout: Workout }>) => {
      state.currentWorkout = action.payload.workout;
    },

    createWorkoutSuccess: (state, action: PayloadAction< { workout: Workout }>) => {
      state.currentWorkout = action.payload.workout;
    },

    createWorkoutFailed: (state, action: PayloadAction<Object>) => {
      state.error = action.payload;
      state.currentWorkout = null;
    },
  }
});

export const {
  createWorkoutStart,
  createWorkoutSuccess,
  createWorkoutFailed
} = workoutSlice.actions;

export default workoutSlice.reducer;
