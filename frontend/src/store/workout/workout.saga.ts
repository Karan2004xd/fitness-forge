import { put, select, call, takeLatest, all } from "@redux-saga/core/effects";
import { PayloadAction } from "@reduxjs/toolkit";
import { AxiosResponse } from "axios";
import { getRequestParamsUrl, makePostRequest } from "../../utils/api/api-calls.utils";
import { WORKOUT_API_ROUTES } from "../../utils/api/api-routes.util";
import { selectCurrentMember } from "../member/member.selector";
import { createWorkoutFailed, createWorkoutStart, createWorkoutSuccess } from "./workout.reducer";
import { Workout } from "./workout.types";

function* createWorkout(action: PayloadAction<{ workout: Workout }>) {
  try {
    const { id, accessToken } = yield select(selectCurrentMember);

    if (id && accessToken) {
      const map = new Map<string, number | string | string[]>();
      map.set('memberId', id);

      const url: string = getRequestParamsUrl(map, WORKOUT_API_ROUTES.createWorkout);
      const response: AxiosResponse = yield call(
        makePostRequest, 
        url, 
        action.payload.workout,
        { accessToken }
      );

      yield put(createWorkoutSuccess({ workout: response.data }));
    }
  } catch (error: any) {
    yield put(createWorkoutFailed(error.response.data));
  }
};

export function* onCreateWorkoutStart() {
  yield takeLatest(createWorkoutStart.type, createWorkout);
}

export function* workoutSagas() {
  yield all([
    call(onCreateWorkoutStart)
  ]);
};
