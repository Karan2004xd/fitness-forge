import { createSelector } from "@reduxjs/toolkit";
import { RootState } from "../store";
import { UserState } from "./member.reducer";

export const selectMemberReducer = (state: RootState): UserState => state.member;

export const selectCurrentMember = createSelector(
  selectMemberReducer,
  (member: UserState) => member.currentMember
);
