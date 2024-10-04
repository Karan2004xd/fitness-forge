import { call, put, all, takeLatest } from "@redux-saga/core/effects";
import { PayloadAction } from "@reduxjs/toolkit";
import { AxiosResponse } from "axios";
import { ApiVariables, makeGetRequest, makePostRequest } from "../../utils/api/api-calls.utils";
import { AUTHENTICATION, MEMBER_API_ROUTES } from "../../utils/api/api-routes.util";
import { signInWithGooglePopUp } from "../../utils/firebase/firebase.util";
import { 
  googleSignUpFailed, 
  googleSignUpStart, 
  googleSignUpSuccess, 
  signInFailed, 
  signInStart, 
  signInSuccess, 
  signUpFailed, 
  signUpStart, 
  signUpSuccess 
} from "./member.reducer";
import { Member } from "./member.types";

const signUpApi = async (path: string, member: Member | any) => {
  try {
    const response = makePostRequest(path, member);
    return response;
  } catch (error) {
    throw error;
  }
};

const getMemberById = async (id: number, accessToken: ApiVariables) => {
  try {
    const resposne = makeGetRequest(`${MEMBER_API_ROUTES.getMember}${id}`, accessToken);
    return resposne;
  } catch (error) {
    throw error;
  }
}

function* signUp(action: PayloadAction<{member: Member}>) {
  try {
    const member: AxiosResponse = yield call(signUpApi, MEMBER_API_ROUTES.createMember, action.payload.member);
    yield put(signUpSuccess({member: member.data}));
  } catch (error: any) {
    yield put(signUpFailed(error.response.data));
  }
}

function* googleSignUp(action: PayloadAction<{member: Member}>) {
  try {
    const { user } = yield call(signInWithGooglePopUp);
    const { email, uid, displayName } = user;
    const newMember = {
      ...action.payload.member,
      email: email,
      password: uid,
      name: displayName
    };

    const createdMember: AxiosResponse = yield call(signUpApi, MEMBER_API_ROUTES.createMember, newMember);
    yield put(googleSignUpSuccess({member: createdMember.data}));

  } catch (error: any) {
    yield put(googleSignUpFailed(error.response.data));
  }
}

function* signIn(action: PayloadAction<{ member: Member}> ) {
  try {
    const { email, password } = action.payload.member;
    const firstResponse: AxiosResponse = yield call(
      signUpApi,
      AUTHENTICATION.authencticateMember,
      { email: email, password: password });

    const authenticatedMember: Member = {
      ...firstResponse.data
    };

    if (authenticatedMember.id) {
      console.log('entered')
      const accessToken = firstResponse.headers['auhorization'];
      const secondResponse: AxiosResponse = yield call(getMemberById, authenticatedMember.id, accessToken);

      const resultMember: Member = {
        ...secondResponse.data,
        accessToken: accessToken
      };

      yield put(signInSuccess({member: resultMember}));
    }

  } catch (error: any) {
    yield put(signInFailed(error.response.data));
  }
}

export function* onSignUpStart() {
  yield takeLatest(signUpStart.type, signUp);
}

export function* onGoogleSignUpStart() {
  yield takeLatest(googleSignUpStart.type, googleSignUp);
}

export function* onSignInStart() {
  yield takeLatest(signInStart.type, signIn);
}

export function* memberSagas() {
  yield all([
    call(onSignUpStart),
    call(onGoogleSignUpStart),
    call(onSignInStart)
  ]);
}
