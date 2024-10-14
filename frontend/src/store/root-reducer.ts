import { combineReducers } from "@reduxjs/toolkit";
import exerciseReducer from "./exercise/exercise.reducer";
import memberReducer from "./member/member.reducer";

export const rootReducer = combineReducers({
  member: memberReducer,
  exercise: exerciseReducer
});
