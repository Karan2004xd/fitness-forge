import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { Member } from "./member.types";

export type UserState = {
  currentMember: Member | null;
  isLoading: boolean;
  error: Object | null
};

const initialState: UserState = {
  currentMember: null,
  isLoading: false,
  error: null
};

const memberSlice = createSlice({
  name: 'member',
  initialState,
  reducers: {
    signUpStart: (state, action: PayloadAction<{ member: Member }>) => {
      state.isLoading = true;
      state.currentMember = action.payload.member;
    },

    signUpSuccess: (state, action: PayloadAction<{ member: Member }>) => {
      state.isLoading = false;
      state.currentMember = action.payload.member;
    },
    
    signUpFailed: (state, action: PayloadAction<Object>) => {
      state.error = action.payload;
      state.isLoading = false;
      state.currentMember = null;
    },

    googleSignUpStart: (state, action: PayloadAction<{ member: Member }>) => {
      state.isLoading = false;
      state.currentMember = action.payload.member;
    },
    
    googleSignUpSuccess: (state, action: PayloadAction<{ member: Member }> ) => {
      state.currentMember = action.payload.member;
      state.isLoading = true;
    },

    googleSignUpFailed: (state, action: PayloadAction<Object>) => {
      state.currentMember = null;
      state.error = action.payload;
      state.isLoading = false;
    },

    signInStart: (state, action: PayloadAction<{ member: Member }> ) => {
      state.isLoading = true;
      state.currentMember = action.payload.member;
    },

    signInSuccess: (state, action: PayloadAction<{ member: Member }> ) => {
      state.isLoading = false;
      state.currentMember = action.payload.member;
    },

    signInFailed: (state, action: PayloadAction<Object>) => {
      state.isLoading = false;
      state.error = action.payload;
    },
  }
});

export const {
  signUpStart,
  signUpSuccess,
  signUpFailed,
  googleSignUpStart,
  googleSignUpFailed,
  googleSignUpSuccess,
  signInStart,
  signInSuccess,
  signInFailed
} = memberSlice.actions;

export default memberSlice.reducer;
