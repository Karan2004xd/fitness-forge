import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { Exercise } from "./exercise.types"

export type ExerciseState = {
  currentExercises: Exercise[] | null;
  isLoading: boolean;
  currentPage: number;
  error: Object | null;
  totalExercises: number;
  currentExercise: Exercise | null;
};

const initialState: ExerciseState = {
  currentExercises: null,
  isLoading: false,
  error: null,
  totalExercises: 0,
  currentPage: 0,
  currentExercise: null
};

const exerciseSlice = createSlice({
  name: 'exercise',
  initialState,
  reducers: {
    fetchTotalExercisesStart: (state) => {
      state.isLoading = true;
    },

    fetchTotalExercisesSuccess: (state, action: PayloadAction<number> ) => {
      state.isLoading = false;
      state.totalExercises = action.payload;
    },
    
    fetchTotalExercisesFailed: (state, action: PayloadAction<Object> ) => {
      state.error = action.payload;
      state.isLoading = false;
    },

    fetchExerciseByPageStart: (state, action: PayloadAction<{pageNumber: number; size: number}>) => {
      state.isLoading = true;
      state.currentPage = action.payload.pageNumber;
    },
    
    fetchExerciseByPageSuccess: (
      state, 
      action: PayloadAction<{
        currentExercises: Exercise[];
        currentPage: number;
      }>) => {
      state.isLoading = false;
      state.currentExercises = action.payload.currentExercises;
      state.currentPage = action.payload.currentPage;
    },

    fetchExerciseByPageFailed: (state, action: PayloadAction<Object>) => {
      state.isLoading = false;
      state.currentExercises = null;
      state.error = action.payload;
    },

    fetchExerciseByExerciseIdStart: (state, action: PayloadAction<{exerciseId: number}>) => {
      state.isLoading = true;
      state.currentExercise = { exerciseId: action.payload.exerciseId };
    },

    fetchExerciseByExerciseIdSuccess: (state, action: PayloadAction<{currentExercise: Exercise}>) => {
      state.isLoading = false;
      state.currentExercise = action.payload.currentExercise;
    },

    fetchExerciseByExerciseIdFailed: (state, action: PayloadAction<Object>) => {
      state.isLoading = false;
      state.error = action.payload;
      state.currentExercise = null;
    }
  }
});

export const {
  fetchTotalExercisesStart,
  fetchTotalExercisesSuccess,
  fetchTotalExercisesFailed,
  fetchExerciseByPageStart,
  fetchExerciseByPageSuccess,
  fetchExerciseByPageFailed,
  fetchExerciseByExerciseIdStart,
  fetchExerciseByExerciseIdSuccess,
  fetchExerciseByExerciseIdFailed
} = exerciseSlice.actions;

export default exerciseSlice.reducer;
