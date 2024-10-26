import { call, put, all, takeLatest } from "@redux-saga/core/effects";
import { PayloadAction } from "@reduxjs/toolkit";
import { AxiosResponse } from "axios";
import { UserCredential } from "firebase/auth";
import { ApiVariables, makeGetRequest, makePostRequest } from "../../utils/api/api-calls.utils";
import { AUTHENTICATION, MEMBER_API_ROUTES } from "../../utils/api/api-routes.util";
import { signInWithGooglePopUp } from "../../utils/firebase/firebase.util";
import { 
  googleSignInFailed,
  googleSignInStart,
  googleSignInSuccess,
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

const getAccessTokenAndId = async (email: string, password: string) => {
  const firstResponse: AxiosResponse = await signUpApi(
    AUTHENTICATION.authencticateMember,
    { email: email, password: password }
  );

  let accessToken: string = firstResponse.headers['authorization'];

  if (accessToken) {
    accessToken = accessToken.replace('Bearer', '').trim();

    const resultMember: Member = {
      accessToken: accessToken,
      email: email,
      password: undefined,
      id: firstResponse.data.id
    };
    return resultMember;
  }
};

const populateMember = async (id: number, accessToken: string) => {
  const secondResponse: AxiosResponse = await getMemberById(id, { accessToken });

  const resultMember: Member = {
    ...secondResponse.data,
    accessToken: accessToken
  };

  return resultMember;
};

const signInMember = async (email: string, password: string) => {
  try {
    const accessMember = await getAccessTokenAndId(email, password)
    if (accessMember && accessMember.id && accessMember.accessToken) {
      const resultMember: Member = await populateMember(accessMember.id, accessMember.accessToken);
      return resultMember;
    }
  } catch (error: any){
    throw error;
  }
}

const signUpMemberWithGoogle = async (userCredential: UserCredential, member?: Member) => {
  try {
    const { user } = userCredential
    const { email, uid, displayName } = user;
    const newMember = {
      ...member,
      email: email,
      password: uid,
      name: displayName
    };

    await signUpApi(MEMBER_API_ROUTES.createMember, newMember);
    if (email && uid) {
      const resultMember = await getAccessTokenAndId(email, uid);
      return resultMember;
    }
  } catch (error: any) {
    throw error;
  }
};

function* signUp(action: PayloadAction<{member: Member}>) {
  try {
    const respone: AxiosResponse = yield call(signUpApi, MEMBER_API_ROUTES.createMember, action.payload.member);
    const { email, password } = action.payload.member;

    if (email && password) {
      const accessMember: Member = yield call(getAccessTokenAndId, email, password);
      const resultMember = {
        ...accessMember,
        ...respone.data,
      }
      yield put(signUpSuccess({member: resultMember}));
    }

  } catch (error: any) {
    yield put(signUpFailed(error.response.data));
  }
}

function* googleSignUp() {
  try {
    const userCredential: UserCredential = yield call(signInWithGooglePopUp);
    const createdMember: Member = yield call(signUpMemberWithGoogle, userCredential);

    yield put(googleSignUpSuccess({member: createdMember}));
  } catch (error: any) {
    yield put(googleSignUpFailed(error));
  }
}

function* signIn(action: PayloadAction<{ member: Member}> ) {
  try {
    const { email, password } = action.payload.member;
    if (email && password) {
      const resultMember: Member = yield call(signInMember, email, password);
      yield put(signInSuccess({member: resultMember}));
    }
  } catch (error: any) {
    yield put(signInFailed(error.response.data));
  }
}

function* googleSignIn() {
  const userCredential: UserCredential = yield call(signInWithGooglePopUp);

  try {
    const { user } = userCredential;
    const { email, uid } = user;

    if (email && uid) {
      const member: Member = yield call(signInMember, email, uid);
      yield put(googleSignInSuccess({member: member}));
    }

  } catch (error: any) {
    try {
      const member: Member = yield call(signUpMemberWithGoogle, userCredential);
      yield put(googleSignUpSuccess({member: member}));
      return;
    } catch (error: any) {
      yield put(googleSignUpFailed(error.response.data));
    }
    yield put(googleSignInFailed(error.response.data));
  }
}

export function* onSignUpStart() {
  yield takeLatest(signUpStart.type, signUp);
}

export function* onGoogleSignUpStart() {
  yield takeLatest(googleSignUpStart.type, googleSignUp);
}

export function* onGoogleSignInStart() {
  yield takeLatest(googleSignInStart.type, googleSignIn);
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
