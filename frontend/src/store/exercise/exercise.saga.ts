import { all, call, put, select, takeLatest } from "@redux-saga/core/effects";
import { AxiosResponse } from "axios"
import { makeGetRequest } from "../../utils/api/api-calls.utils"
import { EXERCISE_API_ROUTES } from "../../utils/api/api-routes.util";
import { selectCurrentMember } from "../member/member.selector";
import { Member } from "../member/member.types";

import { 
  fetchTotalExercisesFailed,
  fetchTotalExercisesStart,
  fetchTotalExercisesSuccess 
} from "./exercise.reducer";

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

export function* onGetTotalExercisesStart() {
  yield takeLatest(fetchTotalExercisesStart.type, getTotalExercises);
}

export function* exerciseSagas() {
  yield all([
    call(onGetTotalExercisesStart)
  ]);
}
