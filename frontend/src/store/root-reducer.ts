import { combineReducers } from "@reduxjs/toolkit";
import memberReducer from "./member/member.reducer";

export const rootReducer = combineReducers({
  member: memberReducer
});
