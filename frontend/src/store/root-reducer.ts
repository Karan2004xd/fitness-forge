import { combineReducers } from "@reduxjs/toolkit";
import exerciseReducer from "./exercise/exercise.reducer";
import memberReducer from "./member/member.reducer";
import workoutReducer from "./workout/workout.reducer";

export const rootReducer = combineReducers({
  member: memberReducer,
  exercise: exerciseReducer,
  workout: workoutReducer
});
