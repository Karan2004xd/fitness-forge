import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { Member } from "./member.types";

export type MemberState = {
  currentMember: Member | null;
  isAuthenticated: boolean;
  error: Object | null
};

const initialState: MemberState = {
  currentMember: null,
  isAuthenticated: false,
  error: null
};

const memberSlice = createSlice({
  name: 'member',
  initialState,
  reducers: {
    signUpStart: (state, action: PayloadAction<{ member: Member }>) => {
      state.isAuthenticated = false;
      state.currentMember = action.payload.member;
    },

    signUpSuccess: (state, action: PayloadAction<{ member: Member }>) => {
      state.isAuthenticated = true;
      state.currentMember = action.payload.member;
    },
    
    signUpFailed: (state, action: PayloadAction<Object>) => {
      state.error = action.payload;
      state.isAuthenticated = false;
      state.currentMember = null;
    },

    googleSignUpStart: (state) => {
      state.isAuthenticated = false;
    },
    
    googleSignUpSuccess: (state, action: PayloadAction<{ member: Member }> ) => {
      state.currentMember = action.payload.member;
      state.isAuthenticated = true;
    },

    googleSignUpFailed: (state, action: PayloadAction<Object>) => {
      state.currentMember = null;
      state.error = action.payload;
      state.isAuthenticated = false;
    },

    signInStart: (state, action: PayloadAction<{ member: Member }> ) => {
      state.isAuthenticated = false;
      state.currentMember = action.payload.member;
    },

    signInSuccess: (state, action: PayloadAction<{ member: Member }> ) => {
      state.isAuthenticated = true;
      state.currentMember = action.payload.member;
    },

    signInFailed: (state, action: PayloadAction<Object>) => {
      state.isAuthenticated = false;
      state.error = action.payload;
      state.currentMember = null;
    },

    googleSignInStart: (state) => {
      state.isAuthenticated = false;
    },

    googleSignInSuccess: (state, action: PayloadAction<{ member: Member}> ) => {
      state.isAuthenticated = true;
      state.currentMember = action.payload.member;
    },

    googleSignInFailed: (state, action: PayloadAction<Object> ) => {
      state.isAuthenticated = false;
      state.error = action.payload;
    },
    
    signOutStart: (state) => {
      state.isAuthenticated = true;
    },

    signOutSuccess: (state, action: PayloadAction<{ member: Member | null}> ) => {
      state.isAuthenticated = false;
      state.currentMember = action.payload.member;
    },

    signOutFailed: (state, action: PayloadAction<Object> ) => {
      state.isAuthenticated = true;
      state.error = action.payload;
    }
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
  signInFailed,
  googleSignInStart,
  googleSignInSuccess,
  googleSignInFailed,
  signOutStart,
  signOutSuccess,
  signOutFailed
} = memberSlice.actions;

export default memberSlice.reducer;
