import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { Exercise } from "./exercise.types"

export type ExerciseState = {
  currentExercises: Exercise[] | null;
  isLoading: boolean;
  error: Object | null;
  totalExercises: number;
};

const initialState: ExerciseState = {
  currentExercises: null,
  isLoading: false,
  error: null,
  totalExercises: 0
};

const exerciseSlice = createSlice({
  name: 'exercise',
  initialState,
  reducers: {
    fetchTotalExercisesStart: (state) => {
      state.isLoading = true;
    },

    fetchTotalExercisesSuccess: (state, action: PayloadAction<{ exercise: ExerciseState }> ) => {
      state.isLoading = false;
    },
    
    fetchTotalExercisesFailed: (state, action: PayloadAction<Object> ) => {
      state.error = action.payload;
      state.isLoading = false;
    }
  }
});

export const {
  fetchTotalExercisesStart,
  fetchTotalExercisesSuccess,
  fetchTotalExercisesFailed
} = exerciseSlice.actions;

export default exerciseSlice.reducer;
