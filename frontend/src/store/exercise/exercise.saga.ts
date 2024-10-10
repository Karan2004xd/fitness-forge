import { 
  all,
  call,
  put,
  select,
  takeLatest 
} from "@redux-saga/core/effects";

import { PayloadAction } from "@reduxjs/toolkit";
import { AxiosResponse } from "axios"
import { makeGetRequest } from "../../utils/api/api-calls.utils"
import { EXERCISE_API_ROUTES } from "../../utils/api/api-routes.util";
import { selectCurrentMember } from "../member/member.selector";
import { Member } from "../member/member.types";

import { 
  fetchExerciseByExerciseIdFailed,
  fetchExerciseByExerciseIdStart,
  fetchExerciseByExerciseIdSuccess,
  fetchExerciseByPageFailed,
  fetchExerciseByPageStart,
  fetchExerciseByPageSuccess,
  fetchTotalExercisesFailed,
  fetchTotalExercisesStart,
  fetchTotalExercisesSuccess 
} from "./exercise.reducer";

import { Exercise } from "./exercise.types";

function* getTotalExercises() {
  try {
    const accessMember: Member = yield select(selectCurrentMember);
    const { accessToken } = accessMember;
    if (accessToken) {
      const response: AxiosResponse = yield call(makeGetRequest, EXERCISE_API_ROUTES.getTotalExercises, { accessToken });
      const { count } = response.data;

      if (count) {
        yield put(fetchTotalExercisesSuccess(count));
      }
    }
  } catch (error: any) {
    yield put(fetchTotalExercisesFailed(error.response.data));
  }
};

function* getExerciseByPage(action: PayloadAction<{pageNumber: number; size: number}>) {
  try {
    const accessMember: Member = yield select(selectCurrentMember);
    const { accessToken } = accessMember;

    if (accessToken) {
      const { pageNumber, size } = action.payload;
      const url = `${EXERCISE_API_ROUTES.getExerciseByPage}?page=${pageNumber}&size=${size}`;

      const response: AxiosResponse = yield call(makeGetRequest, url, { accessToken });
      if (response.data) {
        const exercises: Exercise[] = response.data;
        yield put(fetchExerciseByPageSuccess({currentExercises: exercises, currentPage: pageNumber}));
      }
    }
  } catch (error: any) {
    yield put(fetchExerciseByPageFailed(error.response.data));
  }
};

function* getExerciseById(action: PayloadAction<{exerciseId: number}>) {
  try {
    const accessMember: Member = yield select(selectCurrentMember);
    const { accessToken } = accessMember;

    if (accessToken) {
      const { exerciseId } = action.payload;
      const url = `${EXERCISE_API_ROUTES.getExerciseById}${exerciseId}`;

      const response: AxiosResponse = yield call(makeGetRequest, url, { accessToken });
      if (response.data) {
        const exercise: Exercise = response.data;
        yield put(fetchExerciseByExerciseIdSuccess({currentExercise: exercise}));
      }
    }

  } catch (error: any) {
    yield put(fetchExerciseByExerciseIdFailed(error.response.data));
  }
};

export function* onGetTotalExercisesStart() {
  yield takeLatest(fetchTotalExercisesStart.type, getTotalExercises);
}

export function* onGetExerciseByPageStart() {
  yield takeLatest(fetchExerciseByPageStart.type, getExerciseByPage);
}

export function* onGetExerciseById() {
  yield takeLatest(fetchExerciseByExerciseIdStart.type, getExerciseById);
}

export function* exerciseSagas() {
  yield all([
    call(onGetTotalExercisesStart),
    call(onGetExerciseByPageStart),
    call(onGetExerciseById)
  ]);
}
