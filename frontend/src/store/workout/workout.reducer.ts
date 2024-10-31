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
  formFields: Workout | null;
  completed: boolean;
  currentTemplates: Workout[];
};

export const defaultFormFields: Workout = {
  name: '',
  level: 'Choose a level',
  duration: 0,
  workoutCategory: '',
  workoutDays: [],
  restDuration: 0,
  cardioDays: [],
  cardioDuration: 0,
  equipments: [],
  exerciseToExclude: [],
};

const initialState: WorkoutState = {
  currentWorkout: null,
  error: null,
  workoutExercises: [],
  lastDeletedWorkout: undefined,
  lastWorkout: null,
  formFields: defaultFormFields,
  completed: false,
  currentTemplates: [] 
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
      state.completed = true;
    },

    createWorkoutFailed: (state, action: PayloadAction<Object>) => {
      state.error = action.payload;
      state.currentWorkout = null;
      state.completed = false;
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

    setFormFields: (state, action: PayloadAction<{ formFields: Workout | null }>) => {
      state.formFields = action.payload.formFields;
    },

    setCompleted: (state, action: PayloadAction<{ completed: boolean }>) => {
      state.completed = action.payload.completed;
    },

    fetchCurrentTemplatesStart: (state) => {
      state.currentTemplates = [];
    },

    fetchCurrentTemplatesSuccess: (state, action: PayloadAction<{ currentTemplates: Workout[] }>) => {
      state.currentTemplates = action.payload.currentTemplates;
    },

    fetchCurrentTemplatesFailed: (state, action: PayloadAction<Object>) => {
      state.error = action.payload;
    },

    setCurrentWorkout: (state, action: PayloadAction<{ workout: Workout | null }>) => {
      state.currentWorkout = action.payload.workout;
    },

    fetchWorkoutExercisesStart: (state) => {
      state.completed = false;
    },

    fetchWorkoutExercisesSuccess: (
      state, 
      action: PayloadAction<{
        workoutExercises: WorkoutExerciseType[]
      }>) => {
      state.workoutExercises = action.payload.workoutExercises;
      state.completed = true;
    },

    fetchWorkoutExercisesFailed: (state, action: PayloadAction<Object>) => {
      state.error = action.payload;
      state.completed = false;
    }
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
  updateWorkoutFailed,
  setFormFields,
  setCompleted,
  fetchCurrentTemplatesStart,
  fetchCurrentTemplatesSuccess,
  fetchCurrentTemplatesFailed,
  setCurrentWorkout,
  fetchWorkoutExercisesStart,
  fetchWorkoutExercisesSuccess,
  fetchWorkoutExercisesFailed
} = workoutSlice.actions;

export default workoutSlice.reducer;
