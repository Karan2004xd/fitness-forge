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
  lastDeletedWorkout: number | undefined;
  lastWorkout: Workout | null;
};

const initialState: WorkoutState = {
  currentWorkout: null,
  error: null,
  workoutExercises: [],
  lastDeletedWorkout: undefined,
  lastWorkout: null
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

    deleteWorkoutStart: (state, action: PayloadAction<{ workoutId: number }>) => {
      state.lastDeletedWorkout = action.payload.workoutId;
    },

    deleteWorkoutSuccess: (state, action: PayloadAction<{ workoutId: number }>) => {
      state.lastDeletedWorkout = action.payload.workoutId;
    },

    deleteWorkoutFailed: (state, action: PayloadAction<Object>) => {
      state.error = action.payload;
      state.lastDeletedWorkout = undefined;
    },

    updateWorkoutStart: (state, action: PayloadAction<{ workout: Workout }>) => {
      state.lastWorkout = state.currentWorkout;
      state.currentWorkout = action.payload.workout;
    },

    updateWorkoutSuccess: (state, action: PayloadAction<{ workout: Workout }>) => {
      state.currentWorkout = action.payload.workout;
      state.lastWorkout = null;
    },

    updateWorkoutFailed: (state, action: PayloadAction<Object>) => {
      state.error = action.payload;
      state.currentWorkout = state.lastWorkout;
    },
  }
});

export const {
  createWorkoutStart,
  createWorkoutSuccess,
  createWorkoutFailed,
  deleteWorkoutStart,
  deleteWorkoutSuccess,
  deleteWorkoutFailed,
  updateWorkoutStart,
  updateWorkoutSuccess,
  updateWorkoutFailed
} = workoutSlice.actions;

export default workoutSlice.reducer;
