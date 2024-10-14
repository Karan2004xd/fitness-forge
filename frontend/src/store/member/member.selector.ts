import { createSelector } from "@reduxjs/toolkit";
import { RootState } from "../store";
import { MemberState } from "./member.reducer";

export const selectMemberReducer = (state: RootState): MemberState => state.member;

export const selectCurrentMember = createSelector(
  selectMemberReducer,
  (member: MemberState) => member.currentMember
);

export const selectIsMemberAuthenticated = createSelector(
  selectMemberReducer,
  (member: MemberState) => member.isAuthenticated
)
